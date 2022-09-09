package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Random;

public class EgirlCommand extends Command {
    private String[] replies = {
            "owo",
            "uwu",
            "O w O",
            "U w U",
            "Sorry, I don't speak \"Egirl\"",
            "OwO? What's this?",
            "Don't talk like that!",
            "Stop being creepy!",
            "Are you an anime girl now??",
            "Stop trying to be an anime girl",
            "Weebs scare me!"};

    public EgirlCommand() {
        this.hidden = true;
        this.name = "owo";
        this.aliases = new String[] {"uwu", "OwO", "UwU"};
    }

    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        event.reply(replies[gen.nextInt(replies.length)]);
    }
}
