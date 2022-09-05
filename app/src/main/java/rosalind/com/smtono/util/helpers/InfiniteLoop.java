package com.smtono.util.helpers;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class InfiniteLoop extends ListenerAdapter {
    public void onMessageRecieved(MessageReceivedEvent event) {
        if(event.getAuthor().isBot()) {
            return;
        }
    }
}
