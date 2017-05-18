package net.rizov.learn.spring.boot.blog.repository;

import org.springframework.data.repository.CrudRepository;

import net.rizov.learn.spring.boot.blog.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long>
{

}
