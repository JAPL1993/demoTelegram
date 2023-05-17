package com.telegram.example;

import java.util.logging.Level;

import com.pengrad.telegrambot.model.Update;

public class TextMessageEvent implements BotEvent {

    private ILogger console;

    TextMessageEvent() {
        this.console = new ConsoleLogger(TextMessageEvent.class);
    }

    @Override
    public void processUpdate(Update update) {
        console.log(Level.INFO, "Este es el mensaje fuera del if"+update.message().text());
        if (update.message() != null) {
            console.log(Level.INFO, update.message().text());
        }
    }

}
