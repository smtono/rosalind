package com.smtono;

import com.smtono.commands.administrative.ShutdownCommand;
import com.smtono.commands.extraneous.misc.CompileCommand;
import com.smtono.commands.extraneous.misc.DuckCommand;
import com.smtono.commands.extraneous.misc.music.MusicPlayCommand;
import com.smtono.commands.extraneous.misc.music.MusicSkipCommand;
import com.smtono.commands.extraneous.misc.music.MusicStopCommand;
import com.smtono.commands.talk.conversation.*;
import com.smtono.commands.talk.extraneous.ChooseCommand;
import com.smtono.commands.talk.extraneous.RepeatCommand;
import com.smtono.events.extraneous.ConversationEvent;
import com.smtono.events.extraneous.TonoEvent;
import com.smtono.events.queenie.QueenEvent;
import com.smtono.events.queenie.QueenieEvent;
import com.smtono.util.text.FileUtil;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.List;

public class Queenie {
    // temp fix.
    public static List<String> authentication = FileUtil.readFile("authentication.txt");
    public static void main(String[] args) throws LoginException, IllegalArgumentException {
        String token = authentication.get(0);
        String ownerID = authentication.get(1);

        CommandClientBuilder client = new CommandClientBuilder();
        EventWaiter waiter = new EventWaiter();

        client
                .setOwnerId(ownerID)
                .setPrefix("queenie ")
                .setEmojis("✅", "❓", "❎" )
                .setActivity(Activity.listening("queenie"))
                .addCommands(
                            // talking
                            new HelloCommand(waiter),
                            new SmallTalkCommand(waiter),
                            new ConversationCommand(waiter),
                            new SadCommand(),
                            new ComfortCommand(),
                            new InsultCommand(),
                            new JokeCommand(),

                            // administrative
                            new ShutdownCommand(),

                            // extraneous
                            new CompileCommand(),
                            new DuckCommand(),
                            new ChooseCommand(),
                            new RepeatCommand(),

                            // music
                            new MusicPlayCommand(),
                            new MusicSkipCommand(),
                            new MusicStopCommand()
                );

        JDA jda = JDABuilder
                .createDefault(token)
                .setActivity(Activity.listening("queenie"))
                .addEventListeners(client.build(), waiter)
                .addEventListeners(
                                    new QueenieEvent(),
                                    new QueenEvent(),
                                    new ConversationEvent(),
                                    new TonoEvent())
                .build();
    }
}
