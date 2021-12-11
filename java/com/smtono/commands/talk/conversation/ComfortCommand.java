package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;

public class ComfortCommand extends Command {

    private String[] replies = {
            "Be happy!",
            "I'm happy",
            "I'm happy!!!",
            "Are you happy?",
            "Let's all smile!",
            "Things'll be alright!",
            "Everything will be okay!",
            "THAT'S THE POWER OF LOVEEE!!!!~~!!!!!",
            "I'm not the best at talking, but woo! happeh~"
    };

    public ComfortCommand() {
        this.name = "happy";
        this.aliases = new String[] {"cheer", "cheer up", "smile", "hug"};
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        event.reply(replies[gen.nextInt(replies.length)]);
    }
}
