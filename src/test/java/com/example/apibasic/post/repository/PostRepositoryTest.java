package com.example.apibasic.post.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryTest {

    @Test
    void abc() {
        PostRepository repository = new PostRepository();
        repository.findAll();
    }

}