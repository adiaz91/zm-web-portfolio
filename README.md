# Project Title: 
Simple Portfolio Java Web App

# Description:
This Java web application serves as a basic portfolio website. It displays a profile image, name, and a brief description of the experience.

The following tools were used to develop and build this project:

1. **JDK (Java Development Kit) 17:** 
2. **Gradle 8.2.1:** Gradle is the build tool used for this project. 
3. **Spring Boot 3.1.2:** This project is built using Spring Boot framework, version 3.1.2.
4. **IntelliJ IDEA 2023.2 (Community Edition):** IntelliJ IDEA is the integrated development environment used for coding and managing the project.

Before you begin, make sure you have the following installed on your system:

1. Java Development Kit (JDK) 17+.

# Run

1. Clone this repository to your local machine using <git clone git@github.com:adiaz91/zm-web-portfolio.git>

2. Open the project with your preferred  IDE

3. Setting Environment Variables:
Certain configuration parameters need to be set via environment variables before running the Simple Portfolio Java Web App.
    #### Database
   * `DB_HOST`: "your-database-host"
   * `DB_USERNAME`: "your-database-username"
   * `DB_PASSWORD`: "your-database-password"
   * `DB_DATABASE`: "database-name"
   #### Twitter 
    Obtain your Twitter API credentials from the Twitter Developer platform. If you don't have one, you'll need to create a Twitter Developer account and create an App to obtain the API key.
   * `TWITTER_API_KEY`: 
   * `TWITTER_SECRET_API_KEY`:
   * `TWITTER_ACCESS_TOKEN`:
   * `TWITTER_ACCESS_TOKEN_SECRET`:

4. To run the application locally, use the following command: 

```console
 ./gradlew bootRun
```
**Accessing the Application:**
Once you have successfully built and run the application, you can access it in your web browser
following URL http:\\localhost:8080 

**Accessing API Endpoints:**
The application provides API endpoints that you can interact with. Here's how to access them:
Make GET, PUT requests to the API endpoints based on the available functionality.

For example, you might have endpoints like `http://localhost:8080/api/1` to retrieve  data.
Please refer to the source code to understand the available endpoints and their functionalities.

5. To execute tests for the project, run the following command:

```console
 ./gradlew test
```

