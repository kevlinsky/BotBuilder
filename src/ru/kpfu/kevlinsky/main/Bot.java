package ru.kpfu.kevlinsky.main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.kpfu.kevlinsky.commands.Command;
import org.telegram.telegrambots.ApiContextInitializer;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public List<Command> commands = new ArrayList<>();
    public List<String> commandsName = new ArrayList<>();

    public String botUsername;
    public String botToken;

    public void init() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg (Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        for (int i = 0; i < commands.size(); i++) {
            if (commandsName.get(i).equals(message.getText())) {
                commands.get(i).setBot(this);
                commands.get(i).setMessage(message);
                commands.get(i).execute();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void addCommand(String commandName, Command command){
        this.commands.add(command);
        this.commandsName.add(commandName);
    }

    public String getCommands(){
        return this.commandsName.toString();
    }
}