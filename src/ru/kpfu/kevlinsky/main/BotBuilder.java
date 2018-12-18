package ru.kpfu.kevlinsky.main;

import org.telegram.telegrambots.ApiContextInitializer;
import ru.kpfu.kevlinsky.commands.Command;
import ru.kpfu.kevlinsky.exceptions.ButtonException;
import ru.kpfu.kevlinsky.exceptions.CommandException;
import ru.kpfu.kevlinsky.exceptions.ReactionException;

import java.util.ArrayList;
import java.util.List;

public class BotBuilder {

    public Bot bot;
    public List<String> reactions = new ArrayList<>();
    public List<String> commands = new ArrayList<>();
    public List<String> buttons = new ArrayList<>();

    public BotBuilder() {
        ApiContextInitializer.init();
    }

    public void createBot(String botUsername, String botToken){
        this.bot = new Bot();
        this.bot.setBotToken(botToken);
        this.bot.setBotUsername(botUsername);
    }

    public void addCommandReaction(String commandName, String reaction) throws CommandException, ReactionException {
        if (!this.commands.contains(commandName)) {
            this.commands.add(commandName);
            if (!this.reactions.contains(reaction)) {
                this.reactions.add(reaction);
                Command command = new Command(reaction);
                this.bot.addCommand(commandName, command);
            } else {
                throw new ReactionException("This reaction is already exist");
            }
        } else {
            throw new CommandException("This command is already exist");
        }
    }

    public void addButton(String buttonName) throws ButtonException {
        if (!this.buttons.contains(buttonName)) {
            this.buttons.add(buttonName);
            this.bot.addButton(buttonName);
        } else {
            throw new ButtonException("This button is already exist");
        }

    }

    public void addButtonReaction(String buttonName, String reaction) throws ReactionException{
        this.commands.add(buttonName);
        if (!this.reactions.contains(reaction)) {
            this.reactions.add(reaction);
            Command command = new Command(reaction);
            this.bot.addCommand(buttonName, command);
        } else {
            throw new ReactionException("This reaction is already exist");
        }
    }

    public void deleteCommand(String commandName){
        this.bot.deleteCommand(commandName);
        for (int i = 0; i < this.commands.size(); i++) {
            if (this.commands.get(i).equals(commandName)){
                this.commands.remove(i);
                this.reactions.remove(i);
            }
        }
    }

    public void deleteButton(String buttonName){
        this.bot.deleteButton(buttonName);
    }

    public void deleteButtonReaction(String reaction){
        this.bot.deleteCommand(reaction);
        for (int i = 0; i < this.reactions.size(); i++) {
            if (this.reactions.get(i).equals(reaction)){
                this.reactions.remove(i);
                this.commands.remove(i);
            }
        }
    }

    public Bot getBot(){
        return this.bot;
    }

    public List<String> getReactions() {
        return reactions;
    }

    public List<String> getCommands() {
        return commands;
    }

    public List<String> getButtons() {
        return buttons;
    }
}
