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
public class APIJsonResponse {
    // New, unprocessed, communication
    private static final short MSG_STATUS_NEW = 1;
    // Message was successfully sent
    private static final short MSG_STATUS_SENT = 2;
    // Message was delivered
    private static final short MSG_STATUS_DELIVERED = 3;
    // Message wasn't sent
    private static final short MSG_STATUS_NOT_SENT = 4;
    // Message wasn't delivered
    private static final short MSG_STATUS_NOT_DELIVERED = 5;
    // Insufficient credits to perform the requested communication
    private static final short MSG_STATUS_INSUFF_CREDIT = 6;
    // Communication is in progress
    private static final short MSG_STATUS_IN_PROGRESS = 7;
    // Communication was rejected
    private static final short MSG_STATUS_REJECTED = 8;
    // Communication is completed
    private static final short MSG_STATUS_COMPLETE = 9;
    // Communication was denied
    private static final short MSG_STATUS_DENIED = 10;
    // Communication was transfered
    private static final short MSG_STATUS_TRANSFERED = 11;
    // Communication was hanged up
    private static final short MSG_STATUS_HANGUP = 12;
    // Communication was incomplete
    private static final short MSG_STATUS_INCOMPLETE = 13;
    // Destination was busy
    private static final short MSG_STATUS_BUSY = 14;
    // Communication contains invalid data
    private static final short MSG_STATUS_INVALID = 15;
    // Unexpected error occurred
    private static final short MSG_STATUS_ERROR = 16;
    // Communication session has expired
    private static final short MSG_STATUS_EXPIRED = 17;	 	
    
    private String status;
    private String reason;
    private static final String TYPE_VOICE = "VOICE";
    
    private String type;
    private Integer mid;
    private String to;
    private String from;
    private String message;
    private Float cost;
    private String country;
    private String sent_time;
    private String delivered_time;
    private String time;
    private Integer msg_status;
    private Float rate_discount;
    private Integer free_minutes;
    // TTS Languages
    // 1 	English (US) 	en-us
    private static final int TTS_LANGUAGE_EN_US = 1;
    // 2 	English (GB) 	en-gb
    private static final int TTS_LANGUAGE_EN_GB = 2;
// 3 	Catalan 	ca-es
    private static final int TTS_LANGUAGE_CA_ES = 3;
// 4 	Danish 	da-dk
    private static final int TTS_LANGUAGE_DA_DK = 4;
// 5 	Dutch 	nl-nl
    private static final int TTS_LANGUAGE_NL_NL = 5;
// 6 	Finnish 	fi-fi
    private static final int TTS_LANGUAGE_FI_FI = 6;
// 7 	French 	fr-fr
    private static final int TTS_LANGUAGE_FR_FR = 7;
// 8 	French (Canadian) 	fr-ca
    private static final int TTS_LANGUAGE_FR_CA = 8;
// 9 	Galacian 	gl-es
    private static final int TTS_LANGUAGE_GL_ES = 9;
// 10 	German 	de-de
    private static final int TTS_LANGUAGE_DE_DE = 10;
// 11 	Greek 	el-gr
    private static final int TTS_LANGUAGE_EL_GR = 11;
// 12 	Italian 	it-it
    private static final int TTS_LANGUAGE_IT_IT = 12;
    //TODO: 13??
    // missing from here http://www.bttelco.com/?page_id=433 
// 14 	Polish 	pl-pl
    private static final int TTS_LANGUAGE_PL_PL = 14;
// 15 	Russian 	ru-ru
    private static final int TTS_LANGUAGE_RU_RU = 15;
// 16 	Spanish (Castilian) 	es-es
    private static final int TTS_LANGUAGE_ES_ES = 16;
// 17 	Spanish (Argentine) 	es-ar
    private static final int TTS_LANGUAGE_ES_AR = 17;
// 18 	Spanish (Chilean) 	es-cl
    private static final int TTS_LANGUAGE_ES_CL = 18;
// 19 	Spanish (Mexican) 	es-mx
    private static final int TTS_LANGUAGE_ES_MX = 19;
// 20 	Portuguese 	pt-pt
    private static final int TTS_LANGUAGE_PT_PT = 20;
// 21 	Portuguese (Brazilian) 	pt-br
    private static final int TTS_LANGUAGE_PT_BR = 21;
// 22 	Swedish 	sv-se
    private static final int TTS_LANGUAGE_SV_SE = 22;
// 23 	Valencian 	x-va
    private static final int TTS_LANGUAGE_X_VA = 23;
    
    private Integer language;
    
    private static final int TTS_GENDER_MALE = 1;
    private static final int TTS_GENDER_FEMALE = 2;
    
    private Integer gender;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the mid
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the cost
     */
    public Float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(Float cost) {
        this.cost = cost;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the sent_time
     */
    public String getSent_time() {
        return sent_time;
    }

    /**
     * @param sent_time the sent_time to set
     */
    public void setSent_time(String sent_time) {
        this.sent_time = sent_time;
    }

    /**
     * @return the delivered_time
     */
    public String getDelivered_time() {
        return delivered_time;
    }

    /**
     * @param delivered_time the delivered_time to set
     */
    public void setDelivered_time(String delivered_time) {
        this.delivered_time = delivered_time;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the msg_status
     */
    public Integer getMsg_status() {
        return msg_status;
    }

    /**
     * @param msg_status the msg_status to set
     */
    public void setMsg_status(Integer msg_status) {
        this.msg_status = msg_status;
    }

    /**
     * @return the rate_discount
     */
    public Float getRate_discount() {
        return rate_discount;
    }

    /**
     * @param rate_discount the rate_discount to set
     */
    public void setRate_discount(Float rate_discount) {
        this.rate_discount = rate_discount;
    }

    /**
     * @return the free_minutes
     */
    public Integer getFree_minutes() {
        return free_minutes;
    }

    /**
     * @param free_minutes the free_minutes to set
     */
    public void setFree_minutes(Integer free_minutes) {
        this.free_minutes = free_minutes;
    }

    /**
     * @return the language
     */
    public Integer getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(Integer language) {
        this.language = language;
    }

    /**
     * @return the gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }
}