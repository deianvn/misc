package net.rizov.learn.spring.boot.blog.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DummyDateService
{
    public Date createDate(int days)
    {
	LocalDateTime ldt = LocalDateTime.now().minusDays(days);
	ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
	return Date.from(zdt.toInstant());
    }
}
