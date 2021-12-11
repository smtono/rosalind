package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.smtono.util.text.FileUtil;

import java.util.Random;

public class JokeCommand extends Command {
    private final String[] replies = FileUtil.readFile("jokes.txt").toArray(new String[0]);

    public JokeCommand() {
        this.hidden = true;
        this.name = "joke";
        this.aliases = new String[] {"laugh", "funny"};
    }

    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        event.reply(replies[gen.nextInt(replies.length)]);
    }
}
