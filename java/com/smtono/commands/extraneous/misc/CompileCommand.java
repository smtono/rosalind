package com.smtono.commands.extraneous.misc;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.smtono.fun.doge.Interpreter.Interpreter;


public class CompileCommand extends Command {
    //static CommandEvent event;

    public CompileCommand() {
        this.name = "compile";
        this.aliases = new String[] {"run","dogespeak"};
        this.cooldown = 2;
        this.help = "compiles DogeSpeak code";
    }

    @Override
    protected void execute(CommandEvent event) {
     /*   CompileCommand.event = event;
        Thread compile = new Compile(1500);
        compile.start();

        try {
            compile.join(1500);
        } catch (InterruptedException e) {
            event.reply("Something went wrong.");
            e.printStackTrace();
        }
    }*/
        try {
            event.reply(Interpreter.run(event.getArgs()));
        }
        catch (Exception e) {
            e.printStackTrace();
            event.reply("Something went wrong");
        }
    }
}

/*class Compile extends Thread {
    private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean stopped = new AtomicBoolean(false);
    private int waitTime;

    public Compile(int waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    boolean isRunning() {
        return running.get();
    }

    boolean isStopped() {
        return stopped.get();
    }

    @Override
    public void run() {
        running.set(true);
        stopped.set(false);
        while (running.get()) {
            try {
                CompileCommand.event.reply(Interpreter.run(CompileCommand.event.getArgs()));
                Thread.sleep(waitTime);
            } catch (InterruptedException e){
                CompileCommand.event.reply("Something went wrong.");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            // do something
        }
        stopped.set(true);
    }
}*/
