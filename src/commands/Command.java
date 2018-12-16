package commands;

import main.Bot;
import org.telegram.telegrambots.api.objects.Message;

public class Command{
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
