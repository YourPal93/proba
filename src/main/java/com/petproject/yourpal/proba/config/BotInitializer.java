package com.petproject.yourpal.proba.config;

import com.petproject.yourpal.proba.bot.ProbaTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {
    private final ProbaTelegramBot probaTelegramBot;

    @Autowired
    public BotInitializer(ProbaTelegramBot probaTelegramBot) {
        this.probaTelegramBot = probaTelegramBot;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init()throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try{
            telegramBotsApi.registerBot(probaTelegramBot);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
