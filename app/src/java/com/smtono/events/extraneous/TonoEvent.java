package com.smtono.events.extraneous;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class TonoEvent extends ListenerAdapter {
    String[] replies = {
            "That's my creator!",
            "Who's tono?",
            "What's a tono?",
            "Mom!",
            "I'm pretty sure that's a guy."
    };

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().toLowerCase().contains("queenie tono")) {
            if (event.getAuthor().getName().equalsIgnoreCase("teflon")) {
                event.getChannel().sendMessage("Isn't that you?").queue();
            }
            else {
                Random gen = new Random();
                event.getChannel().sendMessage(replies[gen.nextInt(replies.length)]).queue();
            }
        }
    }
}
