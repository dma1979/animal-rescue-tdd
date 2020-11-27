package features;

import features.fixtures.HomePageFixture;
import features.fixtures.LoginPageFixture;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

	@Bean
	WebDriver webDriver() {

		//Activate web-driver or set it in PATH
		//here 'geckodriver.exe' is located on the root level of project just as an example
		System.setProperty("webdriver.gecko.driver", "..\\geckodriver.exe");
		boolean isHeadless = !System.getenv().containsKey("SHOW_BROWSER");
		return new FirefoxDriver(new FirefoxOptions().setHeadless(isHeadless));
	}

	@Bean
	HomePageFixture homePageFixture(WebDriver webDriver) {
		String envUrl = System.getenv().getOrDefault("ENV_URL", "http://localhost:3100/rescue");
		return new HomePageFixture(webDriver, envUrl);
	}

	@Bean
	LoginPageFixture loginPageFixture(WebDriver webDriver) {
		return new LoginPageFixture(webDriver);
	}
}
