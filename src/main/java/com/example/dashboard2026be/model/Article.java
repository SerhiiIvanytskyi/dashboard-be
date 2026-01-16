package com.example.dashboard2026be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String text;
    private String date;
    private String userId;
    private String userName;
    private String userAvatar;
    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;
}
