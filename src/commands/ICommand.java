package commands;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface ICommand {
    void execute(Message message, TelegramLongPollingBot bot);
}
