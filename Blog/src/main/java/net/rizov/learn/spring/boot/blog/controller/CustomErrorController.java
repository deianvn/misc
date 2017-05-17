package net.rizov.learn.spring.boot.blog.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class CustomErrorController implements ErrorController
{

    private ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes)
    {
	super();
	this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath()
    {
	return "error/generalError";
    }

    @RequestMapping("/404")
    public String error404(HttpServletRequest request, Model model)
    {
	populateErrorAttributes(request, model);
	return "error/page404";
    }

    private void populateErrorAttributes(HttpServletRequest request, Model model)
    {
	RequestAttributes requestAttributes = new ServletRequestAttributes(request);
	Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(requestAttributes, true);
	errorAttributes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
