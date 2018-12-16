package ru.kpfu.kevlinsky.test;

import ru.kpfu.kevlinsky.main.BotBuilder;

public class Test {

    public static void main(String[] args) {
        BotBuilder bb = new BotBuilder();
        bb.createBot("itistest_bot","656061436:AAH5IADgMFZmFrqIWffCAh9bkf7DIlxsufA");
        bb.addCommandReaction("/help", "How can I help you?");
        bb.addCommandReaction("/start", "Hello user!");
        bb.getBot().init();

    }
}
