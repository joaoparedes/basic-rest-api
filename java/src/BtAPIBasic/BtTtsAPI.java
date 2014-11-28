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
public class BtTtsAPI {
//TODO:
    private BtRestAPI rest;

    public BtTtsAPI(String ApiUrl, String AcctName, String AcctKey) {
        this.rest = new BtRestAPI(ApiUrl, AcctName, AcctKey);
    }

    public HashMap<String, String> send(String to, String message, String from, Integer language, Integer gender, String timeStamp) {
                        return this.rest.requestTts("send", to, message, from, language, gender, timeStamp, null);
    }

    public HashMap<String, String> report(Integer messageID) {
        return this.rest.requestTts("report", null, null, null, 0, 0, null, messageID);
    }
}
