package com.petproject.yourpal.proba.service.impl;

import com.petproject.yourpal.proba.bot.ProbaTelegramBot;
import com.petproject.yourpal.proba.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {

    private final ProbaTelegramBot probaTelegramBot;

    @Autowired
    public SendBotMessageServiceImpl(ProbaTelegramBot probaTelegramBot) {
        this.probaTelegramBot = probaTelegramBot;
    }

    @Override
    public void sendMessage(String chatid, String message) {
        var sender = new SendMessage();
        sender.setChatId(chatid);
        sender.setText(message);
        sender.enableHtml(true);

        try {
            probaTelegramBot.execute(sender);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
}
