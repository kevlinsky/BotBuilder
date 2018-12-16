package main;

import commands.Command;

public class BotBuilder {

    public Bot bot;

    public BotBuilder() {
    }

    public void createBot(String botUsername, String botToken){
        this.bot = new Bot();
        this.bot.setBotToken(botToken);
        this.bot.setBotUsername(botUsername);
    }

    public void addCommandReaction(String commandName, String reaction){
        Command command = new Command(reaction);
        this.bot.addCommand(commandName, command);
    }

    public Bot getBot(){
        return this.bot;
    }

}
