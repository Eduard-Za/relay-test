import org.testng.annotations.Test;
import pages.AudiencePage;
import pages.EngagementPage;
import pages.Header;
import pages.LeftMenu;
import scheme.Enters;

import java.util.List;

import static api.ApiInterractions.checkUserEntered;
import static api.ApiInterractions.emulateVisiting;
import static constants.Type.AUDIENCES;
import static constants.Type.ENGAGEMENTS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static pages.AudiencePage.audienceNameValue;
import static pages.EngagementPage.engagementName;
import static pages.LoginPage.login;

public class MainTest extends BaseTest {


    @Test
    public void checkUserIsInAudience() {

        logger.info("Test");
        login();
        AudiencePage audiencePage = Header.toDataManagment(driver);
        LeftMenu.deleteIfPresent(audienceNameValue, AUDIENCES);
        Header.toEngagements();
        LeftMenu.deleteIfPresent(engagementName, ENGAGEMENTS);
        EngagementPage.createEngagement();
        Header.toAudiences(driver);
        audiencePage.createAudience();

        logger.info("Interractions with API");
        emulateVisiting();
        List<Enters> responseFromApi = checkUserEntered();

        String audinceApiNameFromPage = audiencePage.getApiName();
        String audienceNameFromApi = responseFromApi.get(0).getSegmentName();
        String audienceNumberFromApi = responseFromApi.get(0).getSegmentNumber().toString();

        logger.info("Asserts");
        assertThat(audinceApiNameFromPage, containsString(audienceNameFromApi));
        assertThat(audinceApiNameFromPage, containsString(audienceNumberFromApi));
        logger.info("Passed");

    }


}
