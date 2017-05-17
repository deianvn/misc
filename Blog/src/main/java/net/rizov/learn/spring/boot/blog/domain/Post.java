package net.rizov.learn.spring.boot.blog.domain;

import java.util.Date;

public class Post
{
    private String id;

    private String title;

    private String text;

    private Date createdDate;
    
    public Post() {}
    
    public Post(String id, String title, String text, Date createdDate)
    {
	super();
	this.id = id;
	this.title = title;
	this.text = text;
	this.createdDate = createdDate;
    }

    public String getId()
    {
	return id;
    }

    public void setId(String id)
    {
	this.id = id;
    }

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	this.title = title;
    }

    public String getText()
    {
	return text;
    }

    public void setText(String text)
    {
	this.text = text;
    }

    public Date getCreatedDate()
    {
	return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
	this.createdDate = createdDate;
    }

}
