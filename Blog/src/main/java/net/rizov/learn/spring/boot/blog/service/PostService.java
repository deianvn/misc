package net.rizov.learn.spring.boot.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.rizov.learn.spring.boot.blog.domain.Author;
import net.rizov.learn.spring.boot.blog.domain.Post;
import net.rizov.learn.spring.boot.blog.domain.PostNotFoundException;

@Service
public class PostService
{
    private RandomStringService randomStringService;

    private DummyDateService dummyDateService;
    
    @Autowired
    public PostService(RandomStringService randomStringService, DummyDateService dummyDateService)
    {
	super();
	this.randomStringService = randomStringService;
	this.dummyDateService = dummyDateService;
    }

    public List<Post> getAllPosts()
    {
	ArrayList<Post> list = new ArrayList<>();

	for (int i = 0; i < 10; i++)
	{
	    Post post = new Post(randomStringService.nextString(100), randomStringService.nextString(1200),
		    new Author("", "", "", ""));
	    
	    post.setId((long) i);
	    post.setDateCreated(dummyDateService.createDate(i));
	    list.add(post);
	}

	return list;
    }

    public Post getPost(Long id) throws PostNotFoundException
    {
	if ("0".equals(id))
	{
	    Post post = new Post(randomStringService.nextString(100), randomStringService.nextString(1200),
		    new Author("", "", "", ""));
	    
	    post.setId(id);
	    post.setDateCreated(dummyDateService.createDate(id.intValue()));
	    
	    return post;
	}

	throw new PostNotFoundException(id);
    }

}
