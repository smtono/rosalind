package com.smtono.events.extraneous;

import com.smtono.commands.talk.conversation.ConversationCommand;
import com.smtono.fun.conversation.Conversation;
import com.smtono.main.Queenie;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ConversationEvent extends ListenerAdapter {
    private final String[] bye = {"stop", "bye", "goodbye", "farewell"};
    private final String ownerID = Queenie.authentication.get(0);
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if(!ConversationCommand.isDone()) {
            if(event.getAuthor().getId().equals(ownerID) && Arrays.stream(event.getMessage().getContentRaw().split(" ")).anyMatch(b -> Arrays.asList(bye).contains(b))) {
                event.getChannel().sendMessage("Alright, goodbye!").queue();
                ConversationCommand.setDone(true);
                ConversationCommand.setAuthor(null);
            }

            if (event.getAuthor().equals(ConversationCommand.getAuthor()) && event.getChannel().equals(ConversationCommand.getChannel()) && !event.getMessage().getContentRaw().contains("queenie")) {
                event.getChannel().sendTyping().queue();

                try {
                    Thread.sleep( 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }

                if (Arrays.stream(event.getMessage().getContentRaw().split(" ")).anyMatch(b -> Arrays.asList(bye).contains(b))) {
                    event.getChannel().sendMessage("Alright, goodbye!").queue();
                    ConversationCommand.setDone(true);
                    ConversationCommand.setAuthor(null);
                }
                else {
                    Conversation conversation = new Conversation("");
                    conversation.setInput(event.getMessage().getContentRaw());
                    String reply = Conversation.processUserInput(conversation);
                    event.getChannel().sendMessage(reply).queue();
                }
            }
        }
    }
}
