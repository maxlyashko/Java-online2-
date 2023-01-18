package ua.lyashko.module3.entityUtil;

import ua.lyashko.module3.entity.Abonent;
import ua.lyashko.module3.entity.InternetActivity;
import ua.lyashko.module3.entity.RingActivity;
import ua.lyashko.module3.entity.SmsActivity;

import java.util.Arrays;
import java.util.Random;

public class ActivityUtil {
    private static final Random random = new Random ( );

    public static InternetActivity createInternetActivity ( Abonent abonent ) {
        return new InternetActivity (
                "Searching in the Internet" , abonent , random.nextInt ( 1 , 5000 ) );
    }


    public static RingActivity createRingActivity ( Abonent abonent ) {
        return new RingActivity ( "Calling" , abonent , createNumber ( ) );
    }

    public static SmsActivity createSmsActivity ( Abonent abonent ) {
        return new SmsActivity ( "Sent sms" , abonent , createNumber ( ) , createMessage ( ) );
    }

    public static String createNumber () {
        int[] digits = new int[12];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = random.nextInt ( 0 , 10 );
        }
        String str = "+" + Arrays.toString ( digits );
        return str
                .replace ( "[" , "" )
                .replace ( "," , "" )
                .replace ( " " , "" )
                .replace ( "]" , "" );
    }


    private static String createMessage () {
        String[] subject = {"I" , "My Friend" , "Man" , "Woman" , "They"};
        String[] action = {"read" , "eat" , "tell" , "bombed" , "draw"};
        String[] object = {"a sandwich" , "an essay" , "a book" , "a house"};

        String[] message = {subject[random.nextInt ( subject.length )] ,
                action[random.nextInt ( action.length )] ,
                object[random.nextInt ( object.length )]};
        return Arrays.toString ( message )
                .replace ( "[" , "" )
                .replace ( "," , "" )
                .replace ( "]" , "" );
    }
}
