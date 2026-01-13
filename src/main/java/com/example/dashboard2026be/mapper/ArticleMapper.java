package com.example.dashboard2026be.mapper;

import com.example.dashboard2026be.dto.article.ArticleResponse;
import com.example.dashboard2026be.model.Article;

public class ArticleMapper {
  private ArticleMapper() {}

  public static ArticleResponse toResponse(Article article) {
    return new ArticleResponse(
        article.getId(),
        article.getTitle(),
        article.getCategory(),
        article.getText(),
        article.getDate(),
        article.getUserId(),
        article.getUserName(),
        article.getUserAvatar(),
        article.getImageData() != null);
  }
}
