package net.rizov.learn.spring.boot.blog.domain;

public class PostNotFoundException extends Exception
{

    /**
     * 
     */
    private static final long serialVersionUID = 8241735792874056813L;

    private Long postId;

    public PostNotFoundException(Long postId)
    {
	super();
	this.postId = postId;
    }
    
    public Long getPostId()
    {
	return postId;
    }
    
}
