package ua.lyashko.module3.systemUtil;

import java.util.Scanner;

public class SystemRunner {
    private static final Scanner scanner = new Scanner ( System.in );

    public static void systemInteractions () {
        boolean exit = false;
        while ( !exit ) {
            System.out.println ( """
                    Choose interaction: \s
                    1. Search by sms\s
                    2. Top 5 abonents which consume most of calls\s
                    3. Top 5 abonents which consume most of sms\s
                    4. Top 5 abonents which consume most of internet\s
                    5. Most popular service\s
                    6. Most popular device which is used on the network\s
                    or 0 to exit""" );
            switch (scanner.nextLine ( )) {
                case "1" -> System.out.println ( SystemUtil.smsSearch ( scanner.nextLine ( ) ) );
                case "2" -> System.out.println ( SystemUtil.mostCallsConsumers ( ) );
                case "3" -> System.out.println ( SystemUtil.mostSmsConsumers ( ) );
                case "4" -> System.out.println ( SystemUtil.mostInternetConsumers ( ) );
                case "5" -> System.out.println ( SystemUtil.showTheMostPopularService ( ) );
                case "6" -> System.out.println ( SystemUtil.showTheMostPopularDevice ( ) );
                case "0" -> exit = true;
                default -> System.out.println ( "wrong input" );
            }
        }
    }
}
