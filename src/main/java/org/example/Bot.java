package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(Bot.class);
    private MessageHandler messageHandler = new MessageHandler();

    public Bot(String botToken) {
        super(botToken);
    }

    private void loggerMessage(Message message){
        log.info("ID:" + message.getChatId() + ", message = " + message.getText());
        System.out.println("ID:" + message.getChatId() + ", message = " + message.getText());
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatID = 0L;

        if (update.hasMessage()){
            loggerMessage(update.getMessage());
            chatID = update.getMessage().getChatId();
        }


        String responseMessage = messageHandler.returnMessage(update.getMessage().getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(responseMessage);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "alina_version1_bot";
    }
}
