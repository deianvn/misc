package net.rizov.learn.spring.boot.blog.service;

import net.rizov.learn.spring.boot.blog.domain.Author;

public interface AuthorService
{
    Author findByEmail(String email);
}
