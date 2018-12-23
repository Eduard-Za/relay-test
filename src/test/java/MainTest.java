import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.AudiencePage;
import pages.EngagementPage;
import pages.Header;
import scheme.Enters;

import java.util.List;

import static api.ApiInterractions.checkUserEntered;
import static api.ApiInterractions.emulateVisiting;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static pages.LoginPage.login;

public class MainTest extends BaseTest {

    private static final Log LOG = LogFactory.getLog(MainTest.class);

    @Test
    public void checkUserIsInAudience() {

        login();
        AudiencePage audiencePage = Header.toDataManagment(driver);
        Header.toEngagements();
        EngagementPage.createEngagement();
        Header.toAudiences(driver);
        audiencePage.createAudience();

        emulateVisiting();
        List<Enters> responseFromApi = checkUserEntered();

        LOG.info(Assertion.class + " Asserts");

        String audinceApiNameFromPage = audiencePage.getApiName();
        String audienceNameFromApi = responseFromApi.get(0).getSegmentName();
        String audienceNumberFromApi = responseFromApi.get(0).getSegmentNumber().toString();

        LOG.info("Assert: " + audienceNameFromApi + " contains " + " " + audinceApiNameFromPage);
        assertThat(audinceApiNameFromPage, containsString(audienceNameFromApi));

        LOG.info("Assert: " + audienceNameFromApi + " contains " + " " + audienceNumberFromApi);
        assertThat(audinceApiNameFromPage, containsString(audienceNumberFromApi));

        LOG.info(Assertion.class.getSimpleName() + " Successfull asserts");
    }


}
