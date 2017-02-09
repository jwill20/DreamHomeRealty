package com.ibm.sae;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class Tester {

	public static void main(String[] args) {
		
		//1,2,3,4,6,7,8,9
		
		
		
		System.out.println("Tester:main ==> Begin");
		MongoClient mongoClient = new MongoClient("169.45.196.58",27017);
		//@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("dreamHomeAmalgam8DB");
		DBCollection notificationColl = db.getCollection("dhNotificationColl");
		
		DBCollection countersColl = db.getCollection("counters");
    	//System.out.println(coll.findOne());
    	DBObject dbo = new BasicDBObject();
    	
    	String clientId = "8929";
    	String agentId  = "8939";

    	/****************************************************************************************************/
    	/* Get the next value for the notificationId from the counters collection.                          */
    	/****************************************************************************************************/
    	dbo =
    	countersColl.findAndModify(
    			new BasicDBObject("_id","notificationId"),				// Query to find the counter document
    			new BasicDBObject("seq",1),								// Subset of fields to return
    			new BasicDBObject("_id",1),								// Sort if multiple documents returned
    			false,													// Remove document?
    			new BasicDBObject("$inc", new BasicDBObject("seq", 1)), // Update operation to preform
    			true,													// Return the new value?
    			false);													// Create new document if none found by the query
    	
    	System.out.println(dbo.get("seq"));
    			
    	BasicDBObject notificationDocument = new BasicDBObject("notificationId",dbo.get("seq")).
    			append("clientId", new Integer(clientId)).
    			append("agentId", new Integer(agentId));
    	
    	WriteResult wr = notificationColl.insert(notificationDocument, WriteConcern.ACKNOWLEDGED);
    	
		//System.out.println(coll.findOne());
		
		
		System.out.println("Tester:main ==> End");
		
	}

}
