# compare versions & tracking web server

For the SWE Interview Homework questions, I have chosen the compare version question and the tracking web server questions. 
The project has been created using [Spring initializr](https://start.spring.io/). The project is working with Java 1.8 and Gradle.

You can use your favourite IDE. In my case, I have using [Visual Studio Code](https://code.visualstudio.com/).
Also, it would be necessary to install [Postman](https://www.postman.com/), if you do not have it. This program will help us to request the endpoints that the project has.

In order to get the project, it would be necessary to clone the repository with the next command.

    git clone https://github.com/aaxlss/backend.git

To run the embed Tomcat server, you will need to open the *BackendApplication.java* file and run the public static void main method. 
if everything goes well, you would be able to request call to the API in the next route by default.

    http://localhost:8080

# compare versions

In the file *ValidateVersions.java* there is one method called *versions*. This method contains the logic to validate between two versions and the response will be an Integer: 1, -1 or 0 if the version 1 is higher than the version 2, if the versions 2 is higher than the version 1 otherwise 0.

The valid endpoint to call is the next one:

    POST:http://localhost:8080/versions
This request will be expecting 2 attributes (v1 and v2). This could be added as a form-data from postman

# Tracking Web Server

In the file *TrackingWebService.java* there are 2 methods. One to know if the file tmp/ok exist and the another one will return a gif file.

To call the method to validate if the file tmp/ok is the next one:

    GET:http://localhost:8080/pin
This will return a 200 code and an "OK" string in the body if the file exist, otherwise will return *503 code Service unavailable*

The second method will help us to return a gif file:

GET:http://localhost:8080/img

This method will return the file using **APPLICATION_OCTET_STREAM** as a content type.

# Infrastructure
One of the best options to make the system scaleable is choosing the *"Horizontal scaling"*. This option will help to increase the number of nodes , depending on the demand of the service. However, The disadvantage for this option is the cost, the more the servers, the higher the cost to pay. 

Moreover, to have high availability, a *load balancer* needs to be configured to distribute the traffic across the servers. If one of the servers shutdown, the load balancer will redirect the traffic to another server.

Another improvement that could be implemented is to configure cache and CDN. A good idea is having CDN in different Zones to reduce the time-consuming when the user is requesting static information.

AWS provides some services to achieve this kind of architecture