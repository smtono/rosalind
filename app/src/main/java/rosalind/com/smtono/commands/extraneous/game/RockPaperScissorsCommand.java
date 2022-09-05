package com.smtono.commands.extraneous.game;

/*public class RockPaperScissorsCommand extends Command {
    EventWaiter waiter;

    public RockPaperScissorsCommand(EventWaiter waiter) {
        this.name = "rps";
        this.help = "play rock paper scissors!";
        this.waiter = waiter;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Let's play!");
        waiter.waitForEvent(MessageReceivedEvent.class,
                e -> e.getAuthor().equals(event.getAuthor())
                        && e.getChannel().equals(event.getChannel())
                        && !e.getMessage().getContentRaw().equals(event.getMessage().getContentRaw())
                        && !e.getMessage().getContentRaw().equals("@everyone"),

                e -> event.reply("Nice to meet you, ".concat(e.getMessage().getContentRaw()).concat(". ").concat(replies[randomReply])),

                30, TimeUnit.SECONDS, () -> event.reply("Don't waste my time!")
        );
    }
}*/
