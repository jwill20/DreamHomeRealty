/********************************************************************************/
/* Name:       dhApplication                                                    */
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
var MongoClient = require('mongodb').MongoClient;

/***************************************************************/
/* Set up express server                                       */
/***************************************************************/
var app        = express();
var server     = http.createServer(app);
app.use(express.static(__dirname + '/public'));
app.use(bodyParser.json());

console.log("dhApplication ==> Begin Execution");

/******************************************************/
/* Pass through to the dhRegistration service         */
/******************************************************/
app.post('/dhapplication', function (req, res)
{

  console.log("dhApplication - Incoming POST with clientId and agentId in body");

  registrationURL = "http://localhost:6379/dhregistration/dhregistration";

  console.log("dhApplication - Outgoing POST to dhRegistration with clientId = " + req.body.clientId + " and agentId = " + req.body.agentId);

  var options =
  {
    url: registrationURL,
    json: {
        clientId: req.body.clientId,
        agentId:  req.body.agentId
    }
  };

  request.post(options, function(err, registrationResponse, registrationBody)
  {
     if(err)
     {
       console.log("dhApplication - Outgoing POST to dhRegistration received an error");
       console.log("dhApplication - Error is - " + err);
       res.sendStatus(500);
       return;
     }
     if(registrationResponse.statusCode !== 201)
     {
        console.log("dhApplication -  Invalid status code returned from dhRegistration");
        console.log("dhApplication -  Status code is - " + registrationResponse.statusCode);
        res.sendStatus(registrationResponse.statusCode);
        return;
     }
     else
     {
        console.log("dhApplication -  Good status code returned from dhRegistration");
        res.sendStatus(201);
     }

  });
});
/******************************************************/
/* Health check for dhApplication                     */
/******************************************************/
app.get('/health', function (req, res)
{
   console.log("dhApplication - Health check");
   res.status(200).send("Health status for V1.0 of dhApplication is good");
});
/**********************************************/
/* Start the server                           */
/**********************************************/
server.listen(80);
console.log("Starting server on port 80");
//console.log("Starting server on port " + server.address().port);
