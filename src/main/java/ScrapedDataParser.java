import customDataTypes.InterestRate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class ScrapedDataParser {
    private ArrayList<ArrayList<String>> interestData = new ArrayList<>();
    private ArrayList<InterestRate> interestRates = new ArrayList<>();

    public ArrayList<ArrayList<String>> getInterestData() {
        return interestData;
    }

    public ArrayList<InterestRate> getInterestRates() {
        return interestRates;
    }

    public void extractInterestData(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);

        ArrayList<String> headerRatePair = new ArrayList<>();

        Elements cardBlocks = doc.select(".c-block-product-card");

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
