package application.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import application.rest.Message;

@ApplicationPath("/dhregistration")
@Path("/")
public class LibertyRestEndpoint extends Application 
{

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response dhregistration(Message msg) 
    {
    	System.out.println("Here we go....." + msg.clientId);
    	System.out.println("Here we go....." + msg.agentId);

    	try
    	{
           URL url = new URL("http://localhost:6379/dhnotification/dhnotification");
		   //URL url = new URL("http://169.44.127.186:6379/dhnotification/dhnotification");
		   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		   conn.setDoOutput(true);
		   conn.setRequestMethod("POST");
		   conn.setRequestProperty("Accept", "application/json");
		   conn.setRequestProperty("Content-Type", "application/json");
		
    	   String input = "{\"clientId\":" + msg.clientId + ",\"agentId\":" + msg.agentId + "}";

    	   System.out.println(input);

		   OutputStream os = conn.getOutputStream();
		   os.write(input.getBytes());
		   os.flush();

		   if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) 
		   {
		       return Response.status(conn.getResponseCode()).build();
           }
		   else
		   {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));		
			
		   		String registrationResults;
		   		registrationResults = br.readLine();
			    System.out.println(registrationResults);
			
    	   		return Response.status(201).entity("Java").build();
		   }	
		   
    	} 
    	catch (MalformedURLException e) 
    	{
    		System.out.println("MalformedURLException");
			e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
			System.out.println("IOException");
			e.printStackTrace();
		}

		return Response.status(500).build();
			
    }
    @GET
    @Path("/")
    public String hello() 
    {
        return "Hello from the REST endpoint!";
    }
    @GET
    @Path("/health")
    public Response healthCheck() 
    {
        return Response.status(200).entity("Health status good").build();
    }

}
