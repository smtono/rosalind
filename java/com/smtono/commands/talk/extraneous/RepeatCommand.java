package com.smtono.commands.talk.extraneous;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class RepeatCommand extends Command {

    public RepeatCommand() {
        this.name = "repeat";
        this.aliases = new String[] {"say"};
        this.cooldown = 2;
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getMessage().getContentRaw().contains("@")) {
            event.reply("You want me to get in trouble?");
        }
        else {
            event.reply(event.getArgs());
        }
    }

}
