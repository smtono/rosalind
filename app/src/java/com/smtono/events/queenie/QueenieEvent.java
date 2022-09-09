package com.smtono.events.queenie;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class QueenieEvent extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equalsIgnoreCase("queenie")) {
            event.getChannel().sendMessage("How can I help?").queue();
        }
    }
}
