package net.rizov.learn.spring.boot.blog.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfiguration
{
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer()
    {
	return (container ->
	{
	    ErrorPage custom404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
	    container.addErrorPages(custom404Page);
	});
    }
}
