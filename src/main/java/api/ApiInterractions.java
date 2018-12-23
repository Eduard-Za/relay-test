package api;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import scheme.Enters;
import utils.PropertiesLoader;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


/**
 * Class that contains methods for interractions with API
 *
 * @author Eduard Zaretski
 */

public class ApiInterractions {
    private static UUID uuid;

    private static final Log LOG = LogFactory.getLog(ApiInterractions.class);


    /**
     * Static method emulates a visit.
     */
    public static void emulateVisiting() {
        RestAssured.baseURI = PropertiesLoader.loadProperty("url.emulate");
        String paramName = PropertiesLoader.loadProperty("engagement.name");
        String unixTime = Long.toString(System.currentTimeMillis());
        uuid = UUID.randomUUID();
        Response response = given()
                .queryParam("i", uuid.toString())
                .queryParam("e", "true")
                .queryParam("et", paramName)
                .queryParam("cb", unixTime)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();
        LOG.info( "Emulate visiting: " + ApiInterractions.class.getSimpleName() + " Response with code "
                + response.getStatusCode());
    }

    /**
     * Static method that returns information about demoshop, audience, API identifier.
     *
     * @return entersList - list with responses.
     */
    public static List<Enters> checkUserEntered() {
        RestAssured.baseURI = PropertiesLoader.loadProperty("url.inf");
        String username = PropertiesLoader.loadProperty("credentials.username");
        String password = PropertiesLoader.loadProperty("credentials.password");
        Response response = given()
                .queryParam("partnerId", uuid.toString())
                .auth().basic(username, password)
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(JSON)
                .extract()
                .response();
        Enters[] arrayEnters = response.as(Enters[].class);
        List<Enters> entersList = Arrays.asList(arrayEnters);
        LOG.info(ApiInterractions.class.getSimpleName() + " Response with code "
                + response.getStatusCode() + " and response body as list of " + entersList.getClass().getSimpleName());
        return entersList;
    }
}
