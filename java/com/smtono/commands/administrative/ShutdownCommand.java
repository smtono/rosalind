package com.smtono.commands.administrative;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ShutdownCommand extends Command {

    public ShutdownCommand()
    {
        this.hidden = true;
        this.name = "shutdown";
        this.guildOnly = false;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reactSuccess();
        event.reply("Goodbye!");
        event.getJDA().shutdown();
        System.exit(0);
    }

}