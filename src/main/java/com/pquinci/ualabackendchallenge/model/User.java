package com.pquinci.ualabackendchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Table(name="user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String name;
    private String lastname;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Tweet> tweets=new ArrayList<>();
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> follows=new ArrayList<>();
}
