# SurveyProject
About Java Spring Boot RESTful Web service
This RESTful Web service is on customer satisfaction survey. It presents customer a survey on how likely they are recommend a topic to their friends and collegue.

RESTful Web service includes 4 endpoints, 2 models and 2 JUnit test classes.

RESTful Web service

Method	Endpoint	Description
POST	/createSurvey	Creates new survey
POST	/submitSurvey/{surveyId}	It stores score and feedback answers of a customer topic for a topic.
GET	/listSurveyAnswers/{id}	It gets the list of survey answers of a topic.
GET	/listSurveytopics	It gets the list of topics with their Net Promoter Score (NPM) Score.
Endpoint	Test Class	Test Method
/createSurvey	SurveyControllerTest	testAddSurvey()
/submitSurvey/{surveyId}	FeedbackControllerTest	testaddFeedback()
/listSurveyAnswers/{id}	FeedbackControllerTest	testGetAllFeedbacksBySurveyId()
/listSurveytopics	SurveyControllerTest	testGetAllsurveys()
Model	Description
Survey	It stores Survey Question, NPM Score and Survey Topic
Feedback	It stores Feedback answers, Score, Survey Id
Bidirectional Mapping
Survey - Feedback (One-To-Many)

Feedback - Survey (Many-To-One)

The purpose of using the Spring boot framework when developing this application was that many settings were automatic (embedded server, H2 in the memory database) and fast. Spring MVC was implemented as a front-end, as it is compatible with the Restfull URL, thus facilitating application development by dividing it into layers and offering rapid development. H2 database is used to store survey and answers and The Java Persistence API (JPA) is used for accessing databases.

Tools and technologies used
Java Spring Boot
Spring Web MVC
H2 in memory database
JPA
Unit test
Spring Tools Suite 4
Swagger Documentation
Usage
Download the zip or clone the Git repository.
Open the Eclipse or STS
File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
Select Task File
Right Click on the file and Run as SpringBootApp
Add JUnit if it is missing => Properties -> Java Build Path -> Libraries -> Add Library -> JUnit
Access resources with Postman.
Run it, Open the browser and access through postman.
There are 2 Rest Controllers and 4 methods;
