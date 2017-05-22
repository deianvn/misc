package net.rizov.learn.spring.boot.blog.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class GlobalWebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
	http.
        	authorizeRequests().
        		antMatchers("/").permitAll().
        		antMatchers("/posts").permitAll().
        		antMatchers("/posts/*").permitAll().
        		anyRequest().authenticated().
        		and().
        		formLogin().
        		and().
        		logout();
    }
}
