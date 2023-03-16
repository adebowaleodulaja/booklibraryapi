package com.wizer.booklibrary.model;

import javax.persistence.Column;

public class Author {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "gender", nullable = false)
    private String gender;

    public Author() {
    }

}