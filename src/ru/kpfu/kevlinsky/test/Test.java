package ru.kpfu.kevlinsky.test;

import ru.kpfu.kevlinsky.commands.Command;
import ru.kpfu.kevlinsky.exceptions.ButtonException;
import ru.kpfu.kevlinsky.exceptions.CommandException;
import ru.kpfu.kevlinsky.exceptions.ReactionException;
import ru.kpfu.kevlinsky.main.BotBuilder;

public class Test {

    public static void main(String[] args) {
        BotBuilder bb = new BotBuilder();
        bb.createBot("itistest_bot","656061436:AAH5IADgMFZmFrqIWffCAh9bkf7DIlxsufA");
        try {
            Command command = new Command("/help", "How can I help you?");
            bb.addCommand(command);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ReactionException e) {
            e.printStackTrace();
        }
        try {
            Command command1 = new Command("/start", "Hello user!");
            bb.addCommand(command1);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ReactionException e) {
            e.printStackTrace();
        }
        try {
            bb.addButton("Help");
        } catch (ButtonException e) {
            e.printStackTrace();
        }
        try {
            Command command2 = new Command("Help", "I must help you!");
            bb.addButtonReaction(command2);
        } catch (ReactionException e) {
            e.printStackTrace();
        }
        bb.getBot().init();
    }
}
