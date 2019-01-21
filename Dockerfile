FROM openjdk:8-jre-slim

WORKDIR /usr/share/tag

# Add the project jar & copy dependencies
ADD  target/selenium-docker.jar selenium-docker.jar
ADD  target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD  target/libs libs

# Add the suite xmls
ADD order-module.xml order-module.xml
ADD search-module.xml search-module.xml

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# GRIDHOST = selenium hub hostname / ipaddress

ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $MODULE