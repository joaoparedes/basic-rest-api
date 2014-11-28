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
package BtAPIBasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Joao Paredes
 */
public class BtRestAPI {

    private String apiUrl;
    private String acctName;
    private String acctKey;

    public BtRestAPI(String apiUrl, String acctName, String acctKey) {
        this.apiUrl = apiUrl + "/index.php";
        this.acctName = acctName;
        this.acctKey = acctKey;
        // Sets the Authenticator to be used everytime a connection it a HTTPS server is made by the API
        Authenticator.setDefault(new BtAuthenticator(this.acctName, this.acctKey));
    }

    private HashMap<String, String> convertAPIResponseToDictionary(APIJsonResponse Obj) {
        HashMap<String, String> DictObj = new HashMap<String, String>();

        DictObj.put("status", Obj.getStatus());
        DictObj.put("reason", Obj.getReason());
        DictObj.put("type", Obj.getType());
        DictObj.put("mid", Obj.getMid() == null ? null : Obj.getMid().toString());
        DictObj.put("to", Obj.getTo());
        DictObj.put("from", Obj.getFrom());
        DictObj.put("message", Obj.getMessage());
        DictObj.put("cost", Obj.getCost() == null ? null : Obj.getCost().toString());
        DictObj.put("country", Obj.getCountry());
        DictObj.put("sent_time", Obj.getSent_time());
        DictObj.put("delivered_time", Obj.getDelivered_time());
        DictObj.put("time", Obj.getTime());
        DictObj.put("msg_status", Obj.getMsg_status() == null ? null : Obj.getMsg_status().toString());
        DictObj.put("rate_discount", Obj.getRate_discount() == null ? null : Obj.getRate_discount().toString());
        DictObj.put("free_minutes", Obj.getFree_minutes() == null ? null : Obj.getFree_minutes().toString());

        return DictObj;
    }

    private HashMap<String, String> requestUrl(String url) {
        HashMap<String, String> ret = null;
        APIJsonResponse aPIJsonResponse = null;
        StringBuilder response = null;
        try {
            URL iurl = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) iurl.openConnection();
            // TODO: remove from here..only need to be setup once
//            Authenticator.setDefault(new BtAuthenticator(this.acctName, this.acctKey));
//            con.setRequestProperty("User-Agent", "Python-urllib/2.6");
            con.connect();
            System.out.println(con.toString());
            InputStreamReader is = null;
            if (con.getResponseCode() >= 400) {
                is = new InputStreamReader(con.getErrorStream());
            } else {
                is = new InputStreamReader(con.getInputStream());
            }
            BufferedReader br = new BufferedReader(is);
            String input;
            response = new StringBuilder();
            System.out.println("This is the response!");
            while ((input = br.readLine()) != null) {
                System.out.println(input);
                response.append(input);
            }
            br.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(BtRestAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BtRestAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        aPIJsonResponse = APIJsonResponseParser.parser(response.toString());

        // Could not get a valid response
        if (aPIJsonResponse == null) {
            return null;
        }
        ret = convertAPIResponseToDictionary(aPIJsonResponse);

        return ret;

    }

    public HashMap<String, String> requestSms(String operation, String to, String message, String senderID, String timeStamp, Integer messageID) {
        String Url = this.apiUrl + "/sms";

        if (senderID == null || "".equals(senderID)) {
            senderID = "false";
        }

        if (timeStamp == null || "".equals(timeStamp)) {
            timeStamp = "false";
        }

        if (null == messageID || messageID == 0) {
            Url += "/" + operation + "/" + this.acctName + "/" + this.acctKey + "/"
                    + to + "/" + EncodingUtil.encodeURIComponent(message) + "/" + senderID + "/"
                    + timeStamp;
        } else {
            Url += "/" + operation + "/" + this.acctName + "/" + this.acctKey + "/"
                    + messageID.toString();
        }

        return this.requestUrl(Url);
    }

    //TODO:
    public HashMap<String, String> requestUssd(String operation, String to, String message,
            String senderID, String timeStamp, Integer messageID)
                {
                        String Url = this.apiUrl + "/ussd";

        if (senderID == null || "".equals(senderID)) {
            senderID = "false";
        }

        if (timeStamp == null || "".equals(timeStamp)) {
            timeStamp = "false";
        }

        if (messageID == null || messageID == 0) {
            Url += "/" + operation + "/" + this.acctName + "/" + this.acctKey + "/"
                    + to + "/" + EncodingUtil.encodeURIComponent(message) + "/" + senderID + "/"
                    + timeStamp;
        } else {
            Url += "/" + operation + "/" + this.acctName + "/" + this.acctKey + "/"
                    + messageID.toString();
        }

        return this.requestUrl(Url);
    }
    //TODO:
    public HashMap<String, String> requestTts(String operation, String to, String message,String from 
        , Integer language, Integer gender, String timeStamp, Integer messageID) {
        String url = this.apiUrl + "/tts";
        String langStr;
        String genderStr;

        if (from == null) {
            from = "false";
        }

        if (language == null || language == 0) {
            langStr = "false";
        } else {
            langStr = language.toString();
        }

        if (gender == null || gender == 0) {
            genderStr = "false";
        } else {
            genderStr = gender.toString();
        }

        if (timeStamp == null || "".equals(timeStamp)) {
            timeStamp = "false";
        }

        if (messageID == null || messageID == 0) {
            url += "/" + operation + "/" + this.acctName + "/" + this.acctKey + "/"
                    + to + "/" + EncodingUtil.encodeURIComponent(message) + "/" + from + "/"
                    + langStr + "/" + genderStr + "/" + timeStamp;
        } else {
            url += "/" + operation + "/" + this.acctName + "/" + this.acctKey + "/"
                    + messageID.toString();
        }

        return this.requestUrl(url);
    }
    private class BtAuthenticator extends Authenticator {

        private String acctName;
        private String acctKey;

        public BtAuthenticator(String acctName, String acctKey) {
            this.acctName = acctName;
            this.acctKey = acctKey;
        }

        /**
         * This method is called when password authorization is needed.
         *
         * @return The PasswordAuthentication
         */
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.acctName, this.acctKey.toCharArray());
        }
    }
}
