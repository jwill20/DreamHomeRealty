package com.ibm.sae;

import com.netflix.zuul.ZuulFilter;

import java.util.Map;

import org.springframework.stereotype.Component;
import com.netflix.zuul.context.RequestContext;

@Component
public class MetricsCollectionFilter extends ZuulFilter 
{
		@Override
	    public String filterType() 
		{
	        return "pre";
	    }

	    @Override
	    public int filterOrder() 
	    {
	        return 1;
	    }

	    public boolean shouldFilter() 
	    {
	        return true;
	    }
	    public Object run() 
	    {
	    	System.out.println("*********************************");
	    	System.out.println("We are in MetricsCollectionFilter");
	    	System.out.println("*********************************");
	    	System.out.println("Here are the incoming headers    ");
	    	System.out.println("*********************************");
	    	RequestContext ctx = RequestContext.getCurrentContext();
	    	Map<String, String[]> headers = ctx.getRequest().getParameterMap();
	    	System.out.println("Headers = " + ctx.getRequest().getHeaderNames().toString());
	    	System.out.println("Headers = " + ctx.getRequest().getHeader("Content-Type"));
	    	
	    	System.out.println("Number of headers = " + headers.size());
	    	for (int a = 0; a > headers.size(); a++)
	    	{
	    		System.out.println(headers.get(a));
	    	}
	    	return null;
	    }	
}









