# Forester app for deploy on Heroku

In order to run Spring Boot on Heroku add this:
```server.port=${PORT:5000}```
in application.properties file

In order to run Spring MVC on Heroku add this:
```web:    java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war ```
in Procfile
