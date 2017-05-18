package net.rizov.learn.spring.boot.blog.service;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import net.rizov.learn.spring.boot.blog.domain.Author;
import net.rizov.learn.spring.boot.blog.domain.Post;
import net.rizov.learn.spring.boot.blog.domain.PostComment;
import net.rizov.learn.spring.boot.blog.repository.AuthorRepository;
import net.rizov.learn.spring.boot.blog.repository.PostCommentRepository;
import net.rizov.learn.spring.boot.blog.repository.PostRepository;

@Service
@Profile("development")
public class DemoDataLoaderService
{
    private AuthorRepository authorRepository;

    private PostRepository postRepository;

    private PostCommentRepository postCommentRepository;

    private RandomStringService randomStringService;
    
    private DummyDateService dummyDateService;

    private Random random = new Random();
    
    @Autowired
    public DemoDataLoaderService(AuthorRepository authorRepository, PostRepository postRepository,
	    PostCommentRepository postCommentRepository, RandomStringService randomStringService,
	    DummyDateService dummyDateService)
    {
	super();
	this.authorRepository = authorRepository;
	this.postRepository = postRepository;
	this.postCommentRepository = postCommentRepository;
	this.randomStringService = randomStringService;
	this.dummyDateService = dummyDateService;
    }

    @PostConstruct
    public void loadData()
    {
	for (int i = 0; i < 100; i++)
	{
	    Author author = new Author(createRandomName(), createRandomName(), createRandomEmail(), "demo");
	    authorRepository.save(author);
	    
	    for (int j = 0; j < random.nextInt(10); j++)
	    {
		Post post = new Post(createRandomLine(), createRandomText(), author);
		post.setDateCreated(dummyDateService.createDate(random.nextInt(1000)));
		postRepository.save(post);
		
		for (int k = 0; k < random.nextInt(20); k++)
		{
		    PostComment comment = new PostComment(createRandomShortText(), post, author);
		    comment.setDateCreated(dummyDateService.createDate(random.nextInt(1000))); //may be inconsistent with post
		    postCommentRepository.save(comment);
		}
	    }
	}
    }

    private String createRandomName()
    {
	StringBuilder sb = new StringBuilder(randomStringService.nextString(random.nextInt(7) + 4));
	sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
	return sb.toString();
    }

    private String createRandomEmail()
    {
	return
		randomStringService.nextString(random.nextInt(7) + 4) + "@" +
		randomStringService.nextString(random.nextInt(7) + 4) + ".com";
    }
    
    private String createRandomLine()
    {
	StringBuilder sb = new StringBuilder();
	
	for (int i = 0; i < 3 + random.nextInt(12); i++)
	{
	    sb.append(randomStringService.nextString(1 + random.nextInt(10)) + " ");
	}
	
	sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
	
	return sb.toString().trim();
    }
    
    private String createRandomShortText()
    {
	return createRandomText(1, 5);
    }
    
    private String createRandomText()
    {
	return createRandomText(10, 30);
    }
    
    private String createRandomText(int minLines, int maxLines)
    {
	StringBuilder sb = new StringBuilder();
	
	for (int i = 0; i < minLines + random.nextInt(maxLines - minLines); i++)
	{
	    sb.append(createRandomLine() + ". ");
	}
	
	return sb.toString();
    }
    
}
