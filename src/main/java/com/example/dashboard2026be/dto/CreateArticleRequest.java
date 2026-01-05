package com.example.dashboard2026be.dto;

public record CreateArticleRequest(
    String title,
    String category,
    String text,
    String date,
    String imageUrl,
    String userId,
    String userName,
    String userAvatar
) {}
