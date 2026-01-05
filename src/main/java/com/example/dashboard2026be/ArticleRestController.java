package com.example.dashboard2026be;

import com.example.dashboard2026be.dto.CreateArticleRequest;
import com.example.dashboard2026be.model.Article;
import com.example.dashboard2026be.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleRestController {

    private final ArticleService service;

    @GetMapping("articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return new ResponseEntity<>(service.getAllArticles(), HttpStatus.OK);
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable int articleId) {
        Article article = service.getArticle(articleId);
        if (article.getId() > 0) {
            return new ResponseEntity<>(service.getArticle(articleId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("article")
    public ResponseEntity<Article> addArticle(@RequestBody CreateArticleRequest req) {
        return ResponseEntity.ok(service.addArticle(req));
    }

    @PutMapping("article")
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
        service.updateArticle(article);
        return new ResponseEntity<>(service.getArticle(Math.toIntExact(article.getId())), HttpStatus.OK);
    }

    @DeleteMapping("article/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable int articleId) {
        service.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }

}
