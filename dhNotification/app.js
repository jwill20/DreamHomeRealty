/********************************************************************************/
/* Name:       dhNotification                                                   */
/*                                                                              */
/* Purpose:    Writes a notification document to the dhNotification collection. */
/*                                                                              */
/* Interfaces:                                                                  */
/*                                                                              */
/* Author:     Jim Williams                                                     */
/*                                                                              */
/********************************************************************************/
var http       = require('http');
var express    = require('express');
var request    = require('request');
var bodyParser = require('body-parser');
var Cloudant   = require('cloudant');
var async      = require('async');
var MongoClient      = require('mongodb').MongoClient;
var mongodb;
var dhNotification =  process.env.A8_SERVICE || "dhNotification";

/***************************************************************/
/* Set up express server                                       */
/***************************************************************/
var app        = express();
var server     = http.createServer(app);
app.use(express.static(__dirname + '/public'));
app.use(bodyParser.json());

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

console.log("dhNotification ==> Begin Execution");

/***************************************************************/
/* Set up MongoDB connection pool                              */
/***************************************************************/
mongoURL = "mongoDB://:";

MongoClient.connect(mongoURL, {poolSize:10}, function(err, db) 
{
   if(err)
   {
      console.log("dhNotification ==> Error connecting to MongoDB");
      console.log(err);
      res.sendStatus(500);
      return;
   }
   else
   {
      mongodb = db;
      console.log("dhNotification ==> Connected to MongoDB");
      console.log("dhNotification ==> This service is ==> " + dhNotification);
   }
});
/******************************************************/
/* Write to the dhNotificationColl collection         */
/******************************************************/
app.post('/dhnotification', function (req, res, next)
{
   console.log("dhNotification - Incoming POST with " + req.body.clientId + " and " + req.body.agentId);

   var document;
   var counterColl = mongodb.collection("dhCounterColl");
   var collection  = mongodb.collection('dhNotificationColl');

   counterColl.findAndModify(
         {'_id':'notificationId'},
         [['_id','asc']],
         {$inc:{'seq':1}},
         {new:true,w:1,j:1},
   function(err, result)
   {
     if(err)
     {
       console.log("dhnotification ==> Error doing findAndModify");
       console.log(err);
       res.sendStatus(500);
       return;
     }
     else
     {
       console.log("dhnotification ==> Good return from findAndModify");
       console.log(result.value.seq);

       document =
         {notificationId:result.value.seq,
          agentId:parseInt(req.body.agentId),
          clientId:parseInt(req.body.clientId)
         };
       console.log(document);

       collection.insertOne(document, function(err, item)
       {
         if(err)
         {
            console.log("dhnotification ==> Error doing insertOne in MongoDB");
            console.log(err);
            res.sendStatus(500);
            return;
         }
         else
         {
            console.log("dhnotification ==> Good return from insertOne in MongoDB");
            res.sendStatus(201);
            return
         }
      });
    }
   });
});
/******************************************************/
/* Health check for dhNotification                    */
/******************************************************/
app.get('/health', function (req, res)
{
   console.log("dhNotification - Health check");
   res.status(200).send("Health status good");
});
/**********************************************/
/* Start the server                           */
/**********************************************/
server.listen(80);
//server.listen(6378);
console.log("Starting server on port 80");
//console.log("Starting server on port " + server.address().port);
