# OkHttpInterceptor - ByteBuddy

A sample app explaining how to add custom Interceptor for OkHttpClient at runtime using [ByteBuddy](https://bytebuddy.net/#/) and JavaAgent.

## Usage

### Setup App

1. ```git clone https://github.com/gouravkrosx/JavaAgent_ByteBuddy```


2. ``` cd OkHttpInterceptor_ByteBuddy ```


3. ```mvn clean install```


### Run the Application

1. To use Agent for intercepting, run below on terminal
```shell
   java -javaagent:<path to agent>.jar -jar <path to application>.jar
```
for eg:
```shell
   java -javaagent:/home/user/Desktop/JavaAgent_ByteBuddy/Agent/target/Agent-1.0.0-SNAPSHOT.jar -jar /home/user/Desktop/JavaAgent_ByteBuddy/Application/target/Application-1.0.0-SNAPSHOT.jar
```
