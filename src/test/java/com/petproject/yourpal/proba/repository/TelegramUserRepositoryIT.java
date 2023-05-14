package com.petproject.yourpal.proba.repository;

import com.petproject.yourpal.proba.entity.TelegramUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TelegramUserRepositoryIT {

    @Autowired
    private TelegramUserRepository userRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsers() {
        var listOfUsers = userRepository.findAllByActiveTrue();

        assertEquals(5, listOfUsers.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveTelegramUser() {
        var telegramUser = new TelegramUser();

        telegramUser.setChatId("1234567890");
        telegramUser.setActive(false);
        userRepository.save(telegramUser);

        var savedUser = userRepository.findById("1234567890");

        assertTrue(savedUser.isPresent());
        assertEquals(telegramUser, savedUser.get());
    }
}
