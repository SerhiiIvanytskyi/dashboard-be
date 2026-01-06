package com.example.dashboard2026be.dto;

public record UpdateArticleRequest(
    Long id,
    String title,
    String category,
    String text,
    String date,
    String userId,
    String userName,
    String userAvatar
) {}
