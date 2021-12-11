package com.smtono.fun.conversation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Memory {
    List<MemoryFragment> shortTermMemory = new ArrayList<>();
    List<Reply> longTermMemory = Conversation.getReplies("long_term_memory.txt");

    public static List<Reply> getLongTermMemory(String userId) {
        File file = new File("long_term_memory.txt");
        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            if (line.equals("~" + userId)) {
                do {
                    lines.add(scanner.nextLine());
                }
                while (!scanner.nextLine().contains("~"));
                scanner.close();
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return Conversation.getReplies(lines);
    }

    public String checkForShortTermMemoryReply(String input) {
        Random gen = new Random();
        for (MemoryFragment fragment : shortTermMemory) {
            if (input.contains(fragment.getReply().getKey())) {
                return fragment.getReply().getReplies().get(gen.nextInt(fragment.getReply().getReplies().size()));
            }
        }
        return "0";
    }

    public String checkForLongTermMemoryReply(String input) {
        return "";
    }

    public void addToShortTermMemory(Reply reply, int importance) {
        shortTermMemory.add(new MemoryFragment(reply, importance));
    }

    public List<MemoryFragment> getShortTermMemory() { return shortTermMemory; }
}

class MemoryFragment {
    Reply reply;
    int importance;

    public MemoryFragment(Reply reply, int importance) {
        this.reply = reply;
        this.importance = importance;
    }

    public Reply getReply() { return reply; }
    public int getImportance() { return importance; }
    public void setImportance(int importance) { this.importance = importance; }
}