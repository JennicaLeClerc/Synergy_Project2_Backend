# Project 2

## Discription

Synergy Hotel Mangament System is an application for both users and employees to interact with our organization. Users can login, make reservations, view/update existing reservations, and manage their accounts. Employees can view and manage reservations. Our application includes a fully integrated calendar API (FullCalendar) for vacation planning as well as an API to check the weather at our many locations.

## User Stories
User
- Sign up for an account
- Log in and out
- Change information: first name, last name, email, and password
- Make reservations for specific dates
- Make specific accommodation requests
- Update reservation dates

Employee
- Log in and out
- Change information: first name, last name, and password
- Approve or Deny reservations
- Change room availability
- View the status of all rooms

Manager
- All abilities that an Employee has
- Add employee and other manager accounts
- Change room prices by changing amenity prices
- Add rooms to the system

## High-Level Requirements

Application must leverage the full stack: 
- RDBMS for persistence 
- API built with Java 8 and Spring 5
- First iteration of the UI is due on December 10th and will be built with HTML, CSS and JS.
- Second and final iteration of the UI is due with the presentation on December 17 and will be built with React.

Technology framework requirements: 
- Java API will use Hibernate to communicate with a PostGreSQL RDBMS 
- Java API will leverage the Spring Framework 
- Java API will be RESTful (no `HttpSession`, use JWTs!)
- Complete CI/CD pipelines will use AWS (CodePipeline, CodeBuild, Elastic Beanstalk, and S3)

Other requirements: 
- Application will demonstrate at least ten individual user stories 
- Application will leverage at least one external API 
- Application's own data model must be sufficiently complex (i.e. >2 tables) 
- RDBMS will be deployed to the cloud (AWS RDS) 
- Java API will be deployed to the cloud (AWS EC2) 
- UI application will be deployed to the cloud (AWS S3) 
- Java API will have >=80% test coverage for service layer
- Java API will leverage Spring's MockMvc for integration/e2e tests of controller endpoints

Bonus goals:
- Deploy API using ECS w/ Docker (instead of Elastic Beanstalk)
- Secure your Java API using Spring Security
- Java API will be adequately documented (Java Docs and web endpoint documentation [Swagger/OpenAPI])
 
## Front End
