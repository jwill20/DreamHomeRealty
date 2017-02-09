package com.ibm.sae;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
@Component
@EnableDiscoveryClient
public class DhregistrationController 
{
	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/dhregistration",
	        method = RequestMethod.POST) 
	public ResponseEntity<Client> dhregistration(@RequestBody Client client) 
	{
		System.out.println("dhregistration ==> Begin");
		
		System.out.println("Client data = " + client.getAddress());
		
		System.out.println("Before post");
		
		HttpEntity<Client> myClientEntity = 
				new HttpEntity<>(new Client(client.getAgentId(), client.getClientId(), client.getFirstName(), 
						                    client.getLastName(), client.getAddress(), client.getCity(), client.getState()));
		
		// Exchange seems to need a ResponseEntity wrapper
		ResponseEntity<Client> returnedData = restTemplate.exchange("http://dhnotification/dhnotification", HttpMethod.POST, myClientEntity, 
				Client.class);
		
		System.out.println("back in reg - body looks like ..." + returnedData.getBody().getAddress());
		System.out.println("back in reg - status code looks like ..." + returnedData.getStatusCode());
		
		return new ResponseEntity<Client>(client, HttpStatus.CREATED);
	
	}
	/*********************************************************/
	/* client                                                */
	/*********************************************************/
	@RequestMapping(value = "/client",
	        method = RequestMethod.POST) 
	public Client client(@RequestBody Client client) 
	{
		System.out.println("in client ==> Begin");
		
		System.out.println("Client data = " + client.getAddress());
		
		System.out.println("Before post");
		Client myclient = new Client();
		myclient.setAddress(client.getAddress());
		myclient.setAgentId(123);
		myclient.setCity("aaa");
		myclient.setClientId(123);
		myclient.setFirstName("Bob");
		myclient.setLastName("Jones");
		myclient.setState("NJ");
		
		RestTemplate restTemplate = new RestTemplate();
	    
		//10.10.147.130:60051
		//10.240.21.209
		//dhnotification.cfapps.io
		System.out.println("Using => 10.240.21.209:60051");
		Client returnedData = restTemplate.postForObject("https://10.240.21.209:60051/dhnotification", HttpMethod.POST, Client.class, myclient);
		
		System.out.println("After post");
		System.out.println("back in reg - body looks like ..." + returnedData.getState());
		System.out.println("back in reg - status code looks like ..." + returnedData.getCity());
		
		return new Client();
	
	}
	/********************************************************************/
	/*  Call the other method in dhnotification                         */
	/********************************************************************/
	@RequestMapping(value = "/otherCall",
	        method = RequestMethod.POST) 
	public ResponseEntity<Client> dhhealth(@RequestBody Client client) 
	{
		System.out.println("otherCall ==> Begin");
		
		System.out.println("Before post");
		
		ResponseEntity<String> returnedData = restTemplate.exchange("http://dhnotification/mshealth", HttpMethod.GET, null, String.class, "abcdef");
		
		System.out.println("After post");
		
		System.out.println("back in reg - body looks like ..." + returnedData.getBody());
		System.out.println("back in reg - status code looks like ..." + returnedData.getStatusCode());
		
		return new ResponseEntity<Client>(client, HttpStatus.CREATED);
	
	}
	/*************************************************************/
	/* Use DiscoveryClient to interact with Ribbon/Eureka        */
	/*************************************************************/
	@RequestMapping(value = "/dhregistrationShowDiscoveryClient",
	        method = RequestMethod.GET, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> showDiscoveryClient() 
	{
		System.out.println("dhregistrationShowDiscoveryClient ==> Begin");
		
		List<ServiceInstance> ii = discoveryClient.getInstances("dhnotification");
		
		String uri = null;
		for (ServiceInstance info : ii) 
		{ 
		   	System.out.println("Service ID = " + info.getServiceId());
			System.out.println("Host name = " + info.getHost());
			System.out.println("Port = " + info.getPort());
			System.out.println("URI = " + info.getUri());
      }   
		
	return new ResponseEntity<String>(HttpStatus.OK);
	
	}
	/*************************************************************/
	/* Use DiscoveryClient to interact with Ribbon/Eureka        */
	/*************************************************************/
	@RequestMapping(value = "/test",
	        method = RequestMethod.GET) 
	public String test() 
	{
		
		System.out.println("test ==> Begin");
		return "Life is good";
	}
}
