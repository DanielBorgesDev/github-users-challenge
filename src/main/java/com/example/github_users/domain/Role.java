package com.example.github_users.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "roles") // 👈 sempre no plural para evitar conflito
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
