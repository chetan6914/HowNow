import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions (
        features = {"src\\test\\Resources\\Feature"},
        tags = {"@test1"}
//        glue = {"java", "Resources"}
)

public class Runner {
}
