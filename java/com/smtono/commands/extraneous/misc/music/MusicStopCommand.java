package com.smtono.commands.extraneous.misc.music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.smtono.fun.music.MusicCommand;

public class MusicStopCommand extends Command {

    public MusicStopCommand() {
        this.name = "stop";
        this.aliases = new String[] {"disconnect"};
        this.cooldown = 2;
        this.help = "disconnects from the voice channel";
    }

    @Override
    protected void execute(CommandEvent event) {
        MusicCommand music = new MusicCommand();
        music.disconnect(event.getGuild());
    }
}