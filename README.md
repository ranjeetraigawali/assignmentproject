Follow below steps to build and run the project correctly.

1. Running the application in dev mode
    You can run your application in dev mode that enables live coding using:
    ./mvnw compile quarkus:dev   OR
     mvn compile quarkus:dev

2. (Optional) The application can be packaged using:
   ./mvnw package  OR
    mvn package

3. Update below properties related to JWT keys in application.properties file to your location.
    smallrye.jwt.sign.key.location={your location}
    mp.jwt.verify.publickey.location={your location}

    Due to time constraint, I was not able to create different module i.e. one to generate token and other to acsess pricate API.
    To access POST method you must have valid JWT token which will get from /token endpoint.

4.  Update below datasource details/properties required to access mariadb database in the application.properties file.
    quarkus.datasource.db-kind = mariadb
    quarkus.datasource.username = {your_db_username}
    quarkus.datasource.password = {your_db_password}
    quarkus.datasource.jdbc.url = {your_jdbc_url}

You are good to go.
Access the url with below link

http://{hostname}:{port}/q/swagger-ui

For ex. http://localhost:8080/q/swagger-ui
