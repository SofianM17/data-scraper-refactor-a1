import customDataTypes.InterestRate;
import customDataTypes.Money;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataScraper scraper = new DataScraper();
        InterestGrowthCalculator growthCalculator;
        CompoundInterestCalculator compoundedCalculator;

        scraper.scrape();
        ArrayList<ArrayList<String>> interestData = scraper.getInterestData();
        ArrayList<InterestRate> interestRateFloats = scraper.getInterestRates();

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
                growthCalculator = new InterestGrowthCalculator(rate, term);
                compoundedCalculator = new CompoundInterestCalculator(rate, term);

                System.out.println("--------------------------------------------------\n");
                System.out.println(bankInfo + "\n" + rateInfo + "\n");
                growthCalculator.calculateGrowth(principal);
                System.out.println("\n");
                compoundedCalculator.calculateCompoundInterest(principal);
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
