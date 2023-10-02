import customDataTypes.InterestRate;
import customDataTypes.Money;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataScraper scraper = new DataScraper();
        ScrapedDataParser dataParser = new ScrapedDataParser();

        InterestCalculator interestCalculator;

        scraper.scrape();
        dataParser.extractInterestData(scraper.getHtmlContent());

        ArrayList<ArrayList<String>> interestData = dataParser.getInterestData();
        ArrayList<InterestRate> interestRateFloats = dataParser.getInterestRates();

        String bankInfo;
        String rateInfo;

        System.out.println("--- BEST HYSA ACCOUNTS IN CANADA FOR 2023 ---");
        System.out.println("Enter a principal monetary amount as the first arg to see the growth, per account, up to 5 years\n");

        if (args.length == 1) {
            Money principal = new Money(Float.parseFloat(args[0]));
            int term = 5;
            InterestRate rate;

            for (int i=0; i < interestData.size(); i++) {
                bankInfo = interestData.get(i).get(0);
                rateInfo = interestData.get(i).get(1);
                rate = interestRateFloats.get(i);
                interestCalculator = new InterestCalculator(rate, term);

                System.out.println("--------------------------------------------------\n");
                System.out.println(bankInfo + "\n" + rateInfo + "\n");
                interestCalculator.calculateInterest(principal, false);
                System.out.println("\n");
                interestCalculator.calculateInterest(principal, true);
                System.out.println("\n");
            }
        } else {
            for (ArrayList<String> pair : interestData) {
                bankInfo = pair.get(0);
                rateInfo = pair.get(1);

                System.out.println(bankInfo + "\n" + rateInfo + "\n--------------------");
            }
        }
    }
}
