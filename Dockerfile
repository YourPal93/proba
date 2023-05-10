FROM adoptopenjdk/openjdk16:ubi
ARG JAR_FILE=build/libs/proba-*-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT {"java","-Dbot.username=${BOT_NAME}","-jar","./app.jar"}