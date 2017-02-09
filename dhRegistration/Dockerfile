#FROM registry.ng.bluemix.net/ibmnode:v1.2
FROM node:4-onbuild

RUN wget -qO- https://github.com/amalgam8/amalgam8/releases/download/v0.4.2/a8sidecar.sh | sh

# Create app directory
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json /usr/src/app/
RUN npm install

# Bundle app source
COPY . /usr/src/app

EXPOSE 80
#CMD [ "npm", "start" ]

RUN wget -qO- https://github.com/amalgam8/amalgam8/releases/download/v0.4.2/a8sidecar.sh | sh

ENTRYPOINT ["a8sidecar", "--register", "--proxy", "--supervise", "node", "app.js", "80"]

ENV A8_SERVICE=dhregistration:v1.0
ENV A8_ENDPOINT_PORT=80
ENV A8_ENDPOINT_TYPE=http
ENV A8_REGISTRY_URL=http://dev-CPO-a8-registry.mybluemix.net
ENV A8_CONTROLLER_URL=http://dev-CPO-a8-controller.mybluemix.net
ENV A8_LOG=true
