package com.example.dashboard2026be.service;

import com.example.dashboard2026be.dto.article.ArticleResponse;
import com.example.dashboard2026be.dto.article.CreateArticleRequest;
import com.example.dashboard2026be.dto.article.UpdateArticleRequest;
import com.example.dashboard2026be.model.Article;
import com.example.dashboard2026be.repository.ArticleRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArticleService {
  @Autowired public ArticleRepository repo;

  public List<ArticleResponse> getArticles() {
    return repo.findAll().stream()
        .map(
            a ->
                new ArticleResponse(
                    a.getId(),
                    a.getTitle(),
                    a.getCategory(),
                    a.getText(),
                    a.getDate(),
                    a.getUserId(),
                    a.getUserName(),
                    a.getUserAvatar(),
                    a.getImageData() != null))
        .toList();
  }

  public Article addArticle(CreateArticleRequest req, MultipartFile image) throws IOException {
    Article article = new Article();
    article.setTitle(req.title());
    article.setCategory(req.category());
    article.setText(req.text());
    article.setDate(req.date());
    article.setUserId(req.userId());
    article.setUserName(req.userName());
    article.setUserAvatar(req.userAvatar());

    if (image != null && !image.isEmpty()) {
      article.setImageName(image.getOriginalFilename());
      article.setImageType(image.getContentType());
      article.setImageData(image.getBytes());
    }
    return repo.save(article);
  }

  public Article getArticle(int articleId) {
    return repo.findById(articleId).orElse(new Article());
  }

  public Article updateArticle(UpdateArticleRequest req, MultipartFile image) throws IOException {
    Article article =
        repo.findById(Math.toIntExact(req.id()))
            .orElseThrow(() -> new RuntimeException("Article not found"));

    article.setTitle(req.title());
    article.setCategory(req.category());
    article.setText(req.text());
    article.setDate(req.date());
    article.setUserId(req.userId());
    article.setUserName(req.userName());
    article.setUserAvatar(req.userAvatar());

    if (image != null && !image.isEmpty()) {
      article.setImageName(image.getOriginalFilename());
      article.setImageType(image.getContentType());
      article.setImageData(image.getBytes());
    }
    return repo.save(article);
  }

  public void deleteArticle(int articleId) {
    repo.deleteById(articleId);
  }
}
