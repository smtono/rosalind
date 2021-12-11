package com.smtono.fun.conversation;

import com.smtono.util.text.FileUtil;

import java.util.*;

public class Conversation {
    private List<Reply> knowledge = getReplies(queenieScript); // Generic replies
    private String input;

    // file paths
    private static String queenieScript = "sample_script.txt";
    private static String shortTermMemory = "short_term_memory.txt";
    private static String longTermMemory = "long_term_memory.txt";
    private static String synonyms = "synonyms.txt";
    private static String substitutions = "substitutions.txt";


    // for reading lines
    private static String text = "";
    private static int currPosition = -1;
    private static char currChar = 0;
    private static List<Variable> variables = new ArrayList<>();

    public Conversation(String input) {
        this.input = input;
    }

    public void setInput(String input) { this.input = input; }
    public void setKnowledge(List<Reply> knowledge) { this.knowledge = knowledge; }

    public static String processUserInput(Conversation conversation) {
        Random gen = new Random();
        conversation.input = replaceSynonyms(conversation.input);
        List<Reply> availableReplies = orderReplies(findReplies(conversation.input, conversation.knowledge, true));
        availableReplies.forEach(reply1 -> replaceVariables(conversation.input, reply1));

        String reply = availableReplies.get(0).getReplies().get(gen.nextInt(availableReplies.get(0).getReplies().size()));

        if (reply.contains("@")) {
            return "Sorry, what?";
        }
        else {
            return reply;
        }
    }

    private static HashMap<String, List<String>> getSynonyms() {
        List<String> lines = FileUtil.readFile(synonyms);
        HashMap<String, List<String>> output = new HashMap<>();
        for(String element : lines) {
            String[] line = element.split(";"); // split by the regex ;
            for(String word : line){
                String[] currentWord = word.split("/"); // split by the regex /

                String[] synonyms = currentWord[1].split(":"); // split by the regex :
                output.put(currentWord[0], Arrays.asList(synonyms));
            }
        }
        return output;
    }

    private static HashMap<String, String> getSubstitutions() {
        List<String> lines = FileUtil.readFile(substitutions);
        HashMap<String, String> output = new HashMap<>();

        for(String element : lines) {
            String[] line = element.split(";"); // split by the regex ;
            for(String word : line){
                String[] currentWord = word.split("/"); // split by the regex /
                output.put(currentWord[0], currentWord[1]);
            }
        }
        return output;
    }

    private static List<Reply> orderReplies(List<Reply> replies) {
        replies.sort(Comparator.comparingInt(Reply::getWeight));
        Collections.reverse(replies);
        return replies;
    }

    public static List<Reply> getReplies(String filePath) {
        List<String> lines = FileUtil.readFile(filePath);
        List<Reply> replies = new ArrayList<>();

        lines.forEach(line -> {
            replies.add(extractReply(line));
        });

        return replies;
    }

    public static List<Reply> getReplies(List<String> lines) {
        List<Reply> replies = new ArrayList<>();

        lines.forEach(line -> {
            replies.add(extractReply(line));
        });

        return replies;
    }

    private static List<Reply> findReplies(String input, List<Reply> knowledgePool, boolean isFromScript) {
        List<Reply> keywords = new ArrayList<>();

        for (Reply reply : knowledgePool) {
            if (input.toLowerCase().contains(reply.getKey())) {
                keywords.add(reply);
            }
        }

        if (keywords.isEmpty()) {
            if (isFromScript) {
                keywords.add(knowledgePool.get(0)); // put xnone
            }
            else {
                return keywords; // returns empty
            }
        }

        return keywords;
    }

    private static String replaceSynonyms(String input) {
        List<String> sentence = Arrays.asList(input.split(" "));
        HashMap<String, List<String>> synonyms = getSynonyms();

        for(int i = 0; i < sentence.size(); i++) {
            for (Map.Entry<String, List<String>> entry : synonyms.entrySet()) {
                String key = entry.getKey();
                List<String> synonymList = entry.getValue();

                for (String match : synonymList) {
                    String[] replacement = match.split(" ");
                    if (replacement.length > 1) {
                        String userSentence = String.join(" ", sentence);
                        if (userSentence.contains(match)) {
                            int start = userSentence.indexOf(match);
                            int end = start + match.length();
                            String newSentence = userSentence.substring(0, start) + key + userSentence.substring(end);
                            sentence = Arrays.asList(newSentence.split(" "));
                        }
                    } else if (match.equalsIgnoreCase(sentence.get(i))) {
                        sentence.set(i, key);
                    }
                }
            }
        }
        return String.join(" ", sentence);
    }

    private static String replaceSubstitutions(String variable) {
        List<String> input = Arrays.asList(variable.split(" "));
        HashMap<String, String> substitutions = getSubstitutions();
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < input.size(); i++) {
            for (Map.Entry<String, String> entry : substitutions.entrySet()) {
                if(entry.getValue().equalsIgnoreCase(input.get(i))) {
                    input.set(i, entry.getKey());
                    break;
                }
            }
        }

        for(int i = 0; i < input.size(); i++) {
            if (!(i == input.size() - 1)) {
                output.append(input.get(i)).append(" ");
            }
            else {
                output.append(input.get(i));
            }
        }

        return output.toString();
    }

    private static void replaceVariables(String input, Reply reply) {
        if (!reply.getVariables().isEmpty()) {
            reply.getVariables().forEach(variable -> {
                if (variable.getName().equals("[b]")) { // "before"
                    int idx = input.toLowerCase().indexOf(reply.getKey());
                    variable.setValue(input.substring(0, idx));
                }
                else { // "after"
                    int idx = input.toLowerCase().indexOf(reply.getKey()) + reply.getKey().length();
                    variable.setValue(input.substring(idx).replaceAll("\\p{P}", "")); // cut punctuation
                }
            });

            String before = "";
            String after = "";

            for(int i = 0; i < reply.getVariables().size(); i++) {
                if (reply.getVariables().get(i).getName().equals("[b]")) {
                    before = reply.getVariables().get(i).getValue();
                    before = replaceSubstitutions(before);
                }
                else {
                    after = reply.getVariables().get(i).getValue();
                    after = replaceSubstitutions(after);
                }
            }

            for (int i = 0; i < reply.getReplies().size(); i++) {
                reply.getReplies().set(i, reply.getReplies().get(i).replace("[b]", before));
                reply.getReplies().set(i, reply.getReplies().get(i).replace("[a]", after));
            }

        }
    }

    // FOR READING LINES
    private static void advance() {
        currPosition += 1;
        if (currPosition < text.length()) {
            currChar = text.charAt(currPosition);
        }
        else {
            currChar = 0; // Default null character
        }
    }

    private static Reply extractReply(String line) {
        // /key/:weight:"response","response";

        // resetting values
        // for line reading
        text = "";
        currPosition = -1;
        currChar = 0;

        // for extracting replies
        String key = "";
        variables = new ArrayList<>();
        int weight = 0;
        List<String> replies = new ArrayList<>();

        text = line;
        advance();

        // Reading line for components
        while (currChar != 0) {
            switch (currChar) {
                case '/': // within these is the key
                    advance();
                    key = makeKey();
                    break;
                case ':': // within these is the weight
                    advance(); // first :
                    weight = Character.getNumericValue(currChar); // We are past the first colon, must be at the number
                    advance(); // weight
                    advance(); // last :
                    break;
                case '\"': // within these is a reply
                    advance();
                    replies.add(makeReply());
                    break;
                case ',': // in between replies
                    advance();
                    break;
                case ';': // this is the end of the line
                    advance();
                    return new Reply(key, variables, weight, replies);
            }
        }
        System.out.println("Something went wrong in extractReply()");
        return null;
    }

    private static String makeKey() {
        StringBuilder output = new StringBuilder();

        while (currChar != 0) {
            switch (currChar) {
                case '[': // a variable is being made
                    StringBuilder variable = new StringBuilder();
                    variable.append(currChar); // [
                    advance();
                    variable.append(currChar); // x or y
                    advance();
                    variable.append(currChar); // ]
                    variables.add(new Variable(variable.toString()));
                    advance();
                    break;
                case '/': // terminating character
                    advance();
                    return output.toString();
                default:
                    output.append(currChar);
                    advance();
                    break;
            }
        }
        System.out.println("Something went wrong. In makeKey()");
        return "";
    }

    private static String makeReply() {
        StringBuilder output = new StringBuilder();

        while (currChar != 0) {
            if (currChar == '\"') {
                advance();
                return output.toString();
            } else {
                output.append(currChar);
                advance();
            }
        }

        System.out.println("Something went wrong. In makeReply()");
        return "";
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        boolean done = false;

        while (true) {
            System.out.print("Input: ");
            String input = scn.nextLine();

            if (input.equalsIgnoreCase("stop")) {
                break;
            }

            Conversation conversation = new Conversation(input);
            System.out.println("Output: " + processUserInput(conversation));
        }
    }
}

class Reply {
    String key;
    List<Variable> variables;
    int weight;
    List<String> replies;

    public Reply(String key, List<Variable> variables, int weight, List<String> replies) {
        this.key = key;
        this.variables = variables;
        this.weight = weight;
        this.replies = replies;
    }

    public String getKey() { return key; }
    public List<Variable> getVariables() { return variables; }
    public int getWeight() { return weight; }
    public List<String> getReplies() { return replies; }

    @Override
    public String toString() {
        return "Reply{" +
                "key='" + key + '\'' +
                ", variables=" + variables +
                ", weight=" + weight +
                ", replies=" + replies +
                '}';
    }
}

class Variable {
    String name;
    String value;

    public Variable(String name) {
        this.name = name;
        this.value = "";
    }

    public String getName() { return name; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}