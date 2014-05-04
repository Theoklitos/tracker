How to build the project
------------------------
You need to use maven, specifically the maven war plugin. By executing a mvn war:war the generated war file will be deployed to a default location of <strong>${user.home}/tracker_project/tomcat/webapps/</strong>. You can change this location by setting the "tomcat.deploy.folder" system property, for example: mvn war:war -Dtomcat.deploy.folder=/somewhere/else/. The war plugin merely packages the application; if you also want to compile and test then use the commands <strong>mvn compile</strong> and <strong>mvn test</strong>, or simply do </strong>mvn clean install war:war</strong> to go through all the phases.

Application URL
---------------
The application's root context path is "/tracker", which means you can access it by appending that to your tomcat root url, default being <strong>http://localhost:8080/tracker</strong>

Tools and dependencies
----------------------
I used <strong>openJDK7</strong> (java-7-openjdk-amd64) in Ubuntu 12.04 for this project, along with <strong>Maven3.1.1</strong>. Any other dependencies are handled through maven. If you have problems due to a different java or maven version, please contact me to see what can be done.
