package com.petproject.yourpal.proba.command;

import com.petproject.yourpal.proba.service.SendBotMessageService;
import com.petproject.yourpal.proba.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    public final static String STAT_MESSAGE = "Proba Telegram Bot использует %s человек.";

    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        var activeCount = telegramUserService.retrieveAllActiveUsers().size();
        var chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId , String.format(STAT_MESSAGE, activeCount));
    }
}
