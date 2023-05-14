package com.petproject.yourpal.proba.command;

import com.petproject.yourpal.proba.service.SendBotMessageService;
import com.petproject.yourpal.proba.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public static final String STOP_MESSAGE = "Деактивировал все ваши подписки \uD83D\uDE1F.";

    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
        var chatId = update.getMessage().getChatId().toString();
        telegramUserService.findByChatId(chatId).ifPresent(user -> {
            user.setActive(false);
            telegramUserService.save(user);
        });
    }
}
