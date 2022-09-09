package com.smtono.commands.extraneous.misc.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.smtono.fun.music.MusicCommand;

public class MusicSkipCommand extends Command {

    public MusicSkipCommand() {
        this.name = "skip";
        this.cooldown = 2;
        this.help = "skips the current song";
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicCommand music = new MusicCommand();
        music.skipTrack(event.getChannel());
    }
}