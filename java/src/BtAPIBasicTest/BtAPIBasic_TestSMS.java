/*
 * btTelco Basic REST API - Java Implementation
 * 
 * Version 1.21
 * 
 * btTelco - http://www.bttelco.com
 * 
 * A bitTrust Service.
 * http://www.bittrust.net
 * Identikrecord Group
 * Copyright 2013 (C) All rights reserved.
 * 
 */
package BtAPIBasicTest;

import BtAPIBasic.BtAPIBasic;
import java.util.HashMap;

/**
 *
 * @author Joao Paredes
 */
public class BtAPIBasic_TestSMS {
// btTelco API URL     

    private static final String BTTELCO_API_URL = "https://bttelco.bittrust.net/api";
// Update with your sub-account information
    private static final String ACCT_NAME = ""; // Your btTelco sub-account name
    private static final String ACCT_KEY = ""; // Your btTelco sub-account key

// Set the message properties
    private static final String SMS_TO = ""; // Destination number. Do not include any prefix such as 00 nor plus (+) sign
    private static final String SMS_BODY = "Isto e' um teste! plica ' () ~ . O joão é fixe ÀàéÈÓóÚúíÌøêôîûâãẽĩõũ€£§@|\\/,;-_";//"Hello. This is a test message from btTelco. Have a nice day."; // Your message
    // private static final String  SMS_FROM  =  "YourBrand";
    //private static final String  SMS_FROM  = "YourNumber";
    private static final String SMS_FROM = "false";        // When set to false, your account default number will be used as Sender ID

    public static void main(String[] args) throws InterruptedException {
        BtAPIBasic bttelco = new BtAPIBasic(BTTELCO_API_URL, ACCT_NAME, ACCT_KEY);
        HashMap send_data = bttelco.sms.send(SMS_TO, SMS_BODY, SMS_FROM, null);
        
        
        // Check for errors
        if ( send_data == null) {
                // Something went wrong
                System.out.println("Unable to send SMS.");
                return;
        }

        // Retrieve Message ID
        String mId = (String)send_data.get("mid");

        // Check if the message was accepted for delivery
        if (!"ACCEPTED".equals(send_data.get("status"))) {
            // Report that it was rejected
            System.out.println("Message ID " + mId + " was rejected");
            return;
        }

        // Report that it is being delivered
        System.out.println("Message ID " + mId + " was accepted and is being delivered.");

        // Wait some time before checking for delivery receipt
        Thread.sleep(30000);        

        // Check the message status
        HashMap report_data = bttelco.sms.report(new Integer(mId));

        // Check for errors
        if (report_data == null) {
                // Something went wrong
                System.out.println("Unable to retrieve Message ID " + mId + " report.");
                return;
                        }
        // Check if the message was delivered (3 is the DELIVERED status)
        if (!"3".equals(report_data.get("msg_status"))) {
                // Report that message wasn"t delivered yet
                System.out.println("Message ID "+mId+ " was _NOT_ delivered yet (after 30 seconds).");
                return;
        }
        // Report that message was delivered
        System.out.println("Message ID " + mId+ "was delivered");
        
    }
}
