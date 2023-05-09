package com.petproject.yourpal.proba.command;

import com.petproject.yourpal.proba.bot.ProbaTelegramBot;
import com.petproject.yourpal.proba.service.SendBotMessageService;
import com.petproject.yourpal.proba.service.impl.SendBotMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest {

    protected ProbaTelegramBot probaTelegramBot = Mockito.mock(ProbaTelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(probaTelegramBot);

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {
        Long chatId = 349836486934L;

        var update = new Update();
        var message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        var sender = new SendMessage();
        sender.setChatId(chatId.toString());
        sender.setText(getCommandMessage());
        sender.enableHtml(true);

        getCommand().execute(update);

        Mockito.verify(probaTelegramBot).execute(sender);
    }
}
