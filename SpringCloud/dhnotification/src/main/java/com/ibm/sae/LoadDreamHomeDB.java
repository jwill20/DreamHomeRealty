package com.ibm.sae;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.mongodb.*;

public class LoadDreamHomeDB
{
	public static void main(String[] args) 
	{
		//MongoClient mongoClient = new MongoClient("158.85.248.111",8888);
		
		MongoCredential credential = MongoCredential.createCredential("cpoAdmin", "admin", "enitlavo908#".toCharArray());
		
		MongoClient mongoClient = new MongoClient(new ServerAddress("158.85.248.111",8888), Arrays.asList(credential));
		
		DB db = mongoClient.getDB( "dreamHomeAmalgam8DB" );
		
		loadCounters(db);
		
		loadAll(db);
		
		//db = mongoClient.getDB( "dreamHomeOpenShiftDB" );
		//loadAll(db);
		
		//db    = mongoClient.getDB( "dreamHomeAWSDB" );
		//loadAll(db);
		
		//db    = mongoClient.getDB( "dreamHomeAzureDB" );
		//loadAll(db);
		
		//db    = mongoClient.getDB( "dreamHomePivotalDB" );
		//loadAll(db);
		
		mongoClient.close();
	}

	/***************************************************************/
	/* loadNotificationCounters                                    */
	/***************************************************************/
	private static void loadCounters(DB db)
	{
		System.out.println("LoadDreamHomeDB:loadCounters begins");

		DBCollection coll = db.getCollection("dhCounterColl");
		System.out.println("LoadDreamHomeDB:loadCounters collection");
		coll.drop();
		
		System.out.println("LoadDreamHomeDB:loadCounters after drop");
		
		DBObject countersDoc = new BasicDBObject("_id", "notificationId")
		    		.append("seq", 5000);
		
		WriteResult wr = coll.insert(countersDoc, WriteConcern.ACKNOWLEDGED);

		countersDoc = new BasicDBObject("_id", "clientId")
	    		.append("seq", 4000);
		
		wr = coll.insert(countersDoc, WriteConcern.ACKNOWLEDGED);

		countersDoc = new BasicDBObject("_id", "propertyId")
	    		.append("seq", 2000);
		
		wr = coll.insert(countersDoc, WriteConcern.ACKNOWLEDGED);

		countersDoc = new BasicDBObject("_id", "agentId")
	    		.append("seq", 1000);
		
		wr = coll.insert(countersDoc, WriteConcern.ACKNOWLEDGED);

		countersDoc = new BasicDBObject("_id", "officeId")
	    		.append("seq", 3000);
		
		wr = coll.insert(countersDoc, WriteConcern.ACKNOWLEDGED);

		
		System.out.println("LoadDreamHomeDB:loadNotificationCounters ends");
	}

		


	/***************************************************************/
	/* loadAll                                                     */
	/***************************************************************/
	private static void loadAll(DB db)
	{
		System.out.println("LoadDreamHomeDB:loadAll begins");
		
		DBCollection coll = db.getCollection("dhPropertyColl");
		loadProperty(db, coll);
		
		coll = db.getCollection("dhOfficeColl");
		loadOffice(db, coll);
		
		coll = db.getCollection("dhNotificationColl");
		loadNotification(db, coll);
		
		coll = db.getCollection("dhAgentColl");
		loadAgent(db, coll);
		
		coll = db.getCollection("dhClientColl");
		loadClient(db, coll);
		
	}	

	/***************************************************************/
	/* loadClient                                                  */
	/***************************************************************/
	private static void loadClient(DB db, DBCollection coll)
	{
		System.out.println("LoadDreamHomeDB:loadClient begins");

		coll.drop();
		
		List<DBObject> comments = new ArrayList<DBObject>();
		List<DBObject> suggestedProperties = new ArrayList<DBObject>();
		
		DBObject commentDoc = new BasicDBObject("comment", "This is a beautiful home");
		
		comments.add(commentDoc);
		
		DBObject propertyDoc = new BasicDBObject("propertyId", 1999)
				.append("propertyState", 0)
				.append("askingPrice", 689900.45)
				.append("rating", 0)
				.append("comments", comments);
			
		suggestedProperties.add(propertyDoc);
		
		DBObject clientDoc = new BasicDBObject("clientId", 3999)
		    		.append("clientName", new BasicDBObject("clientFN", "Richard")
					.append("clientLN", "Hendrix"))
		    		.append("clientInfo", new BasicDBObject("address", "101 Valley Street")
					.append("city", "Glendale")
					.append("state", "California")
					.append("zip", "67854"))
					.append("agentId", 1001)
					.append("suggestedProperties", suggestedProperties)
					.append("minAskingPrice", 500000.00)
					.append("maxAskingPrice", 700000.00);
		
		WriteResult wr = coll.insert(clientDoc, WriteConcern.ACKNOWLEDGED);
		
		System.out.println("LoadDreamHomeDB:loadClient ends");
	}
	
	/***************************************************************/
	/* loadAgent                                                   */
	/***************************************************************/
	private static void loadAgent(DB db, DBCollection coll)
	{
		System.out.println("LoadDreamHomeDB:loadAgent begins");

		coll.drop();
		
		List<DBObject> properties = new ArrayList<DBObject>();
		
		DBObject propertyDoc = new BasicDBObject("propertyId", 2000)
				.append("clientId", 4000)
	    		.append("propertyState", 0);
	
		properties.add(propertyDoc);
		
		DBObject agentDoc = new BasicDBObject("agentId", 999)
		    		.append("agentData", new BasicDBObject("agentFN", "Dinesh")
							.append("agentLN", "Chugtai")
							.append("agentLicense", "CAL-34917"))
					.append("officeId", 3000)
					.append("properties", properties);
		
		WriteResult wr = coll.insert(agentDoc, WriteConcern.ACKNOWLEDGED);
		
		System.out.println("LoadDreamHomeDB:loadAgent ends");
	}

	/***************************************************************/
	/* loadNotifications                                           */
	/***************************************************************/
	private static void loadNotification(DB db, DBCollection coll)
	{
		System.out.println("LoadDreamHomeDB:loadNotification begins");

		coll.drop();
		
		DBObject notificationDoc = new BasicDBObject("notificationId", 4999)
		    		.append("agentId", 1000)
		    		.append("clientId", 4000);
		WriteResult wr = coll.insert(notificationDoc, WriteConcern.ACKNOWLEDGED);

		System.out.println("LoadDreamHomeDB:loadNotification ends");
	}

	/***************************************************************/
	/* loadOffice                                                  */
	/***************************************************************/
	private static void loadOffice(DB db, DBCollection coll)
	{
		System.out.println("LoadDreamHomeDB:loadOffice begins");

		coll.drop();
		
		DBObject officeDoc = new BasicDBObject("officeId", 2999)
		    		.append("officeName", "Valley North")
		    		.append("officeManager", "Erlich Bachman")
					.append("officeAddr", new BasicDBObject("address", "223 Mountain Drive")
					.append("city", "Buena Vista")
					.append("state", "California")
					.append("zip", "65746"))
					.append("numProperties", 0);
		WriteResult wr = coll.insert(officeDoc, WriteConcern.ACKNOWLEDGED);
		
		System.out.println("LoadDreamHomeDB:loadOffice ends");
	}
	
	/***************************************************************/
	/* loadProperty                                                */
	/***************************************************************/
	private static void loadProperty(DB db, DBCollection coll)
	{
		System.out.println("LoadDreamHomeDB:loadOffice begins");
		
		coll.drop();
		
	    DBObject propertyDoc = new BasicDBObject("propertyId", 2000)
				.append("location", new BasicDBObject("address", "1024 College")
						.append("city", "Wheaton")
						.append("state", "California")
						.append("zip", "76857")
						.append("longitude", "35.601623")
						.append("latitude", "-78.245908"))
				.append("sqFeet", 2895)
				.append("numBeds", 4)
				.append("numBaths", 3)
				.append("description", "Two blocks from university")
				.append("askingPrice", 789900.00);
	    WriteResult wr = coll.insert(propertyDoc, WriteConcern.ACKNOWLEDGED);

	    DBObject propertyDoc2 = new BasicDBObject("propertyId", 2001)
				.append("location", new BasicDBObject("address", "435 Weston")
						.append("city", "Springfield")
						.append("state", "California")
						.append("zip", "76857")
						.append("longitude", "36.507623")
						.append("latitude", "-79.145509"))
				.append("sqFeet", 3200)
				.append("numBeds", 5)
				.append("numBaths", 3)
				.append("description", "Nice cottage by lake")
	    		.append("askingPrice", 569900.00);
	    WriteResult wr2 = coll.insert(propertyDoc2, WriteConcern.ACKNOWLEDGED);

	    DBObject propertyDoc3 = new BasicDBObject("propertyId", 2002)
				.append("location", new BasicDBObject("address", "2240 Berlin")
						.append("city", "Florence")
						.append("state", "California")
						.append("zip", "76857")
						.append("longitude", "31.086579")
						.append("latitude", "-72.357987"))
				.append("sqFeet", 3950)
				.append("numBeds", 5)
				.append("numBaths", 5)
				.append("description", "Mansion in the city")
				.append("askingPrice", 645800.00);
	    WriteResult wr3 = coll.insert(propertyDoc3, WriteConcern.ACKNOWLEDGED);
	    
		System.out.println("LoadDreamHomeDB:loadProperty ends");
	}
	
}
