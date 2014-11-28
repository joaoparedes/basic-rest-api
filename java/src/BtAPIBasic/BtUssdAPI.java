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
public class BtUssdAPI {
//TODO:
    private BtRestAPI rest;

    public BtUssdAPI(String ApiUrl, String AcctName, String AcctKey) {
        this.rest = new BtRestAPI(ApiUrl, AcctName, AcctKey);
    }

    public HashMap<String, String> send(String to, String message, String senderID, String timeStamp)
                {
                        return this.rest.requestUssd("send", to, message, senderID, timeStamp, null);
    }

    public HashMap<String, String> report(Integer messageID) {
        return this.rest.requestUssd("report", null, null, null, null, messageID);
    }
}
