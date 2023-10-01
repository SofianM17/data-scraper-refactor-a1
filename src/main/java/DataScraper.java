import customDataTypes.InterestRate;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
public class DataScraper {
    private ArrayList<ArrayList<String>> interestData = new ArrayList<>();
    private ArrayList<InterestRate> interestRates = new ArrayList<>();
    private String path = System.getProperty("user.dir");
    private final String TARGET_URL = "https://www.nerdwallet.com/ca/banking/best-high-interest-savings-accounts";
    private String htmlContent;

    public ArrayList<ArrayList<String>> getInterestData() {
        return interestData;
    }

    public ArrayList<InterestRate> getInterestRates() {
        return interestRates;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public WebDriver setupChromeDriver() {
        ChromeOptions driverOptions = new ChromeOptions();
        driverOptions.addArguments("--headless");
        driverOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36");
        // specify path to WebDriver exe
        System.setProperty("webdriver.chrome.driver", path+"/src/main/resources/chromedriver-win64/chromedriver.exe");

        return new ChromeDriver(driverOptions);
    }

    public void scrape() {
        WebDriver driver = setupChromeDriver();

        try {
            driver.get(TARGET_URL);
        }
        catch(Exception e) {
            System.err.println(e);
        }

        setHtmlContent(driver.getPageSource());

        Document doc = Jsoup.parse(getHtmlContent());
        extractInterestData(doc);
    }

    public void extractInterestData(Document parsedHtml) {
        ArrayList<String> headerRatePair = new ArrayList<>();

        Elements cardBlocks = parsedHtml.select(".c-block-product-card");

        for (Element el: cardBlocks) {
            String interestRate = processInterestRateMarkup(el);
            String header = el.selectFirst("h3").text();

            headerRatePair.addAll(Arrays.asList(header, interestRate));
            interestData.add(headerRatePair);
            headerRatePair = new ArrayList<>();
        }

        computeInterestRateFloatsToInterestRates();
    }

    public String processInterestRateMarkup(Element element) {
        Element interestRateElement = element.selectFirst(".c-keto-product-card__driver-fee");
        Element filterElement = interestRateElement.selectFirst(".js-tooltip-wrapper");

        String interestRate = interestRateElement.text();

        if (filterElement != null) {
            interestRate = interestRateElement.text().replace(filterElement.text(), "");
        }

        return interestRate;
    }

    public void computeInterestRateFloatsToInterestRates() {
        for (ArrayList<String> pair : interestData) {
            String rateString = pair.get(1).replaceAll("[^0-9.]", "");
            float rate = Float.parseFloat(rateString)/100;
            interestRates.add(new InterestRate(rate));
        }
    }
}
