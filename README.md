# Test Automation Engineer testing task

This project contains an automation test which checks if the user is in an audience and instructions which describe how to run this test in a container, how to install environment.

<h3>Technology stack includes:</h3>
<ul>
<li>Java 8</li>
<li>Maven</li>
<li>Selenide wrapper for Selenium</li>
<li>TestNG</li>
<li>Lombok</li>
<li>Gson</li>
<li>SLF4J</li>
<li>Hamcrest</li>
<li>RestAssured</li>
<li>Selenoid</li>
<li>Docker</li>
</ul>

<h3>Description of project structure</h3>

Test <b>(src\test\java\MainTest.java)</b> was written using Selenide and Page Object pattern <b>(src\main\java\pages)</b>.
Package <b>(src\main\java\drivers)</b> contains <b>ChromeWebDriver.class</b> which contains static <b>getWebDriverInstance()</b> method and provides us with the possibility to run tests in Windows, Linux, Mac environments. 
Drivers are situated in the <b>(src\main\resources\chromedrivers)</b> package.
Also, were created custom exceptions <b>(src\main\java\exceptions)</b>.

Package (<b>src\main\java\utils</b>) contains: PropertiesLoader.java - contains a method that helps to load properties from a property file.


Package (<b>src\main\java\ui_methods</b>) contains <b>JsDragAndDrop.java</b> class which contains method <b>dragAndDrop(WebDriver driver, WebElement from, WebElement to)</b> that provides us with a possibility to emulate drag and drop for Selenium 3 and Html5.

Package <b>(src\main\java\api)</b> contains a class with methods for interactions with API using RestAssured.

<h3>How to run tests in local environment:</h3>

<p>Please open a terminal and run next commands:</p>
<code>https://github.com/Eduard-Za/relay-test.git</code><br/>
<code> cd relay-test</code><br/>
<code> mvn clean test</code><br/>

<h3>How to run tests in the container</h3>

Host - Ubuntu 18.10.

1. Please install Docker on the machine.<br>
https://docs.docker.com/install/linux/docker-ce/ubuntu/#set-up-the-repository

2. Selenoid is an implementation of Selenium hub using Docker containers to launch browsers.

Please open terminal and run:

<code>wget -O cm https://github.com/aerokube/cm/releases/download/1.5.6/cm_linux_amd64</code>

It's Configuration Manager that helps us to install Selenoid.

3. Please run <code>chmod +x ./cm</code>

4. Please run<code>sudo ./cm selenoid start --vnc</code>

This command will download Selenoid release.

5. Please run <code>sudo ./cm selenoid-ui start</code>

This command will start status page with UI updates by SSE, backed by constant polling of status handle of selenoid on small go backend.
![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/image.png)

6. Please run <code>docker pull selenoid/vnc:chrome_70.0</code><br>
and after that <code>docker pull selenoid/vnc:firefox_64.0</code>.

We will use chrome_70.0 and firefox_64.0 for testing.(PS: It's a temporary desicion. We can download much more images).

7. Please check your result with result from image:</br>
![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/Screenshot%20from%202018-12-20%2000-12-55.png)


8. Please run <code>git clone https://github.com/Eduard-Za/relay-test.git</code><br>
          and <code>cd relay-test</code>.
          
9. Please run test using <code>mvn clean test -Dbrowser="browser" -DbrowserVersion="browserVersion"</code>
<br>where:</br>
<ul>
          <li>browser - browser name;</li>
          <li>browserVersion - version of browser;</li>
</ul>

Please see example:
![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/part1.png)
![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/part2.png)


10. During the execution we can see the test run using selenoid-ui(by default: localhost:8080):

![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/Screenshot%20from%202018-12-20%2000-35-56.png)
![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/Screenshot%20from%202018-12-20%2000-37-22.png)

Please, pay attention that in the code, I specified:
<code>desiredCapabilities.setCapability("enableVNC", true);
      desiredCapabilities.setCapability("enableVideo", false);</code>
      
We also can use these values as parametrs and for exaple we can enable video recording for session through.    


