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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao Paredes
 */
public class APIJsonResponseParser {

    /**
     *
     * @param jsonString
     * @return
     * @throws java.io.IOException
     */
    public static APIJsonResponse parser(String jsonString) {
        APIJsonResponse resp = null;
        JsonFactory f = new JsonFactory();
        try {
            JsonParser jp = f.createParser(jsonString);            
            jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
            // Only make a new instance if JsonToken.START_OBJECT is found!
            resp = new APIJsonResponse();
            while (jp.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jp.getCurrentName();
                jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
                switch (fieldname) {
                    case "status":
                        // contains an object
                        resp.setStatus(jp.getText());
                        break;
                    case "reason":
                        resp.setReason(jp.getText());
                        break;
                    case "type":
                        resp.setType(jp.getText());
                        break;
                    case "mid":
                        resp.setMid(Integer.valueOf(jp.getText()));
                        break;
                    case "to":
                        resp.setTo(jp.getText());
                        break;
                    case "from":
                        resp.setFrom(jp.getText());
                        break;
                    case "message":
                        resp.setMessage(jp.getText());
                        break;
                    case "cost":
                        resp.setCost(Float.valueOf(jp.getText()));
                        break;
                    case "country":
                        resp.setCountry(jp.getText());
                        break;
                    case "sent_time":
                        resp.setSent_time(jp.getText());
                        break;
                    case "delivered_time":
                        resp.setDelivered_time(jp.getText());
                        break;
                    case "time":
                        resp.setTime(jp.getText());
                        break;
                    case "msg_status":
                        resp.setMsg_status(Integer.valueOf(jp.getText()));
                        break;
                    case "rate_discount":
                        resp.setRate_discount(Float.valueOf(jp.getText()));
                        break;
                    case "free_minutes":
                        resp.setFree_minutes(Integer.valueOf(jp.getText()));
                        break;
                    case "language":
                        resp.setFree_minutes(Integer.valueOf(jp.getText()));
                        break;
                    case "gender":
                        resp.setFree_minutes(Integer.valueOf(jp.getText()));
                        break;
                    default:
                    throw new IllegalStateException("Unrecognized field '" + fieldname + "'!");                        
                }
            }
        } catch (JsonParseException ex) {
            Logger.getLogger(APIJsonResponseParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(APIJsonResponseParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(APIJsonResponseParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }
}
