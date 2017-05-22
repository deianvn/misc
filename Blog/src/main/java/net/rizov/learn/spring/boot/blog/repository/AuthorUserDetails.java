package net.rizov.learn.spring.boot.blog.repository;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.rizov.learn.spring.boot.blog.domain.Author;

public class AuthorUserDetails implements UserDetails
{
    /**
     * 
     */
    private static final long serialVersionUID = 5389850768808016206L;
    
    private Author author;
    
    public AuthorUserDetails(Author author)
    {
	super();
	this.author = author;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
	Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	authorities.add(new SimpleGrantedAuthority("ROLE_AUTHOR"));
	
	return authorities;
    }

    @Override
    public String getPassword()
    {
	return author.getPassword();
    }

    @Override
    public String getUsername()
    {
	return author.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
	return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
	return true;
    }

    @Override
    public boolean isEnabled()
    {
	return true;
    }

}
