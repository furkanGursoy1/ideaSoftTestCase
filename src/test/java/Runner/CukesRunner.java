package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json", "html:target/default-html-reports", "rerun:target/rerun.txt"},
        publish = true,
        monochrome = true,
        features = "src/test/resources/features",
        glue = "StepDefinitions",
        dryRun = false,
        tags = "@reg" //calıstırmak istenilen senaryonun tagname yazılır.

)
public class CukesRunner {
}