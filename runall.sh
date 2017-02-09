#!/bin/bash
#docker run -d --name dhapplication  -p 80 registry.ng.bluemix.net/jimnode/dhapplication:v2.0
#docker run -d --name dhregistration -p 80 registry.ng.bluemix.net/jimnode/dhregistration:v1.0
docker run -d --name dhnotification -p 80 registry.ng.bluemix.net/jimnode/dhnotification:v1.0
#docker run -d --name dhgateway -p 6379 registry.ng.bluemix.net/jimnode/dhgateway:v1.0
