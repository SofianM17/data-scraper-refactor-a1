import customDataTypes.InterestRate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    private static ScrapedDataParser dataParser;
    private ArrayList<ArrayList<String>> interestData = dataParser.getInterestData();
    private ArrayList<InterestRate> interestRateFloats = dataParser.getInterestRates();

    @BeforeAll
    public static void scrapeData() {
        dataScraper = new DataScraper();
        dataParser = new ScrapedDataParser();

        dataScraper.scrape();
        dataParser.extractInterestData(dataScraper.getHtmlContent());
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
    public void testProductCardExists() {
        // product cards contain interest rate data. They must exist for the program to work expectedly.
        Document parsedHtml = Jsoup.parse(dataScraper.getHtmlContent());
        Element cardBlock = parsedHtml.selectFirst(".c-block-product-card");
        assertNotNull(cardBlock);
    }

    @Test
    public void testWebDriverSetup(){
        WebDriver driver = dataScraper.setupChromeDriver();
        assertNotNull(driver);
    }

    @Test
    public void testValidHtmlContent() {
        // after parsing, htmlContent should not be null
        assertNotNull(dataScraper.getHtmlContent());
    }

    @Test
    public void testWebDriverRequestFailure() {
        ChromeOptions driverOptions = new ChromeOptions();
        driverOptions.addArguments("--headless");
        WebDriver webDriver = new ChromeDriver(driverOptions);

        String invalidUrl = "https://www.invalid.com/invalid";
        assertThrows(WebDriverException.class, ()->{webDriver.get(invalidUrl);});
    }

    @Test
    public void testInterestRateFloatsInRange() {
        for (InterestRate rate : interestRateFloats) {
            // interest rates in the data source range between 4% and 6%
            // the selected range (0, 1] provides room for the data to be adjusted in the future
            // at the source scraped while ensuring that the float calculations for rates are still realistic for 2023.
            assertTrue(0 < rate.getAmount() && rate.getAmount() <= 1);
        }
    }
}
