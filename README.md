# COVA TRIVIA APP

### About:

A simple quiz application REST API suite for users to answers set of questions based on various difficulty level and get answers at the end

### Functional Requirements 
<small>These functional requirements have been unit tested as well<small/>

#### Signup and Login
- User can signup with their email, firstname, lastname, username and password
- User can get a notification for successful signup
- User can get a notification for a failed signup  
- User can log-in with their username and password
- User can view their account after successful sign-in
- User can get an error for a failed sign-in and hint on what to do
- User can log out of their account and not have access to any resource until they sign in again

#### Trivia
- User can select trivia to answer based on selected difficulty level
- User can provide answer to selected quiz
- User can skip to the next question
- User can submit answers at anytime and end the quiz
- User can view their result immediately after submit their answers or ending the quiz

#### Trivia History
- Users can view the past result of trivias taken - Trivia History


#### Design
- SOLID
- TDD
  - Unit Test
  - Integration Test  
- Documentation
- API Design

#### Running the Project
- To set-up this project locally, you must have the following installed locally:

    1. minimum of JDK 8
    2. Apache maven 3.6.3 and above
    3. SpringBoot version _ and above
    4. Mysql version 5.x+
 
- At the root of the project run this command `mvn clean install`
This would run both the main application and, the associated  tests

### Api Docs
[cova-trivia postman api collection](https://documenter.getpostman.com/view/10629518/TzedhQAB)