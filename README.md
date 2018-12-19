# Test Automation Engineer testing task
Test Automation Engineer - UI testing task.

This project contains automation test which checks if the user is in an audience.
And also contains instructions which describe how to run this test in a container.
instances etc.

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
Package <b>(src\main\java\drivers)</b> contains <b>ChromeWebDriver.class</b> which provides us with the possibility to
run tests in Windows, Linux, Mac environments (method - <b>getWebDriverInstance()</b>). 
Drivers are situated in the <b>(src\main\resources\chromedrivers)</b>.
Also, were created custom exceptions <b>(src\main\java\exceptions)</b>.

Package (<b>src\main\java\utils</b>) contains:
<ul>
<li>PropertiesLoader.java - contains method that helps to load properties from property file.</li>
</ul>

Package (<b>src\main\java\ui_methods</b>) contains <b>JsDragAndDrop.java</b> class which provides us with possibility
to emulate drag and drop for Selenium 3 and Html5.

Package <b>src\main\java\api</b> contains class with methods for interactions with API using RestAssured.

<h3>How to run tests in local environment:</h3>

<p>Please open a terminal and run next commands:</p>
<code>https://github.com/Eduard-Za/relay-test.git</code><br/>
<code> cd relay-test</code><br/>
<code> mvn clean test</code><br/>

<p>Please open a terminal and run next commands:</p>
<code>git clone https://github.com/Eduard-Za/UI-Selenium-task.git</code><br/>
<code> cd UI-Selenium-task</code><br/>
<code> mvn clean test</code><br/>
<br> 

<h3>How to run tests in the container</h3>

As host was used Ubuntu 18.10 .

1. Please install Docker on the machine.
https://docs.docker.com/install/linux/docker-ce/ubuntu/#set-up-the-repository

2. Please install Selenoid using next commands:

Selenoid is an implementation of Selenium hub using Docker containers to launch browsers.
No need to manually install browsers.

Please open terminal and run:

<code>wget -O selenoid  https://github.com/aerokube/cm/releases/download/1.5.1/cm_linux_amd64</code>

It's Configuration manger that helps us to install Selenoid.

(Attention!: please use 1.5.1 version).

3.Please run <code>chmod +x ~/selenoid</code>

4.Please run<code>./selenoid selenoid start --vnc</code>

This command will download Selenoid release, browser images, generate new browsers.json, restart Selenoid and download docker images with VNC support (please see <b>enable vnc</b> property in the desiredCapabilities).

5. Please run <code>./cm selenoid-ui start</code>

This command will start status page with UI updates by SSE, backed by constant polling of status handle of selenoid on small go backend.

6. Please run <code>git clone https://github.com/Eduard-Za/Candidate-task.git</code>.
7.

