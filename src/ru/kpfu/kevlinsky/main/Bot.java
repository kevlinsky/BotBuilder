package ru.kpfu.kevlinsky.main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.kpfu.kevlinsky.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public List<String> buttonsName = new ArrayList<>();

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
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> rows = new ArrayList<>();
        int n = this.buttonsName.size();
        if (n % 3 != 0) {
            for (int i = 0; i < (n / 3 + 1); i++) {
                rows.add(new KeyboardRow());
            }
            int i = 0;
            int count = 0;
            while (i != n){
                rows.get(count).add(this.buttonsName.get(i));
                i++;
                if (i % 3 == 0){count++;}
            }
        } else {
            for (int i = 0; i < n / 3; i++) {
                rows.add(new KeyboardRow());
            }
            int i = 0;
            int count = 0;
            while (i != n){
                rows.get(count).add(this.buttonsName.get(i));
                i++;
                if (i % 3 == 0){count++;}
            }
        }
        replyKeyboardMarkup.setKeyboard(rows);
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

    public void addCommand(Command command){
        this.commands.add(command);
        this.commandsName.add(command.getName());
    }

    public void addButton(String buttonName){
        this.buttonsName.add(buttonName);
    }

    public void deleteCommand(Command command){
        for (int i = 0; i < this.commandsName.size(); i++) {
            if(this.commands.get(i).equals(command)){
                this.commandsName.remove(i);
                this.commands.remove(i);
            }
        }
    }

    public void deleteButton(String buttonName){
        this.buttonsName.remove(buttonName);
        for (int i = 0; i < this.commandsName.size(); i++) {
            if(this.commandsName.get(i).equals(buttonName)){
                this.commandsName.remove(i);
                this.commands.remove(i);
            }
        }
    }

    public String getCommands(){
        return this.commandsName.toString();
    }

    public String getButtons(){return this.buttonsName.toString();}
}
