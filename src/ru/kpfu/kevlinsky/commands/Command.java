package ru.kpfu.kevlinsky.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.kpfu.kevlinsky.main.Bot;

public class Command implements ICommand{
    public final String reply;
    public Message message;
    public Bot bot;

    public Command(String reply) {
        this.reply = reply;
    }

    public void execute(){
        this.bot.sendMsg(this.message, this.reply);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }
}
