Scopic Automation Test Task – Bogdan Turchyk

Automation Framework has been created according to the "Automation Test Task" document for testing the basic functionality of the RegioJet system.

NOTE: I did not make separate projects for the frontend and backend, but placed the tests in different classes of the same project.

General Information
The framework is built with Gradle - build automation tool which has build.gradle file that allows
us to manage our dependencies/versions easily.
IntelliJ is used as IDE Tool.
Java – Selenium – JUnit5 have implemented the actual coding logic inside of the "Tests" package and their own respective/related classes.
Page Object Model is used to simplify managing and maintaining the framework.
BasePage (Abstract) is created and initiated with the "PageFactory" class using the "Driver" and "BasePage" constructors.
"MainSearchPage" is created and extends the "BasePage".
This page keeps the respective web elements and some custom methods in the classes in order to call from Test Classes 
with the help of page objects, during the code implementation.
TestBase class (Abstract) is created in order to implement some JUnit 5 annotations such as @BeforeEach, and @AfterEach.
The "FrontEndTests" class contains all created tests for frontend testing and is extended to the "TestBase" class in order to have reusable codes.
The "BackEndTests" class contains all created tests for backend testing.
In the "utilities" package were created some respective classes with reusable codes, such as "Driver" and "ConfigurationReader".
Singleton Design Pattern is used in the "Driver Class" to allow the framework to pass the same instance of the Webdriver in one session.
"BrowserUtils Class" is created in order to have some reusable codes.
"ConfigurationReader Class" is created to read specific data from the configuration.properties file.
The "configuration.properties" type of file is created in order to keep the important test data about the framework in key-value format.
The "log4j2.xml" file is used for logging the automation steps, which can be seen on the console. I also used this to output 
to the console for additional information about the routes according to the task.

Test Cases for Front End project.
Note: Those steps below are implemented in the TestBase class with the help of TestNG Annotations (@BeforeEach – @AfterEach), 
which will be performed before and after each test.
- call Driver and navigate to the URL: "https://regiojet.com" from the configuration.properties file.
- click on "AllowAllButton" by using the method from pageClass for closing the pop-up window.
- the method from pageClass class helps us to fill in information by choosing journey direction.
- "closeDriver" method for closing driver session.

Additional Test Case: Print additional information about connections.
Steps:
1 - Choose the direction
2 - Choose the date
3 - Click the "Search" button
4 - Print additional information (departure time, arrival time, number of stops, and the price of the journey) as an output to the console.
Made with a created custom method "printAdditionInfoTOConsole()" and "log4j2.xml" plugin for a nice look with additional information.

Test Case 1: Find the soonest arrival time to Brno on the next Monday starting from midnight. 
Steps:
1 - Choose the direction
2 - Choose the date
3 - Click the "Search" button
4 - Find the soonest arrival time to Brno from the list of available connections
Made with a created custom method "theSoonestArrivalTime()", with which I was able to extract the arrival time of each train, 
convert the string to a numeric value, and compare, thus determining the soonest arrival time.

Test Case 2: Find the shortest time spent traveling - sitting on the train
1 - Choose the direction
2 - Choose the date
3 - Click the "Search" button
4 - Find the shortest time spent traveling - sitting on the train
Made with a created custom method "the shortest duration()", with the help of which I was able to extract the duration 
of each train's journey, convert the string into a numeric value, and compare, thus determining the shortest journey option.

Test Case 3: Find the lowest price of the journey
1 - Choose the direction
2 - Choose the date
3 - Click the "Search" button
4 - Find the lowest price of the journey
Made with a created custom method "theLowestPrice()", with the help of which I was able to extract the price 
of each train's journey, convert the string into a numeric value, and compare, thus determining the lowest price of the journey.

At the same time, I had to make sure that all the connections that have been found met the input criteria
(departure time, place of departure, place of arrival, and whether it is a direct or indirect connection)
Made with JUnit 5 Assertions custom methods "isMatchWithInputCriteria()" and "isConnectionDirect()".
If it doesn't match test(s) will be failed and will be printed additional message on the console.

Test Cases for Back End Project

In the missing a public API, I went to the Network Tab in Chrome DevTools and there I selected a request, with which we 
can get a list of all available routes according to the specified criteria.
Then I imported this curl link into Postman and, with the help of get request, received a response of all available routes 
in JSON format.
For automation, according to the task I used the RestAssured library, setting the base URL and parameters in the key : value format.
This allowed me to extract the response to this request and display on the console all the information about the routes 
in a meaningful format of travel data using the prettyPrint() method, which was what I needed to do.
Next, we can use deserialization to convert JSON Response to Java Collection/Data structure or convert JSON to Custom Java Classes 
and perform the actions we need, for example, comparing the expected and actual results.
I did it in the task class for Front End test cases, so I won't repeat it.


How To Run The Framework

With The IDE Tool: IntelliJ is recommended
Inside Test Classes:
Open "RegioJet\src\test\java\com\regioJet\tests\FrontEndTest or BackEndTest" or then
Go to the class title and click the "Green Play Button" left side of the "Class Name" or
Go to @Test annotation and click "Green Play Button" left side of the "Method Name"

With The Help of IntelliJ Terminal:
Run Specific Test:
Go to IntelliJ Terminal
Type this command into the terminal:
mvn test -Dtest=”FrontEndTest” 
and hit the "Enter"
It will trigger only specific test classes.

Reports
You can navigate to the file path: "RegioJet\build\reports\tests\test\index.html" and open it with the browser

Bogdan Turchyk – Software Development Engineer in Test
bturchyk@gmail.com
linkedin.com/in/bogdan-turchyk
