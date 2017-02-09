package com.ibm.sae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class DhApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(DhApplication.class, args);
	}

}
