# Step 1: Use an official Eclipse GlassFish base image
FROM ghcr.io/eclipse-ee4j/glassfish:7.0.11-jdk17

# Step 2: Set the environment variable for the deployment directory
ENV DEPLOY_DIR=/glassfish7/glassfish/domains/domain1/autodeploy

# Step 3: Copy your built .war or .ear file into the autodeploy folder
# (Adjust 'target/*.war' if your build tool outputs somewhere else)
COPY target/customer-service.war ${DEPLOY_DIR}/

# Step 4: Expose GlassFish standard ports (8080 HTTP, 4848 Admin Console)
EXPOSE 8080 4848
