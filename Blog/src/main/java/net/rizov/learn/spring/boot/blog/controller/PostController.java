package net.rizov.learn.spring.boot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.rizov.learn.spring.boot.blog.domain.Post;
import net.rizov.learn.spring.boot.blog.domain.PostNotFoundException;
import net.rizov.learn.spring.boot.blog.service.PostService;

@Controller
public class PostController
{
    @Autowired
    private PostService postService;

    @RequestMapping("/posts")
    public String showPosts(Model model)
    {
	model.addAttribute("posts", postService.getAllPosts());
	return "views/posts";
    }

    @RequestMapping("/posts/{postId}")
    public String showPost(@PathVariable(value = "postId") Long postId, Model model)
	    throws PostNotFoundException
    {
	Post post = postService.getPost(postId);
	model.addAttribute("post", post);
	return "views/post";
    }
    
    @Secured("ROLE_AUTHOR")
    @RequestMapping("/posts/new")
    public String newPost()
    {
	return "views/postNew";
    }

    @Secured("ROLE_AUTHOR")
    @RequestMapping("/posts/create")
    public String createPost()
    {
	return "views/posts";
    }
}
