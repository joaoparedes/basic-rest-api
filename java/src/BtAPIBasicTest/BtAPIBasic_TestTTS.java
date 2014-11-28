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
public class BtAPIBasic_TestTTS {
// btTelco API URL     

    private static final String BTTELCO_API_URL = "https://bttelco.bittrust.net/api";
// Update with your sub-account information
    private static final String ACCT_NAME = ""; // Your btTelco sub-account name
    private static final String ACCT_KEY = ""; // Your btTelco sub-account key

// Set the message properties
    private static final String TTS_TO = ""; // Destination number. Do not include any prefix such as 00 nor plus (+) sign
    private static final String TTS_BODY = "Hello. This is a test message from B T Telco. Have a nice day."; // Your message
    private static final String TTS_FROM = "351300500490";//"false";        // When set to false, your account default number will be used as Sender ID

    private static final int TTS_LANGUAGE = 1;//"false"; // Set to 1 for en-US, 2 for en-UK, 20 for pt-PT, etc. When false, en-US will be used
    private static final int TTS_GENDER = 1;//"false"; // Set to 1 for Male voice, 2 for Female voice. When false, Male will be the default

    public static void main(String[] args) throws InterruptedException {
        BtAPIBasic bttelco = new BtAPIBasic(BTTELCO_API_URL, ACCT_NAME, ACCT_KEY);
        // Send the TTS
        HashMap<String, String> send_data = bttelco.tts.send(TTS_TO, TTS_BODY, TTS_FROM, TTS_LANGUAGE, TTS_GENDER, null);
        // send_data = bttelco.tts.send(TTS_TO, TTS_BODY); // This is also valid. Default values will be used.

        // Check for errors
        if (send_data == null) {
            // Something went wrong
            System.out.println("Unable to send TTS.");
            return;
        }

        // Retrieve Message ID
        String mId = (String) send_data.get("mid");

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
            // Report that message wasn't delivered yet
            System.out.println("Message ID " + mId + " was _NOT_ delivered yet (after 30 seconds).");
            return;
        }
        // Report that message was delivered
        System.out.println("Message ID " + mId + " was delivered");

    }
}