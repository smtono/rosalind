package com.smtono.commands.talk.conversation;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HelloCommand extends Command {
    private final EventWaiter waiter;
    private String[] replies = {"I'm Queenie!",
                                "I think you know who I am!",
                                "What a wonderful name!",
                                "My, you look rough buddy.",
                                "Are you my new sugar daddy?",
                                "Are you here to take me away?~",
                                "Don't expect me to remember you!"};
    private String[] hello = {"Hello!",
                                "Hi!",
                                "Hey!",
                                "Greetings!",
                                "Hello there!",
                                "Hi there!"};
    private String[] prompts = {"What's your name?",
                                "Do you have a name?",
                                "Tell me your name!",
                                "I don't really care what your name is, but tell me anyway!",
                                "Now tell me your name!",
                                "Name. Now!!!"};

    public HelloCommand(EventWaiter waiter) {
        this.waiter = waiter;
        this.hidden = true;
        this.name = "hello";
        this.aliases = new String[] {"hi", "howdy", "greetings", "hewwo"};
        this.cooldown = 5;
    }
    @Override
    protected void execute(CommandEvent event) {
        Random gen = new Random();
        int randomHello = gen.nextInt(hello.length);
        int randomPrompt = gen.nextInt(prompts.length);
        int randomReply = gen.nextInt(replies.length);

        if(event.getMessage().getContentRaw().equals("queenie hewwo")) {
            event.reply("Hewwo OwO");
        } else {
            event.reply(hello[randomHello].concat(" ").concat(prompts[randomPrompt]));

            waiter.waitForEvent(MessageReceivedEvent.class,
                    e -> e.getAuthor().equals(event.getAuthor())
                            && e.getChannel().equals(event.getChannel())
                            && !e.getMessage().getContentRaw().equals(event.getMessage().getContentRaw())
                            && !e.getMessage().getContentRaw().equals("@everyone"),

                    e -> event.reply("Nice to meet you, ".concat(e.getMessage().getContentRaw()).concat(". ").concat(replies[randomReply])),

                    30, TimeUnit.SECONDS, () -> event.reply("Don't waste my time!")
            );
        }
    }
}
