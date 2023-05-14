package com.petproject.yourpal.proba.bot;

import com.petproject.yourpal.proba.command.CommandContainer;
import com.petproject.yourpal.proba.service.TelegramUserService;
import com.petproject.yourpal.proba.service.impl.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.petproject.yourpal.proba.command.CommandName.NO;

@Component
public class ProbaTelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;

    private final static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    @Autowired
    public ProbaTelegramBot(TelegramUserService telegramUserService) {
        this.commandContainer =
                new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService);
    }
    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {;
            var message = update.getMessage().getText().trim();
            if(message.startsWith(COMMAND_PREFIX)) {
                commandContainer.retrieveCommand(message).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
