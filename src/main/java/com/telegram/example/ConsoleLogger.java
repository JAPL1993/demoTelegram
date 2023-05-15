package com.telegram.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogger implements ILogger {
    Logger logger;
    private Class<?> class1;

    public ConsoleLogger(Class<?> clazz) {
        this.class1 = clazz;
        this.logger = Logger.getLogger(clazz.getName());
    }

    @Override
    public void log(Level level, String message) {
        logger.logp(level, class1.getName(), "", message);
    }

}
