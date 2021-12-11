package com.smtono.events.meme;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

public class DadEvent extends ListenerAdapter {
    private String[] im = {"im", "i'm", "Im", "I'm", "I am", "i am"};

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (Arrays.stream(event.getMessage().getContentRaw().split(" ")).anyMatch(q -> Arrays.asList(im).contains(q))) {
            String[] split = event.getMessage().getContentRaw().split(" ");
            List<String> content = Arrays.asList(split);
            StringBuilder what = new StringBuilder();

            for(int i = 0; i < content.size(); i++) {
                String word = content.get(i);
                if (Arrays.asList(im).contains(word)) {
                    for(int j = i + 1; j < content.size(); j++) {
                        if (!(j == content.size() - 1)) {
                            what.append(content.get(j)).append(" ");
                        }
                        else {
                            what.append(content.get(j));
                        }
                    }
                }
            }

            if (!event.getAuthor().isBot() && !event.getMessage().getContentRaw().contains("@everyone")) {
                event.getChannel().sendMessage("Hi, " + what + ". I'm dad!").queue();
            }
        }
    }
}

