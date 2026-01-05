package com.example.dashboard2026be;

import com.example.dashboard2026be.dto.CreateArticleRequest;
import com.example.dashboard2026be.model.ArticlePost;
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
    public ResponseEntity<List<ArticlePost>> getAllArticles() {
        return new ResponseEntity<>(service.getAllArticles(), HttpStatus.OK);
    }

    @GetMapping("/jobPost/{postId}")
    public ResponseEntity<ArticlePost> getJob(@PathVariable int postId) {
        ArticlePost job = service.getArticle(postId);
        if (job.getId() > 0) {
            return new ResponseEntity<>(service.getArticle(postId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("article")
    public ResponseEntity<ArticlePost> addArticle(@RequestBody CreateArticleRequest req) {
        return ResponseEntity.ok(service.addArticle(req));
    }

//    @PutMapping("jobPost")
//    public ResponseEntity<ArticlePost> updateJob(@RequestBody ArticlePost jobPost) {
//        service.updateJob(jobPost);
//        return new ResponseEntity<>(service.getArticle(jobPost.getArticleId()), HttpStatus.OK);
//    }
//
//    @DeleteMapping("jobPost/{postId}")
//    public ResponseEntity<String> deleteJob(@PathVariable int postId)
//    {
//        service.deleteJob(postId);
//        return new ResponseEntity<>("Deleted", HttpStatus.OK);
//    }

//    @GetMapping("load")
//    public String loadData() {
//        service.load();
//        return "success";
//    }
}
