package com.petproject.yourpal.proba.service.impl;

import com.petproject.yourpal.proba.bot.ProbaTelegramBot;
import com.petproject.yourpal.proba.service.SendBotMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-leve testing for SendBotMessageSrvice")
class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private ProbaTelegramBot probaTelegramBot;

    @BeforeEach
    public void init() {
        probaTelegramBot = Mockito.mock(ProbaTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(probaTelegramBot);
    }

    @Test
    void shouldProperlySendMessage() throws TelegramApiException {
        var chatId = "test_chat_id";
        var message = "test_chat_message";

        var sender = new SendMessage();
        sender.setChatId(chatId);
        sender.setText(message);
        sender.enableHtml(true);

        sendBotMessageService.sendMessage(chatId, message);

        Mockito.verify(probaTelegramBot).execute(sender);
    }
}