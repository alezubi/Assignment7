# Use a base image with a servlet container like Tomcat
FROM tomcat:9.0

# Copy the WAR file to the webapps directory of Tomcat
COPY target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/demo.war

# Expose the Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]