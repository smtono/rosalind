package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.smtono.fun.conversation.Talk;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.concurrent.TimeUnit;

public class SmallTalkCommand extends Command {
    EventWaiter waiter;
    Talk talk = new Talk();

    public SmallTalkCommand(EventWaiter waiter) {
        this.waiter = waiter;
        this.name = "talk";
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Okay! Hi!");

        //conversation.answer(e, waiter);

        waiter.waitForEvent(MessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor())
                        && e.getChannel().equals(event.getChannel())
                        && !e.getMessage().getContentRaw().equals(event.getMessage().getContentRaw())
                        && !e.getMessage().getContentRaw().equals("@everyone"),

                e -> {
                    talk.answer(e, waiter);
                },

                30, TimeUnit.SECONDS, () -> event.reply("Don't waste my time!")
        );
    }
}
