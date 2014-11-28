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

/**
 *
 * @author Joao Paredes
 */
public class BtAPIBasic {

    public BtSmsAPI sms;
    // TODO: implement the remaining API
    public BtUssdAPI ussd;
    public BtTtsAPI tts;

    public BtAPIBasic(String apiUrl, String acctName, String acctKey) {
        this.sms = new BtSmsAPI(apiUrl, acctName, acctKey);
        this.ussd = new BtUssdAPI(apiUrl, acctName, acctKey);
        this.tts = new BtTtsAPI(apiUrl, acctName, acctKey);
    }    
}
