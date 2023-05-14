package com.petproject.yourpal.proba.command;

import com.petproject.yourpal.proba.entity.TelegramUser;
import com.petproject.yourpal.proba.service.SendBotMessageService;
import com.petproject.yourpal.proba.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    public final static String START_MESSAGE = "Привет. Я proba Telegram Bot. Я помогу тебе быть в курсе последних " +
            "статей тех авторов, котрые тебе интересны. Я еще маленький и только учусь.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        var chatId = update.getMessage().getChatId().toString();

        telegramUserService.findByChatId(chatId).ifPresentOrElse(
                user -> {
                    user.setActive(true);
                    telegramUserService.save(user);
                },
                () -> {
                    var newUser = new TelegramUser();
                    newUser.setChatId(chatId);
                    newUser.setActive(true);
                    telegramUserService.save(newUser);
                });
        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
