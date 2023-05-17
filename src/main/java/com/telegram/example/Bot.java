package com.telegram.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.pengrad.telegrambot.ExceptionHandler;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramException;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

public class Bot {
    private final TelegramBot bot;
    private final List<BotEvent> events;
    private final ILogger console;

    public Bot(String token) throws SchedulerException {
        this.bot = new TelegramBot(token);
        this.events = new ArrayList<>();
        this.console = new ConsoleLogger(Bot.class);

        this.events.add(new TextMessageEvent());

        JobDetail job = JobBuilder.newJob()
                .ofType(TelegramMessageJob.class)
                .withIdentity("telegramMessageJob")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("telegramMessageTrigger")
                .forJob(job)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(60)
                        .repeatForever())
                .build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(job, trigger);
        scheduler.getContext().put("bot", this.bot);
        //this.scheduler.start();

    }

    public void start() {

        console.log(Level.INFO, "Starting Telegram Bot");
        bot.setUpdatesListener(new UpdatesListener() {

            @Override
            public int process(List<Update> updates) {
                for (Update update : updates) {
                    for (BotEvent event : events) {
                        event.processUpdate(update);
                    }
                }
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        },
                new ExceptionHandler() {

                    @Override
                    public void onException(TelegramException e) {
                        if (e.response() != null) {
                            // got bad response from telegram
                            e.response().errorCode();
                            e.response().description();
                        } else {
                            // probably network error
                            e.printStackTrace();
                        }
                    }

                });
    }

    public void stop() {
        console.log(Level.INFO, "Stopping Telegram Bot");
        bot.removeGetUpdatesListener();
    }
}
