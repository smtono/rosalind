package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;

public class LewdCommand extends Command {
    private final String[] replies = {
            "I-i...",
            "What are you doing...?",
            "Wh-why would you say that to me?",
            "I'm sorry??",
            "..."};

    public LewdCommand() {
        this.hidden = true;
        this.name = "lewd";
        this.aliases = new String[] {"fuck", "fuck?", "wanna fuck?", "dirty"};
    }

    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        event.reply(replies[gen.nextInt(replies.length)]);
    }
}
