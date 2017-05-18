package net.rizov.learn.spring.boot.blog.repository;

import org.springframework.data.repository.CrudRepository;

import net.rizov.learn.spring.boot.blog.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long> 
{

}
