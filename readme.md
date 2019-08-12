##*Codding assessment by **Federico González Castañón***
This project was made using spring boot, JAVA 8 and Eclipse IDE. In order to use it first cd to project folder, then run 
“mvn install”
after successful build, run 
java -jar target/restBack-0.0.1-SNAPSHOT.war
And that should leave the project up and running and ready to test.
The code defines 13 Tests on class RestBackApplicationTests to be run with jUnit 
#Detailed process
After reading the assignment I made a few decisions regarding the architecture of my solution.
First, building an interface for the REST controller and then implement said interface in a class.  This became NotesController and NotesControllerImpl. This makes everyone’s job easier, since teams depending on methods included in the interface can make a mock implementation and start using them, without waiting for the actual implementation to come through.
Since CRUD operations were required I started thinking what was I going to do for persistence. I decided, again, to make an interface and then implement it. The reasons where the same as before, but this time I wasn’t sure on how I was going to make the implementation, so the possibility of starting with a MockDAOImpl was also attractive. This became NotesDAO and NotesDAOImpl.
The development was made under the concept of Test Driven Design, not only because the code assessment specified some test that the resulting code should pass, but because I believe this to be a great way to go about any coding.
When choosing tools, I decided upon spring boot given the great size of its community, it’s top notch documentation, easy set up and native integration with jUnit for testing. I had worked in the past with Spring 2 and 3 so it seemed like a good fit for me, even though I hadn’t used it. The fact that it can be executed without the need of any other tool besides JAVA was also a plus, considering the recommendation that whatever I submitted should be easily testable. 
Decided upon H2 for persistence after a quick google search for “in memory database + JAVA”. Most results pointed out how straightforward it was to set up with spring boot and several web sites offered numerous examples on how to do it.
Finally, I looked for a framework that would allow me to test without the need for external tools such as Postman, in order to provide another way to test my code without the need to use “curl”. I decided upon REST Assured since I’ve used it successfully recently.
After some configuration I realized that implementing the DAO was not going to be a problem so the idea of a MockDAO was dismissed. 
I moved to implementing my first two tests, one to warrant that my controller was up un running and other to check if the in-memory database was successfully created and responding as expected. This are alive and dbAliveAndWell.
These two test warrant that nothing has gone amiss among the system layers, so they were implemented first.
Next off was creating every other test. Starting with those specified in the document, continuing with the missing CRUD operations and finishing with some bug related testing (very large inputs, null inputs).
Given that the url has no versioning, I would recommend doing it with custom header “Accept-version: v1”, but decided against implementing it in order to avoid conflicts with the test described in the document. 
Researching, coding, testing and documenting took me about 7 hours.

