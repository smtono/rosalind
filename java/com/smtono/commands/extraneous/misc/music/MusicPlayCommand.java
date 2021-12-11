package com.smtono.commands.extraneous.misc.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.smtono.fun.music.MusicCommand;

public class MusicPlayCommand extends Command {

    public MusicPlayCommand() {
        this.name = "play";
        this.aliases = new String[] {"music"};
        this.cooldown = 2;
        this.help = "plays music via links";
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicCommand music = new MusicCommand();
        music.loadAndPlay(event.getChannel(), event.getArgs());
    }
}