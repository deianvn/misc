package net.rizov.learn.spring.boot.blog.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class PostComment
{
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(columnDefinition= "TEXT")
    private String text;
    
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP")
    private Date dateCreated;
    
    @ManyToOne
    private Post post;
    
    @ManyToOne
    private Author author;
    
    public PostComment() {}
    
    public PostComment(String text, Post post, Author author)
    {
	super();
	this.text = text;
	this.post = post;
	this.author = author;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Post getPost()
    {
        return post;
    }

    public void setPost(Post post)
    {
        this.post = post;
    }

    public Author getAuthor()
    {
        return author;
    }

    public void setAuthor(Author author)
    {
        this.author = author;
    }
    
}
