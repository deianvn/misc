package net.rizov.learn.spring.boot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.rizov.learn.spring.boot.blog.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController
{
    @Autowired
    private PostService postService;
    
    @RequestMapping("/")
    public String showPosts(Model model)
    {
	model.addAttribute("posts", postService.getAllPosts());
	return "views/posts";
    }
}
