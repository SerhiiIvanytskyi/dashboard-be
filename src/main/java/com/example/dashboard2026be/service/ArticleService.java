package com.example.dashboard2026be.service;

import com.example.dashboard2026be.dto.CreateArticleRequest;
import com.example.dashboard2026be.model.ArticlePost;
import com.example.dashboard2026be.repo.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {
    @Autowired
    public ArticleRepo repo;

    //method to return all articles
    public List<ArticlePost> getAllArticles() {
        return repo.findAll();
    }

    // method to add a new article
    public ArticlePost addArticle(CreateArticleRequest req) {
        ArticlePost article = new ArticlePost(
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

    //method to get job by id
    public ArticlePost getArticle(int postId) {
        return repo.findById(postId).orElse(new ArticlePost());
    }

    //method to update job with job post object
    public void updateJob(ArticlePost jobPost) {
        repo.save(jobPost);
    }

    //method to delete job post by id
    public void deleteJob(int postId) {
        repo.deleteById(postId);
    }

//    public void load() {
//        // arrayList to store store JobPost objects
//        List<ArticlePost> jobs =
//                new ArrayList<>(List.of(
//                        new ArticlePost(1, "Software Engineer", "Exciting opportunity for a skilled software engineer.", 3, List.of("Java", "Spring", "SQL")),
//                        new ArticlePost(2, "Data Scientist", "Join our data science team and work on cutting-edge projects.", 5, List.of("Python", "Machine Learning", "TensorFlow")),
//                        new ArticlePost(3, "Frontend Developer", "Create amazing user interfaces with our talented frontend team.", 2, List.of("JavaScript", "React", "CSS")),
//                        new ArticlePost(4, "Network Engineer", "Design and maintain our robust network infrastructure.", 4, List.of("Cisco", "Routing", "Firewalls")),
//                        new ArticlePost(5, "UX Designer", "Shape the user experience with your creative design skills.", 3, List.of("UI/UX Design", "Adobe XD", "Prototyping"))
//
//                ));
//
//        repo.saveAll(jobs);
//
//    }
}
