package ui_methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;


/**
 * Drag and drop - JS Work Around for Selenium 3 and Html5;
 */


public class JsDragAndDrop {

    public static void dragAndDrop(WebDriver driver, WebElement from, WebElement to) {
        String script = "function createEvent(typeOfEvent) {" + "var event =document.createEvent(\"CustomEvent\");"
                + "event.initCustomEvent(typeOfEvent,true, true, null);" + "event.dataTransfer = {" + "data: {},"
                + "setData: function (key, value) {" + "this.data[key] = value;" + "},"
                + "getData: function (key) {" + "return this.data[key];" + "}" + "};" + "return event;"
                + "}" + "function dispatchEvent(element, event,transferData) {"
                + "if (transferData !== undefined) {" + "event.dataTransfer = transferData;" + "}"
                + "if (element.dispatchEvent) {" + "element.dispatchEvent(event);"
                + "} else if (element.fireEvent) {" + "element.fireEvent(\"on\" + event.type, event);" + "}"
                + "}" + "function simulateHTML5DragAndDrop(element, destination) {"
                + "var dragStartEvent =createEvent('dragstart');" + "dispatchEvent(element, dragStartEvent);"
                + "var dropEvent = createEvent('drop');"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);"
                + "var dragEndEvent = createEvent('dragend');"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);" + "}"
                + "var source = arguments[0];" + "var destination = arguments[1];"
                + "simulateHTML5DragAndDrop(source,destination);";

        (((JavascriptExecutor) driver)).executeScript(script, from, to);
    }

}
