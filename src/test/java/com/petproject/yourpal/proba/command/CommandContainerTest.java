package com.petproject.yourpal.proba.command;

import com.petproject.yourpal.proba.service.SendBotMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        var senderService = Mockito.mock(SendBotMessageService.class);
        commandContainer = new CommandContainer(senderService);
    }

    @Test
    void shouldGetAllTheExistingCommands() {
        Arrays.stream(CommandName.values()).forEach(command -> {
            var retrievedCommand = commandContainer.retrieveCommand(command.getCommandName());
            assertNotEquals(UnknownCommand.class, retrievedCommand);
        });
    }

    @Test
    void  shouldReturnUnknownCommand() {
        var bogusCommand = "/aldfjasdfjkaslkjdf";

        var retrievedCommand = commandContainer.retrieveCommand(bogusCommand);

        assertEquals(UnknownCommand.class, retrievedCommand.getClass());
    }
}