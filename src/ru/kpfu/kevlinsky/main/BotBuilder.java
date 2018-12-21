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
    public List<Command> commands = new ArrayList<>();
    public List<String> buttons = new ArrayList<>();

    public BotBuilder() {
        ApiContextInitializer.init();
    }

    public void createBot(String botUsername, String botToken){
        this.bot = new Bot();
        this.bot.setBotToken(botToken);
        this.bot.setBotUsername(botUsername);
    }

    public void addCommand(Command command) throws CommandException, ReactionException {
        if (this.commands.contains(command)) {
            throw new CommandException("This command is already exist");
        }
        this.commands.add(command);
        this.bot.addCommand(command);
    }

    public void addButton(String buttonName) throws ButtonException {
        if (this.buttons.contains(buttonName)) {
            throw new ButtonException("This button is already exist");
        }
        this.buttons.add(buttonName);
        this.bot.addButton(buttonName);

    }

    public void addButtonReaction(Command command) throws ReactionException{
        if (this.commands.contains(command)) {
            throw new ReactionException("This reaction is already exist");
        }
        this.commands.add(command);
        this.bot.addCommand(command);
    }

    public void deleteCommand(Command command){
        this.bot.deleteCommand(command);
        for (int i = 0; i < this.commands.size(); i++) {
            if (this.commands.get(i).equals(command)){
                this.commands.remove(i);
            }
        }
    }

    public void deleteButton(String buttonName){
        this.bot.deleteButton(buttonName);
    }

    public void deleteButtonReaction(Command command){
        this.bot.deleteCommand(command);
        for (int i = 0; i < this.commands.size(); i++) {
            if (this.commands.get(i).equals(command)){
                this.commands.remove(i);
            }
        }
    }

    public void addWeatherBot(){this.bot.setFlag(true);}

    public void removeWeatherBot(){this.bot.setFlag(false);}

    public Bot getBot(){
        return this.bot;
    }

    public List<String> getReactions() {
        List<String> reactions = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            reactions.add(commands.get(i).getReply());
        }
        return reactions;
    }

    public List<String> getCommands() {
        List<String> s = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            s.add(commands.get(i).getName());
        }
        return s;
    }

    public List<String> getButtons() {
        return buttons;
    }
}
