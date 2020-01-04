# pull the latest node image from dockerhub
FROM node:lts-alpine

# copy the contents of the ./client-certify to 
# /root/client-certify directory inside the container
COPY . /root/Client

# set the workdirectory to /root/client-certify
WORKDIR /root/Client

# Do the npm install
RUN npm i vuetify --save

# npm install 
RUN npm install

# port to expose
EXPOSE 8080

# start the project when container starts
ENTRYPOINT ["npm", "run", "dev"]