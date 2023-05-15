package com.telegram.example;

import java.util.logging.Level;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;

import com.pengrad.telegrambot.TelegramBot;

public class TelegramMessageJob implements Job {
    private ILogger console = new ConsoleLogger(TelegramMessageJob.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        SchedulerContext schedulerContext = null;
        try {
            schedulerContext = arg0.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }
        TelegramBot bot = (TelegramBot) schedulerContext.get("bot");
        MessageSender sender = new MessageSender(bot);
        sender.sendMessage("1142039979", "mensaje enviado desde el job");
    }

}
