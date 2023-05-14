package com.petproject.yourpal.proba.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tg_user")
public class TelegramUser {

    @Id
    private String chatId;
    private boolean active;
}
