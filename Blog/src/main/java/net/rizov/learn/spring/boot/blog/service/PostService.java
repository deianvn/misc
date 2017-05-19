package net.rizov.learn.spring.boot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.rizov.learn.spring.boot.blog.domain.Post;
import net.rizov.learn.spring.boot.blog.domain.PostNotFoundException;
import net.rizov.learn.spring.boot.blog.repository.PostRepository;

@Service
public class PostService
{
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository)
    {
	super();
	this.postRepository = postRepository;
    }

    public Iterable<Post> getAllPosts()
    {
	return postRepository.findAll();
    }

    public Post getPost(Long id) throws PostNotFoundException
    {
	Post post = postRepository.findOne(id);

	if (post != null)
	{
	    return post;
	}
	
	throw new PostNotFoundException(id);
    }

}
