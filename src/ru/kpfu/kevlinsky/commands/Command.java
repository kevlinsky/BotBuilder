package ru.kpfu.kevlinsky.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.kpfu.kevlinsky.main.Bot;

public class Command implements ICommand{
    public final String name;
    public final String reply;
    public Message message;
    public Bot bot;

    public Command(String name, String reply) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public String getReply() {
        return reply;
    }

    public String toString(){
        String s = name + " --> " + reply;
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;

        if (name != null ? !name.equals(command.name) : command.name != null) return false;
        return reply != null ? reply.equals(command.reply) : command.reply == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (reply != null ? reply.hashCode() : 0);
        return result;
    }
}
