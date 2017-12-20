FROM openjdk:8-jre-slim

# Docker image base dir
ARG BASE_DIR=/usr/share/tag

# Add the project jar & copy dependencies
ADD  target/container-test.jar BASE_DIR/container-test.jar
ADD  target/libs BASE_DIR/libs

# Add the suite xmls
ADD order-module.xml BASE_DIR/order-module.xml
ADD search-module.xml BASE_DIR/search-module.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# GRIDHOST = selenium hub hostname / ipaddress

ENTRYPOINT /usr/bin/java -cp BASE_DIR/container-test.jar: -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG BASE_DIR/$MODULE