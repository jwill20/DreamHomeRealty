package com.ibm.sae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

@RestController
@Component
@RefreshScope
@EnableDiscoveryClient
public class Dhclientcontroller 
{
		/**************************************************************************************/
		/* Pull in the parameters from the Git repository yml file.                           */
		/**************************************************************************************/
		//@Value ("${refreshProperty}")
		//private String refreshProperty;

		@Value ("${dhDatabaseIp}")
		private String databaseIp;

		@Value ("${dhDatabasePort}")
		private int databasePort;
		
		/**************************************************************************************/
		/* dhnotification writes a notification document to the dhNotification collection.    */
		/**************************************************************************************/
	    @RequestMapping(value = "/dhclient",
	    		        method = RequestMethod.POST)
	    public ResponseEntity<Client> dhclient(@RequestBody Client client) 
	    {
	    	System.out.println("dhclient ==> Begin");
	    	System.out.println("dhclient ==> Client values = " + client.getAddress());
	    	
	    	MongoCredential credential = MongoCredential.createCredential("", "", "".toCharArray());
			
		MongoClient mongoClient = new MongoClient(new ServerAddress(databaseIp, databasePort), Arrays.asList(credential));
			
	    	//MongoClient mongoClient = new MongoClient(databaseIp, databasePort);
	    	
	    	@SuppressWarnings("deprecation")
	    	DB db = mongoClient.getDB("");
	    	
	    	DBCollection countersColl = db.getCollection("dhCounterColl");
	    	DBCollection clientColl = db.getCollection("dhClientColl");
	    
	    	DBObject dbo = new BasicDBObject();
	    	
	    	/****************************************************************************************************/
	    	/* Get the next value for the clientId from the counters collection.                                */
	    	/****************************************************************************************************/
	    	dbo =
	    	countersColl.findAndModify(
	    			new BasicDBObject("_id","clientId"),			  	    // Query to find the counter document
	    			new BasicDBObject("seq",1),								// Subset of fields to return
	    			new BasicDBObject("_id",1),								// Sort if multiple documents returned
	    			false,													// Remove document?
	    			new BasicDBObject("$inc", new BasicDBObject("seq", 1)), // Update operation to perform
	    			true,													// Return the new value?
	    			false);													// Create new document if none found by the query
	    	
	    	List<DBObject> suggestedProperties = new ArrayList<DBObject>();
			
			DBObject clientDoc = new BasicDBObject("clientId", dbo.get("seq"))
			    		.append("clientName", new BasicDBObject("clientFN", client.getFirstName())
						.append("clientLN", client.getLastName()))
			    		.append("clientInfo", new BasicDBObject("address", client.getAddress())
						.append("city", client.getCity())
						.append("state", client.getState())
						.append("zip", client.getZip()))
						.append("agentId", client.getAgentId())
						.append("suggestedProperties", suggestedProperties)
						.append("minAskingPrice", 500000.00)
						.append("maxAskingPrice", 700000.00);
			
	    	WriteResult wr = clientColl.insert(clientDoc, WriteConcern.ACKNOWLEDGED);
	    	
	    	return new ResponseEntity<Client>(client,HttpStatus.CREATED);
	    }


}
