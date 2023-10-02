import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DataScraper {
    private String path = System.getProperty("user.dir");
    private final String TARGET_URL = "https://www.nerdwallet.com/ca/banking/best-high-interest-savings-accounts";
    private String htmlContent;

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
    }
}
