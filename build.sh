#!/bin/bash

cd dhgateway
pwd
#bluemix ic build -t registry.ng.bluemix.net/jimnode/dhgateway:v1.0 .

cd ..
cd dhapplication
pwd
#bluemix ic build -t registry.ng.bluemix.net/jimnode/dhapplication:v2.0 .

cd ..
cd dhregistration
pwd
#bluemix ic build -t registry.ng.bluemix.net/jimnode/dhregistration:v1.0 .

cd ..
cd dhnotification
pwd
bluemix ic build -t registry.ng.bluemix.net/jimnode/dhnotification:v1.0 .

cd ..
pwd
