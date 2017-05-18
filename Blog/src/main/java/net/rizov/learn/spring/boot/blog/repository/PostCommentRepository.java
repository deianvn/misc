package net.rizov.learn.spring.boot.blog.repository;

import org.springframework.data.repository.CrudRepository;

import net.rizov.learn.spring.boot.blog.domain.PostComment;

public interface PostCommentRepository extends CrudRepository<PostComment, Long>
{

}
