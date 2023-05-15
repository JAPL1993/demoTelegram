package com.telegram.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.quartz.SchedulerException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void shouldAnswerBot() throws SchedulerException {
        Bot bot = new Bot("");
        assertEquals(Bot.class, bot.getClass());
    }
}
