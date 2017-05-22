package net.rizov.learn.spring.boot.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.rizov.learn.spring.boot.blog.domain.Author;
import net.rizov.learn.spring.boot.blog.repository.AuthorRepository;
import net.rizov.learn.spring.boot.blog.repository.AuthorUserDetails;

@Service
public class AuthorServiceImpl implements AuthorService, UserDetailsService
{
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository)
    {
	super();
	this.authorRepository = authorRepository;
    }
    
    @Override
    public Author findByEmail(String email)
    {
        return authorRepository.findByEmail(email);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
	Author author = findByEmail(username);
	
	if (author == null)
	{
	    throw new UsernameNotFoundException(username);
	}
	
	return new AuthorUserDetails(author);
    }
    
}
