package net.rizov.learn.spring.boot.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.rizov.learn.spring.boot.blog.domain.Post;
import net.rizov.learn.spring.boot.blog.domain.PostNotFoundException;

@Service
public class PostService
{
    @Autowired
    private RandomStringService randomStringService;

    @Autowired
    private DummyDateService dummyDateService;

    public List<Post> getAllPosts()
    {
	ArrayList<Post> list = new ArrayList<>();

	for (int i = 0; i < 10; i++)
	{
	    list.add(new Post(String.valueOf(i), randomStringService.nextString(100),
		    randomStringService.nextString(1200), dummyDateService.createDate(i)));
	}
	
	return list;
    }
    
    public Post getPost(String id) throws PostNotFoundException
    {
	if ("0".equals(id))
	{
	    return new Post(id, randomStringService.nextString(100),
		    randomStringService.nextString(1200), dummyDateService.createDate(0));
	}
	
	throw new PostNotFoundException(id);
    }

}
