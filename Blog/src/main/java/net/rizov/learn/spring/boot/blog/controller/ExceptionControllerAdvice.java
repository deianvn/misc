package net.rizov.learn.spring.boot.blog.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.rizov.learn.spring.boot.blog.domain.PostNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice
{
    @ExceptionHandler(PostNotFoundException.class)
    public String postNotFound(PostNotFoundException exception, Model model)
    {
	model.addAttribute("postId", exception.getPostId());
	return "error/post404";
    }
}
