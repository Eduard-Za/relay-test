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
Package <b>(src\main\java\drivers)</b> contains <b>WebDriverInstance.class</b> which contains static <b>getLocalDriver()</b> method and provides us with the possibility to run tests in Windows, Linux, Mac environments. 
Drivers are situated in the <b>(src\main\resources\drivers)</b> package.
Also, were created custom exceptions <b>(src\main\java\exceptions)</b>.

Package (<b>src\main\java\utils</b>) contains: PropertiesLoader.java class which include a method that helps to load properties from a property file.


Package (<b>src\main\java\ui_methods</b>) contains <b>JsDragAndDrop.java</b> class which contains method <b>dragAndDrop(WebDriver driver, WebElement from, WebElement to)</b> that provides us with a possibility to emulate drag and drop for Selenium 3 and Html5.

Package <b>(src\main\java\api)</b> contains a class with methods for interactions with API using RestAssured.

<h3>How to run tests in the local environment:</h3>

<p>Please open a terminal and run next commands:</p>
<code>https://github.com/Eduard-Za/relay-test.git</code><br/>
<code> cd relay-test</code><br/>
<code> mvn clean test</code><br/>

<h3>How to run tests in the container</h3>

<h3>Solution 1 - via Dockerfile configuration:</h3>
1. Please install Docker on the machine.
https://docs.docker.com/install/linux/docker-ce/ubuntu/#set-up-the-repository</br>
2. Please install VNC viewer.https://www.realvnc.com/en/connect/download/viewer/</br>
3. Please run <code>git clone https://github.com/Eduard-Za/relay-test.git</code>
              <code>cd relay-test</code>.</br>
4. Please run <code>sudo docker build . -t relay-test:0.0.1 && docker run -p 6080:80 -p 5900:5900 relay-test:0.0.1</code></br>

As base image, I used <b>dorowu/ubuntu-desktop-lxde-vnc</b>. That docker image provides HTML5 VNC interface to access Ubuntu. After execution our container will start and our vnc server, which situated in the container, start too.
VNC port - <b>5090</b>. </br>

(P.S: If you want to start test using headless firefox, please uncomment and run:</br>
<code># USE IF YOU WANT TO RUN IN HEADLESS MODE</code></br>
<code>#RUN ["chmod", "+x", "./headless.sh"]</code></br>
<code>#ENTRYPOINT ["./headless.sh"]</code> in the Dockerfile.</br>
Please, see screenshot).

![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/Screenshot%20from%202018-12-24%2006-05-11.png)


5. Please connect VNC viewer to the container (we specified standart 5900 port for vnc viewer).

6. Please open terminal, run to the working directory and run next command to start test:

<code>docker exec -it $(docker ps -lq) sh -c "x11vnc -display :99 -localhost & export DISPLAY=:1 && mvn clean test" </code>

You can observe test run using VNC viewer (please, see screenshots):

![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/Screenshot%20from%202018-12-24%2006-29-25.png)


(If you need several instances - you can generate several instances just changing port number.For example:</br>
<code>docker run -p 6081:80 -p 5901:5900 relay-test:0.0.1</code>) .


<h3>Solution 2 - using Selenoid (at the current moment for Ubuntu only):</h3>

Host - Ubuntu 18.10.

<b>BEFORE EXPLANATION:</b>

You can run test using only one simple bash script - <b>script.sh</b>

Please run <code>git clone https://github.com/Eduard-Za/relay-test.git</code><br>
           <code>cd relay-test</code><br>
           <code>sudo chmod +x script.sh</code>.<br>
           <code>./script.sh browsername numberOfVersion</code><br> 
           
           Please use: ./script.sh chrome 70.0
              
              
<b>Or you can use step by step description:</b>


1. Please install Docker on the machine.
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


10. During the execution, we can see the test run using selenoid-ui(by default: localhost:8080):

![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/Screenshot%20from%202018-12-20%2000-35-56.png)
![alt](https://github.com/Eduard-Za/relay-test/blob/master/images/Screenshot%20from%202018-12-20%2000-37-22.png)

Please, pay attention that in the code, I specified:
<code>desiredCapabilities.setCapability("enableVNC", true);
      desiredCapabilities.setCapability("enableVideo", false);</code>
      
We also can use these values as parameters and for example, we can enable video recording for a session through a command line.

<b>IF SOMETHING GOES WRONG</b>
Please use <br>
<code>docker stop selenoid</code><br>
<code>docker stop selenoid-ui</code><br>
<code>./cm selenoid start --force</code>

please, resolve conflicts and run test again.

If you have any questions, please ask eduardos1218@gmail.com .
