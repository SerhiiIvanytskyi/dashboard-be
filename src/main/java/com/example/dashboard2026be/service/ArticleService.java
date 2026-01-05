package com.example.dashboard2026be.service;

import com.example.dashboard2026be.dto.CreateArticleRequest;
import com.example.dashboard2026be.model.Article;
import com.example.dashboard2026be.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    public ArticleRepo repo;

    //method to return all articles
    public List<Article> getAllArticles() {
        return repo.findAll();
    }

    // method to add a new article
    public Article addArticle(CreateArticleRequest req) {
        Article article = new Article(
            null,
            req.title(),
            req.category(),
            req.text(),
            req.date(),
            req.imageUrl(),
            req.userId(),
            req.userName(),
            req.userAvatar()
        );
        return repo.save(article);
    }

    //method to get article by id
    public Article getArticle(int articleId) {
        return repo.findById(articleId).orElse(new Article());
    }

    //method to update article
    public void updateArticle(Article article) {
        repo.save(article);
    }

    //method to delete article by id
    public void deleteArticle(int articleId) {
        repo.deleteById(articleId);
    }

}
