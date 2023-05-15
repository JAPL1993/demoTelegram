package com.telegram.example;

import java.io.IOException;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class MessageSender implements BotSendMessage {
    private TelegramBot bot;

    public MessageSender(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message) {

        SendMessage request = new SendMessage(
                chatId, message)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        bot.execute(request, new Callback<SendMessage, SendResponse>() {

            @Override
            public void onResponse(SendMessage request, SendResponse response) {
                System.out.println("enviado");
            }

            @Override
            public void onFailure(SendMessage request, IOException e) {
                throw new UnsupportedOperationException("Unimplemented method 'onFailure'");
            }

        });
    }

}
