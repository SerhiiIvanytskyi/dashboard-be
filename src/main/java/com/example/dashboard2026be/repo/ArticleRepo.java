package com.example.dashboard2026be.repo;

import com.example.dashboard2026be.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {


}
