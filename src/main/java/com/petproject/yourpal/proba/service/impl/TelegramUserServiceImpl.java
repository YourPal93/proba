package com.petproject.yourpal.proba.service.impl;

import com.petproject.yourpal.proba.entity.TelegramUser;
import com.petproject.yourpal.proba.repository.TelegramUserRepository;
import com.petproject.yourpal.proba.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramUserRepository userRepository;
    @Override
    public void save(TelegramUser user) {
        userRepository.save(user);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return userRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return userRepository.findById(chatId);
    }
}
