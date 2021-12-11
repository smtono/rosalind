package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class ConversationCommand extends Command {
    EventWaiter waiter;
    static boolean done = true;
    static User author = null;
    static MessageChannel channel = null;

    public ConversationCommand(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "chat";
        this.aliases = new String[] {"converse", "conversation"};
        this.help = "chat with a queen!";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reactSuccess();
        event.reply("Okay, let's talk. You begin.");
        setDone(false);
        author = event.getAuthor();
        channel = event.getChannel();
    }

    public static boolean isDone() { return done; }
    public static void setDone(boolean done) { ConversationCommand.done = done; }

    public static User getAuthor() { return author; }
    public static void setAuthor(User author) { ConversationCommand.author = author; }

    public static MessageChannel getChannel() { return channel; }
    public static void setChannel(MessageChannel channel) { ConversationCommand.channel = channel; }
}
