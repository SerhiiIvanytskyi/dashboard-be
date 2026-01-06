package com.example.dashboard2026be.service;

import com.example.dashboard2026be.dto.ArticleResponse;
import com.example.dashboard2026be.dto.CreateArticleRequest;
import com.example.dashboard2026be.dto.UpdateArticleRequest;
import com.example.dashboard2026be.model.Article;
import com.example.dashboard2026be.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    public ArticleRepo repo;

    //method to return all articles
    public List<ArticleResponse> getArticles() {
        return repo.findAll().stream()
            .map(a -> new ArticleResponse(
                a.getId(),
                a.getTitle(),
                a.getCategory(),
                a.getText(),
                a.getDate(),
                a.getUserId(),
                a.getUserName(),
                a.getUserAvatar(),
                a.getImageData() != null
            ))
            .toList();
    }

    // method to add a new article
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

    //method to get article by id
    public Article getArticle(int articleId) {
        return repo.findById(articleId).orElse(new Article());
    }

    //method to update article
    public Article updateArticle(UpdateArticleRequest req, MultipartFile image) throws IOException {
        Article article = repo.findById(Math.toIntExact(req.id()))
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

    //method to delete article by id
    public void deleteArticle(int articleId) {
        repo.deleteById(articleId);
    }

}
