package com.portfolio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "portfolio")
@Getter
@Setter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "idportfolio")
    private Long id;
    @Column(length = 255)
    private String description;
    @Column(name = "image_url", length = 255)
    private String imageUrl;
    @Column(name = "twitter_user_name", length = 255)
    private String twitterUserName;
    @Column(name = "title", length = 255)
    private String title;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "experience", length = 255)
    private String experience;
    @Column(name = "experience_summary", length = 255)
    private String experienceSummary;
    @Column(name = "image_path", length = 255)
    private String imagePath;
    @Column(name = "last_names", length = 255)
    private String lastName;
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "phone", length = 255)
    private String phone;
    @Column(name = "zip_code", length = 255)
    private String zipCode;

}
