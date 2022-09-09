package com.smtono.fun.conversation;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.smtono.util.text.FileUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Talk {
    HashMap<String, String> knowledge;

    /**
     * This is a default constructor.
     */
    public Talk() {
        knowledge = TalkUtil.readFromKnowledge();
    }


    public void answer(MessageReceivedEvent event, EventWaiter waiter) {
        String question = event.getMessage().getContentRaw();
        Set<String> keys = knowledge.keySet();

        if (question.contains("\n") || question.contains("@")) {
            event.getChannel().sendMessage("My system is overloaded.").queue();
        }
        else {
            for (String key : keys) {

                String lowerKey = key.toLowerCase();
                String lowerQuestion = question.toLowerCase();

                if (lowerKey.equals(lowerQuestion)) {
                    event.getChannel().sendMessage(knowledge.get(key)).queue();
                    return;
                    //event.reply(knowledge.get(key));
                }
            }

            event.getChannel().sendMessage("Sorry I don't know what to say... How should I reply in the future?").queue();
            train(event, waiter);
        }
    }

    /** Trains for future output */
    public void train(MessageReceivedEvent event, EventWaiter waiter) {
        String question = event.getMessage().getContentRaw();

        // event.reply("Sorry I don't know what to say... How should I reply in the future?");

        waiter.waitForEvent(
                MessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor())
                        && e.getChannel().equals(event.getChannel())
                        && !e.getMessage().getContentRaw().equals(event.getMessage().getContentRaw())
                        && !e.getMessage().getContentRaw().contains("@"),

                e -> {
                    event.getChannel().sendMessage("Alright! I'll remember that for next time.").queue();
                    //event.reply("Alright! I'll remember that for next time.");
                    event.getMessage().addReaction("✅").queue();
                    knowledge.put(question, e.getMessage().getContentRaw());
                    TalkUtil.writeToKnowledge(question, e.getMessage().getContentRaw());
                },
                30, TimeUnit.SECONDS, () -> event.getChannel().sendMessage("Don't waste my time!").queue()
        );
    }
}

class TalkUtil {
    public static void writeToKnowledge(String key, String reply) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("knowledge.txt", true)))) {
            out.println(key + "/" + reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> readFromKnowledge() {
        List<String> lines = FileUtil.readFile("knowledge.txt");
        HashMap<String, String> knowledge = new HashMap<>();
        String[] output;

        // TODO: finish
        for (String line : lines) {
            output = line.split("/");
            knowledge.put(output[0], output[1]);
        }
        return knowledge;
    }
}
