/********************************************************************************/
/* Name:       dhRegistration                                                   */
/*                                                                              */
/* Purpose:    Acts as the main entry point to the application and also serves  */
/*             up the web app.                                                  */
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

/***************************************************************/
/* Set up express server                                       */
/***************************************************************/
var app        = express();
var server     = http.createServer(app);
app.use(express.static(__dirname + '/public'));
app.use(bodyParser.json());

console.log("dhRegistration ==> Begin Execution");

/******************************************************/
/* Route to invoke dhnotification                     */
/******************************************************/
app.post('/dhregistration', function (req, res) 
{

  console.log("dhRegistration - Incoming POST with clientId and agentId");

  notificationURL = "http://localhost:6379/dhnotification/dhnotification";

  console.log("dhRegistration - Outgoing POST to dhNotification with clientId = " + req.body.clientId + " and agentId = " + req.body.agentId);

  var options =
  {
    url: notificationURL,
    json: {
        clientId: req.body.clientId,
        agentId:  req.body.agentId
    }
  };

  request.post(options, function(err, notificationResponse, notificationBody)
  {
     if(err)
     {
       console.log("dhRegistration - Outgoing POST to dhNotification received an error");
       console.log("dhRegistration - Error is - " + err);
       res.sendStatus(notificationResponse.statusCode);
       return;
     }
     if(notificationResponse.statusCode !== 201)
     {
        console.log("dhRegistration -  Invalid status code returned from dhNotification");
        console.log("dhRegistration -  Status code is - " + notificationResponse.statusCode);
        res.sendStatus(notificationResponse.statusCode);
        return;
     }
     else
     {
        console.log("dhRegistration -  Good status code returned from dhNotification");
        res.status(201).send("Node");
        return;
     }

  });
});
/******************************************************/
/* Path to .........                                  */
/******************************************************/
app.get('/test', function (req, res) 
{
   'use strict';
   var x = 3;
   var device = "abcdefg#Bob";
   var device2 = "abcdefg#Alice";
   console.log("Here we go!");
   console.log(device.substr(0, device.indexOf("#")));
   console.log(device.substr(device.indexOf("#") +1));
   console.log(device2.substr(device2.indexOf("#") +1));
   res.sendStatus(200);
});
/**********************************************/
/* Start the server                           */ 
/**********************************************/ 
server.listen(80);
//server.listen(6379);
console.log("Starting server on port 80");
//console.log("Starting server on port " + server.address().port);
