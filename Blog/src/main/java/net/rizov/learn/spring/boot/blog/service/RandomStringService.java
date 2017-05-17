package net.rizov.learn.spring.boot.blog.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomStringService
{

    private static final String AB = "0123456 789ABCDEFG HIJKLMNOPQRS TUVWXY Zabcde fg hijklmn opqrst uvwxyz";
    
    private static Random random = new Random();

    public String nextString(int length)
    {
       StringBuilder sb = new StringBuilder(length);
       
       for(int i = 0; i < length; i++)
       {
	   sb.append(AB.charAt(random.nextInt(AB.length())));
       }
       
       return sb.toString();
    }
    
}
