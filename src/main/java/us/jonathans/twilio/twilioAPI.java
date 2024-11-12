package us.jonathans.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

// Sends user an SMS to update them on changes to their position on the leaderboard

public class twilioAPI {

    public static final String ACCOUNT_SID = "UserAuthSID";
    public static final String AUTH_TOKEN = "UserAuthToken";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try {
            Message message = Message.creator(
                    new PhoneNumber(""), // Recipient's number
                    new PhoneNumber("+14082170234"), // Your Twilio number
                    "Hello from Java!"
            ).create();

            System.out.println("Message sent with SID: " + message.getSid());
        } catch (Exception e) {
            System.err.println("Twilio test failed: " + e.getMessage());
        }
    }
}
