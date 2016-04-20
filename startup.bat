@echo off

set CLASSPATH=lib\commons-codec-1.9.jar;lib\commons-logging-1.2.jar;lib\gson-2.6.2.jar;lib\hamcrest-core-1.1.jar;lib\httpclient-4.5.jar;lib\httpcore-4.4.1.jar;lib\httpmime-4.5.jar;lib\irju-bot.jar;lib\jackson-core-asl-1.9.13.jar;lib\jackson-mapper-asl-1.9.13.jar;lib\java-telegram-bot-api.jar;lib\log4j-1.2.17.jar;lib\retrofit-1.9.0.jar;lib\slf4j-api-1.7.12.jar;lib\slf4j-simple-1.7.12.jar

java -classpath %CLASSPATH% ch.irju.telegram.bot.Main
