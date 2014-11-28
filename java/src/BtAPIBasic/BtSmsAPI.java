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

import java.util.HashMap;

/**
 *
 * @author Joao Paredes
 */
public class BtSmsAPI {

    private BtRestAPI rest;

    public BtSmsAPI(String apiUrl, String acctName, String acctKey) {
        this.rest = new BtRestAPI(apiUrl, acctName, acctKey);
    }

    public HashMap<String, String> send(String to, String message, String senderID, String timeStamp) {
        return this.rest.requestSms("send", to, message, senderID, timeStamp, null);
    }

    public HashMap<String, String> report(Integer messageID) {
        return this.rest.requestSms("report", null, null, null, null, messageID);
    }
}
