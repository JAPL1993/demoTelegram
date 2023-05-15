package com.telegram.example;

import com.pengrad.telegrambot.model.Update;

public interface BotEvent {
    void processUpdate(Update update);
}
