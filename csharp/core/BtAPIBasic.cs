/* 
 * btTelco Basic REST API - C# Implementation
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

using System;
using System.Collections.Generic;
using System.Net;
using System.Web;
using System.Web.Script.Serialization;

namespace BtAPIBasicNS
{
	public class APIJsonResponse {
		public String status { get; set; }
		public String reason { get; set; }
		public String type { get; set; }
		public Int32 mid { get; set; }
		public String to { get; set; }
		public String from { get; set; }
		public String message { get; set; }
		public float cost { get; set; }
		public String country { get; set; }
		public String sent_time { get; set; }
		public String delivered_time { get; set; }
		public String time { get; set; }
		public Int32 msg_status { get; set; }
	}

	public class BtRestAPI
	{
		private String _ApiUrl;
		private String _AcctName;
		private String _AcctKey;

		public BtRestAPI(String ApiUrl, String AcctName, String AcctKey)
		{
			this._ApiUrl = ApiUrl + "/index.php";
			this._AcctName = AcctName;
			this._AcctKey = AcctKey;
		}

		private Dictionary<String, String> _ConvertAPIResponseToDictionary(APIJsonResponse Obj)
		{
			Dictionary<String, String> DictObj = new Dictionary<String, String>();
			
			DictObj.Add("status", Obj.status);
			DictObj.Add("reason", Obj.reason);
			DictObj.Add("type", Obj.type);
			DictObj.Add("mid", Obj.mid.ToString());
			DictObj.Add("to", Obj.to);
			DictObj.Add("from", Obj.from);
			DictObj.Add("message", Obj.message);
			DictObj.Add("cost", Obj.cost.ToString());
			DictObj.Add("country", Obj.country);
			DictObj.Add("sent_time", Obj.sent_time);
			DictObj.Add("delivered_time", Obj.delivered_time);
			DictObj.Add("time", Obj.time);
			DictObj.Add("msg_status", Obj.msg_status.ToString());
			
			return DictObj;
		}

		private Dictionary<String, String> _RequestUrl(String Url)
		{
			JavaScriptSerializer js = new JavaScriptSerializer();
			String JsonResponse;
			
			using (WebClient client = new WebClient()) {
				try {
					JsonResponse = client.DownloadString(Url);
				} catch {
					return null;
				}
			}

			try {
				return this._ConvertAPIResponseToDictionary(js.Deserialize<APIJsonResponse>(JsonResponse));
			} catch {
				return null;
			}
		}

		public Dictionary<String, String> RequestSms(
					String Operation, String To, String Message,
					String SenderID = null, String TimeStamp = null, Int32 MessageID = 0)
		{
			String Url = this._ApiUrl + "/sms";
			
			if (SenderID == null)
				SenderID = "false";

			if (TimeStamp == null)
				TimeStamp = "false";

			if (MessageID == 0) {
				Url += "/" + Operation + "/" + this._AcctName + "/" + this._AcctKey + "/" +
						To + "/" + Uri.EscapeDataString(Message) + "/" + SenderID + "/" +
						TimeStamp;
			} else {
				Url += "/" + Operation + "/" + this._AcctName + "/" + this._AcctKey + "/" +
						MessageID.ToString();
			}
			
			return this._RequestUrl(Url);
		}
		
		public Dictionary<String, String> RequestUssd(
					String Operation, String To, String Message,
					String SenderID = null, String TimeStamp = null, Int32 MessageID = 0)
		{
			String Url = this._ApiUrl + "/ussd";
			
			if (SenderID == null)
				SenderID = "false";

			if (TimeStamp == null)
				TimeStamp = "false";

			if (MessageID == 0) {
				Url += "/" + Operation + "/" + this._AcctName + "/" + this._AcctKey + "/" +
						To + "/" + Uri.EscapeDataString(Message) + "/" + SenderID + "/" +
						TimeStamp;
			} else {
				Url += "/" + Operation + "/" + this._AcctName + "/" + this._AcctKey + "/" +
						MessageID.ToString();
			}
			
			return this._RequestUrl(Url);
		}
	
		public Dictionary<String, String> RequestTts(
					String Operation, String To, String Message,
					String From = null, Int32 Language = 0, Int32 Gender = 0,
					String TimeStamp = null, Int32 MessageID = 0)
		{
			String Url = this._ApiUrl + "/tts";
			String LangStr;
			String GenderStr;

			if (From == null)
				From = "false";
			
			if (Language == 0) {
				LangStr = "false";
			} else {
				LangStr = Language.ToString();
			}

			if (Gender == 0) {
				GenderStr = "false";
			} else {
				GenderStr = Gender.ToString();
			}

			if (TimeStamp == null)
				TimeStamp = "false";

			if (MessageID == 0) {
				Url += "/" + Operation + "/" + this._AcctName + "/" + this._AcctKey + "/" +
						To + "/" + Uri.EscapeDataString(Message) + "/" + From + "/" +
						LangStr + "/" + GenderStr + "/" + TimeStamp;
			} else {
				Url += "/" + Operation + "/" + this._AcctName + "/" + this._AcctKey + "/" +
						MessageID.ToString();
			}

			return this._RequestUrl(Url);
		}
	}
	
	public class BtSmsAPI
	{
		private BtRestAPI _rest;

		public BtSmsAPI(String ApiUrl, String AcctName, String AcctKey)
		{
			this._rest = new BtRestAPI(ApiUrl, AcctName, AcctKey);
		}
		
		public Dictionary<String, String> Send(String To, String Message, String SenderID = null, String TimeStamp = null)
		{
			return this._rest.RequestSms("send", To, Message, SenderID, TimeStamp);
		}
		
		public Dictionary<String, String> Report(Int32 MessageID)
		{
			return this._rest.RequestSms("report", null, null, null, null, MessageID);
		}
	}
	
	public class BtUssdAPI
	{
		private BtRestAPI _rest;

		public BtUssdAPI(String ApiUrl, String AcctName, String AcctKey)
		{
			this._rest = new BtRestAPI(ApiUrl, AcctName, AcctKey);
		}
		
		public Dictionary<String, String> Send(String To, String Message, String SenderID = null, String TimeStamp = null)
		{
			return this._rest.RequestUssd("send", To, Message, SenderID, TimeStamp);
		}
		
		public Dictionary<String, String> Report(Int32 MessageID)
		{
			return this._rest.RequestUssd("report", null, null, null, null, MessageID);
		}
	}

	public class BtTtsAPI
	{
		private BtRestAPI _rest;

		public BtTtsAPI(String ApiUrl, String AcctName, String AcctKey)
		{
			this._rest = new BtRestAPI(ApiUrl, AcctName, AcctKey);
		}
		
		public Dictionary<String, String> Send(String To, String Message, String From = null, Int32 Language = 0, Int32 Gender = 0, String TimeStamp = null)
		{
			return this._rest.RequestTts("send", To, Message, From, Language, Gender, TimeStamp);
		}
		
		public Dictionary<String, String> Report(Int32 MessageID)
		{
			return this._rest.RequestTts("report", null, null, null, 0, 0, null, MessageID);
		}
	}

	public class BtAPIBasic
	{
		public BtSmsAPI Sms;
		public BtUssdAPI Ussd;
		public BtTtsAPI Tts;

		public BtAPIBasic(String ApiUrl, String AcctName, String AcctKey)
		{
			this.Sms = new BtSmsAPI(ApiUrl, AcctName, AcctKey);
			this.Ussd = new BtUssdAPI(ApiUrl, AcctName, AcctKey);
			this.Tts = new BtTtsAPI(ApiUrl, AcctName, AcctKey);
		}
	}
}

