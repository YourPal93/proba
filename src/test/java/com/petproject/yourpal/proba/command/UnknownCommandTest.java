package com.petproject.yourpal.proba.command;

import org.junit.jupiter.api.DisplayName;

import static com.petproject.yourpal.proba.command.UnknownCommand.UNKNOWN_MESSAGE;

@DisplayName("Unit-level testing for StartCommand")
public class UnknownCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return "/bogussfdsdf";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
