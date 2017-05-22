package net.rizov.learn.spring.boot.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@Profile("production")
public class SecurityConfiguration extends GlobalWebSecurityConfiguration
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
	super.configure(http);
    }
}
