package net.rizov.learn.spring.boot.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.rizov.learn.spring.boot.blog.domain.Post;

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

}
