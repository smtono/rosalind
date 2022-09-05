package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import java.util.Random;

public class SimpCommand extends Command {
    EventWaiter waiter;
    private String[] replies = {"I aspire to be just as successful as Pokimane! (●'◡'●)",
                                "A simp? Where have I heard that before...",
                                "I have some of those!",
                                "What would we be without them? (❁´◡`❁)"};

    public SimpCommand() {
        this.hidden = true;
        this.name = "simp";
        this.aliases = new String[] {"simps"};
    }
    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        event.reply(replies[gen.nextInt(replies.length)]);
    }
}
