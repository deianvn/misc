package net.rizov.learn.spring.boot.blog.domain;

public class PostNotFoundException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 8241735792874056813L;

    private String postId;

    public PostNotFoundException(String postId)
    {
	super();
	this.postId = postId;
    }
    
    public String getPostId()
    {
	return postId;
    }
    
}
