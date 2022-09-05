package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;

public class InsultCommand extends Command {
    private final String[] replies = {
            "Wh-what did I do??",
            "I'm sorry! I'll try to be better...",
            "How dare you say that to a lady!",
            "You'll regret saying that to me!",
            "no u"};

    public InsultCommand() {
        this.hidden = true;
        this.name = "stupid";
        this.aliases = new String[] {"idiot", "no life", "nobody", "dumb", "fuck you"};
    }

    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        event.reply(replies[gen.nextInt(replies.length)]);
    }
}
