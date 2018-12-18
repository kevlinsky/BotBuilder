package ru.kpfu.kevlinsky.test;

import ru.kpfu.kevlinsky.exceptions.ButtonException;
import ru.kpfu.kevlinsky.exceptions.CommandException;
import ru.kpfu.kevlinsky.exceptions.ReactionException;
import ru.kpfu.kevlinsky.main.BotBuilder;

public class Test {

    public static void main(String[] args) {
        BotBuilder bb = new BotBuilder();
        bb.createBot("itistest_bot","656061436:AAH5IADgMFZmFrqIWffCAh9bkf7DIlxsufA");
        try {
            bb.addCommandReaction("/help", "How can I help you?");
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ReactionException e) {
            e.printStackTrace();
        }
        try {
            bb.addCommandReaction("/start", "Hello user!");
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (ReactionException e) {
            e.printStackTrace();
        }
        try {
            bb.addButton("/help");
        } catch (ButtonException e) {
            e.printStackTrace();
        }
        try {
            bb.addButtonReaction("/help", "How can I help you, sir?");
        } catch (ReactionException e) {
            e.printStackTrace();
        }
        bb.getBot().init();
    }
}
