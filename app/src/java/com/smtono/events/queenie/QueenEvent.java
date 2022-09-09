package com.smtono.events.queenie;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class QueenEvent extends ListenerAdapter {
    private String[] queen = {"queen", "kween", "kqween", "qween"};
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(Arrays.stream(event.getMessage().getContentRaw().split(" ")).anyMatch(q -> Arrays.asList(queen).contains(q))) {
            event.getChannel().sendMessage("Did someone call me?").queue();
        }
    }
}
