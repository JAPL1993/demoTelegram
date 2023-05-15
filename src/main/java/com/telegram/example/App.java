package com.telegram.example;

import org.quartz.SchedulerException;

/**
 * Telegram
 * +
 *
 */
public class App {

    public static void main(String[] args) throws SchedulerException {
        Bot bot = new Bot("6285572110:AAHsUlkS7TZXDZcXJ59lb_l-g5jwLEj4NbA");
        bot.start();
    }
}
