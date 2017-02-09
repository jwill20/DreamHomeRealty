package com.ibm.sae;

import com.mongodb.*;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@Component
@EnableDiscoveryClient
//@EnableEurekaClient
public class DhnotificationController 
{
	/**************************************************************************************/
	/* Pull in the parameters from the Git repository yml file.                           */
	/**************************************************************************************/
	//@Value ("${refreshProperty}")
	//private String refreshProperty;

	@Value ("${vcap.application.instance_id}")
	private String instanceId;
	
	@Value ("${dhDatabaseIp}")
	private String databaseIp;

	@Value ("${dhDatabasePort}")
	private int databasePort;

	/**************************************************************************************/
	/* dhnotification writes a notification document to the dhNotification collection.    */
	/**************************************************************************************/
    @RequestMapping(value = "/dhnotification",
    		        method = RequestMethod.POST)
    public ResponseEntity<Client> dhnotification(@RequestBody Client client) 
    {
    	System.out.println("dhnotification ==> Begin");
    	System.out.println("dhnotification ==> Client values = " + client.getAddress());
    	System.out.println("dhnotification ==> Instance = " + instanceId);
    	
    	
    	//vcap.application.instance_id
    	
    	MongoCredential credential = MongoCredential.createCredential("cpoUser", "dreamHomePivotalDB", "enitlavo908#".toCharArray());
		
		MongoClient mongoClient = new MongoClient(new ServerAddress(databaseIp, databasePort), Arrays.asList(credential));
		
		//MongoClient mongoClient = new MongoClient(databaseIp, databasePort);
    	
    	@SuppressWarnings("deprecation")
    	DB db = mongoClient.getDB("dreamHomePivotalDB");
    	
    	DBCollection countersColl = db.getCollection("dhCounterColl");
    	DBCollection notificationColl = db.getCollection("dhNotificationColl");
    
    	DBObject dbo = new BasicDBObject();
    	
    	/****************************************************************************************************/
    	/* Get the next value for the notificationId from the counters collection.                          */
    	/****************************************************************************************************/
    	dbo =
    	countersColl.findAndModify(
    			new BasicDBObject("_id","notificationId"),				// Query to find the counter document
    			new BasicDBObject("seq",1),								// Subset of fields to return
    			new BasicDBObject("_id",1),								// Sort if multiple documents returned
    			false,													// Remove document?
    			new BasicDBObject("$inc", new BasicDBObject("seq", 1)), // Update operation to perform
    			true,													// Return the new value?
    			false);													// Create new document if none found by the query
    	
    	BasicDBObject notificationDocument = new BasicDBObject("notificationId",dbo.get("seq")).
    			append("clientId", client.getClientId()).
    			append("agentId", client.getAgentId());
    	
    	WriteResult wr = notificationColl.insert(notificationDocument, WriteConcern.ACKNOWLEDGED);
    	
    	return new ResponseEntity<Client>(client,HttpStatus.CREATED);
    }

    /**************************************************************************************/
	/* dhnotification writes a notification document to the dhNotification collection.    */
	/**************************************************************************************/
    @RequestMapping(value = "/client",
    		        method = RequestMethod.POST)
    public Client client(@RequestBody Client client) 
    {
    	System.out.println("In Notificationn - Just Client ==> Begin");
    	System.out.println("Incoming client ==> " + client.getAgentId());
        Client c = new Client();
    	c.setAddress("123 Main");
    	return c;
    }
	/*************************************************************/
	/* Health check for dhnotification                           */
	/*************************************************************/
    @RequestMapping(value = "/mshealth",
    		        method = RequestMethod.GET)
    		        //produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> healthCheck() 
    {	
    	System.out.println("dhnotification:mshealth ==> Begin");
    	//System.out.println("dhnotification:mshealth ==> Refresh Property = " + refreshProperty);
    	return new ResponseEntity<String>("dhnotification v1.0 - Health status good", HttpStatus.OK);
    }
}    
