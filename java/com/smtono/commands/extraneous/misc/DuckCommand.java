package com.smtono.commands.extraneous.misc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.smtono.fun.duckduckgo.DuckDuckGoSearch;
import com.smtono.util.helpers.Forbidden;

import java.util.List;

public class DuckCommand extends Command {

    List<String> words = Forbidden.getForbiddenWords();

    public DuckCommand() {
        this.name = "search";
        this.aliases = new String[] {"duck", "ddg", "duckduckgo"};
        this.cooldown = 2;
        this.help = "searches the internet using DuckDuckGo";
    }

    @Override
    protected void execute(CommandEvent event) {
        String query = event.getArgs();
        String result = DuckDuckGoSearch.getSearchResults(query);

        if (words.contains(query)) {
            event.reply("No way!");
        }
        else {
            event.reactSuccess();
            event.reply(result);
        }
    }
}
