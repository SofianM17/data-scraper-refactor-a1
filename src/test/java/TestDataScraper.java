import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestDataScraper {
    private static DataScraper dataScraper;
    private ArrayList<ArrayList<String>> interestData = dataScraper.getInterestData();
    private ArrayList<Float> interestRateFloats = dataScraper.getInterestRateFloats();

    @BeforeAll
    public static void scrapeData() {
        dataScraper = new DataScraper();
        dataScraper.scrape();
    }

    @Test
    public void testInterestDataPopulated(){
        // after scraping, interestData should be populated
        assertFalse(interestData.isEmpty());
    }

    @Test
    public void testInterestRateFloatsPopulated() {
        // after scraping, interestRateFloats should be populated
        assertFalse(interestRateFloats.isEmpty());
    }

    @Test
    public void testDriverRequestFailure() {
        ChromeOptions driverOptions = new ChromeOptions();
        driverOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(driverOptions);

        String invalidUrl = "https://www.invalid.com/invalid";
        assertThrows(WebDriverException.class, ()->{webDriver.get(invalidUrl);});
    }

    @Test
    public void testInterestRateFloatsInRange() {
        for (float rate : interestRateFloats) {
            // interest rates in the data source range between 4% and 6%
            // the selected range (0, 1] provides room for the data to be adjusted in the future
            // at the source scraped while ensuring that the float calculations for rates are still realistic for 2023.
            assertTrue(0 < rate && rate <= 1);
        }
    }
}
