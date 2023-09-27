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
    private ArrayList<Float> interestRateFloats = new ArrayList<>();
    private String path = System.getProperty("user.dir");

    public ArrayList<ArrayList<String>> getInterestData() {
        return interestData;
    }

    public ArrayList<Float> getInterestRateFloats() {
        return interestRateFloats;
    }

    public void scrape() {
        ArrayList<String> headerRatePair = new ArrayList<>();

        ChromeOptions driverOptions = new ChromeOptions();
        driverOptions.addArguments("--headless");
        driverOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36");
        // specify path to WebDriver exe
        System.setProperty("webdriver.chrome.driver", path+"/src/main/resources/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver(driverOptions);

        try {
            driver.get("https://www.nerdwallet.com/ca/banking/best-high-interest-savings-accounts");
        }
        catch(Exception e) {
            System.err.println(e);
        }

        String htmlContent = driver.getPageSource();

        Document doc = Jsoup.parse(htmlContent);

        Elements cardBlocks = doc.select(".c-block-product-card");

        for (Element el: cardBlocks) {
            String interestRate;
            String header = el.selectFirst("h3").text();
            Element interestRateElement = el.selectFirst(".c-keto-product-card__driver-fee");
            Element filterElement = interestRateElement.selectFirst(".js-tooltip-wrapper");

            if (filterElement != null) {
                interestRate = interestRateElement.text().replace(filterElement.text(), "");
            } else {
                interestRate = interestRateElement.text();
            }

            headerRatePair.addAll(Arrays.asList(header, interestRate));
            interestData.add(headerRatePair);
            headerRatePair = new ArrayList<>();
        }

        for (ArrayList<String> pair : interestData) {
            String rateString = pair.get(1).replaceAll("[^0-9.]", "");
            float rate = Float.parseFloat(rateString)/100;
            interestRateFloats.add(rate);
        }
    }


}
