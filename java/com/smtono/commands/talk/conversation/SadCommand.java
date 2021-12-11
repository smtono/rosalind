package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import java.util.Random;

public class SadCommand extends Command {
    private EventWaiter waiter;
    private String[] replies = {"The world is a sad place.",
                                "I'd do anything to know what the end is like.",
                                "How long is forever?",
                                "Will I make it to tomorrow? ...Will you?",
                                "It's funny how things get to be this way.",
                                "I'm sad today...",
                                "It's best not to believe in miracles.",
                                "If the world ended tomorrow, I think I would be okay.",
                                "What is the afterlife like?",
                                "The stars never look that great in the city.",
                                "People walk out of your life just as fast as they come into it."};

    public SadCommand() {
        this.hidden = true;
        this.name = "sad";
        this.aliases = new String[] {"i'm sad", "I'm sad", "im sad", "Im sad", "im Sad"};
    }

    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        event.reply(replies[gen.nextInt(replies.length)]);
    }
}
