package com.example.dashboard2026be.controller;

import com.example.dashboard2026be.dto.article.ArticleResponse;
import com.example.dashboard2026be.dto.article.CreateArticleRequest;
import com.example.dashboard2026be.dto.article.UpdateArticleRequest;
import com.example.dashboard2026be.mapper.ArticleMapper;
import com.example.dashboard2026be.model.Article;
import com.example.dashboard2026be.service.ArticleService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

  private final ArticleService service;

  @GetMapping("articles")
  public ResponseEntity<List<ArticleResponse>> getArticles() {
    return new ResponseEntity<>(service.getArticles(), HttpStatus.OK);
  }

  @GetMapping("/article/{articleId}")
  public ResponseEntity<ArticleResponse> getArticle(@PathVariable int articleId) {
    Article article = service.getArticle(articleId);
    ArticleResponse response = ArticleMapper.toResponse(article);

    return ResponseEntity.ok(response);
  }

  @PostMapping("article")
  public ResponseEntity<?> addArticle(
      @RequestPart CreateArticleRequest req,
      @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
    Article savedArticle = null;
    try {
      savedArticle = service.addArticle(req, imageFile);
      return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    } catch (IOException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping(value = "/article", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> updateArticle(
      @RequestPart UpdateArticleRequest req,
      @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
    try {
      Article updated = service.updateArticle(req, imageFile);
      return ResponseEntity.ok(updated);
    } catch (IOException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("article/{articleId}")
  public ResponseEntity<String> deleteArticle(@PathVariable int articleId) {
    service.deleteArticle(articleId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/article/{articleId}/image")
  public ResponseEntity<byte[]> getArticleImage(@PathVariable int articleId) {
    return service
        .getArticleImage(articleId)
        .map(
            image ->
                ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                    .body(image))
        .orElse(ResponseEntity.notFound().build());
  }
}
