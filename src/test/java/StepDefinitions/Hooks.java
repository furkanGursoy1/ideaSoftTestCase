package StepDefinitions;

import Utilities.ConfigurationReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import Utilities.Driver;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.Timestamp;
import java.time.Duration;

public class Hooks {

    @Before
    public void setUp() {
        String url = ConfigurationReader.get("url");
        System.out.println(new Timestamp(System.currentTimeMillis()));
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Driver.get().manage().window().maximize();
        Driver.get().get(url);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        scenario.log("SON URL: " + Driver.get().getCurrentUrl());
        scenario.log("TEST BİTİŞ = " + new Timestamp(System.currentTimeMillis()));

        Driver.get().manage().deleteAllCookies();
        Driver.closeDriver();
    }
}
