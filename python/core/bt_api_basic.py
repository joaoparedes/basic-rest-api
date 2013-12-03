#!/usr/bin/python -P

# 
# btTelco Basic REST API - Python Implementation
# 
# Version 1.21
#
# btTelco - http://www.bttelco.com
#
# A bitTrust Service.
# http://www.bittrust.net
# Identikrecord Group
# Copyright 2013 (C) All rights reserved.
# 


import urllib
import json
import syslog

class BtRestAPI:
	def __init__(self, api_url, acct_name, acct_key):
		self.api_url = api_url + '/index.php'
		self.acct_name = acct_name
		self.acct_key = acct_key

	def _request_url(self, url):
		print url
		try:
			r = urllib.urlopen(url)
		except:
			syslog.syslog('BtRestAPI._request_url(): urllib2.urlopen(\"%s\"): Failed' % url)
			return False

		try:
			res_json = json.load(r)
		except:
			syslog.syslog('BtRestAPI._request_url(): json.load(): Failed')
			return False

		return res_json

	def request_sms(self, op, to, msg, sender = False, timestamp = False, mid = False):
		url = self.api_url + '/sms'

		if sender == False:
			sender = 'false'

		if timestamp == False:
			timestamp = 'false'

		if mid == False:
			url = "%s/%s/%s/%s/%s/%s/%s/%s" % (url, op, self.acct_name, self.acct_key, to, urllib.quote(msg), sender, timestamp)
		else:
			url = "%s/%s/%s/%s/%s" % (url, op, self.acct_name, self.acct_key, mid)

		return self._request_url(url)

	def request_ussd(self, op, to, msg, sender = False, timestamp = False, mid = False):
		url = self.api_url + '/ussd'

		if sender == False:
			sender = 'false'

		if timestamp == False:
			timestamp = 'false'

		if mid == False:
			url = "%s/%s/%s/%s/%s/%s/%s/%s" % (url, op, self.acct_name, self.acct_key, to, urllib.quote(msg), sender, timestamp)
		else:
			url = "%s/%s/%s/%s/%s" % (url, op, self.acct_name, self.acct_key, mid)

		return self._request_url(url)

	def request_tts(self, op, to, msg, from_num = False, language = False, gender = False, timestamp = False, mid = False):
		url = self.api_url + '/tts'

		if from_num == False:
			sender = 'false'

		if language == False:
			language = 'false'

		if gender == False:
			gender = 'false'

		if timestamp == False:
			timestamp = 'false'

		if mid == False:
			url = "%s/%s/%s/%s/%s/%s/%s/%s/%s/%s" % (url, op, self.acct_name, self.acct_key, to, urllib.quote(msg), sender, language, gender, timestamp)
		else:
			url = "%s/%s/%s/%s/%s" % (url, op, self.acct_name, self.acct_key, mid)

		return self._request_url(url)

class BtSmsAPI:
	def __init__(self, api_url, acct_name, acct_key):
		self._rest = BtRestAPI(api_url, acct_name, acct_key)

	def send(self, to, msg, sender = False, timestamp = False):
		return self._rest.request_sms('send', to, msg, sender, timestamp)

	def report(self, mid):
		return self._rest.request_sms('report', False, False, False, False, mid)

class BtUssdAPI:
	def __init__(self, api_url, acct_name, acct_key):
		self._rest = BtRestAPI(api_url, acct_name, acct_key)

	def send(self, to, msg, sender = False, timestamp = False):
		return self._rest.request_ussd('send', to, msg, sender, timestamp)

	def report(self, mid):
		return self._rest.request_ussd('report', False, False, False, False, mid)

class BtTtsAPI:
	def __init__(self, api_url, acct_name, acct_key):
		self._rest = BtRestAPI(api_url, acct_name, acct_key)

	def send(self, to, msg, from_num = False, language = False, gender = False, timestamp = False):
		return self._rest.request_tts('send', to, msg, from_num, language, gender, timestamp)

	def report(self, mid):
		return self._rest.request_tts('report', False, False, False, False, False, False, mid)

class BtAPIBasic:
	def __init__(self, api_url, acct_name, acct_key):
		self.acct_name = acct_name
		self.acct_key = acct_key
		self.sms = BtSmsAPI(api_url, acct_name, acct_key)
		self.ussd = BtUssdAPI(api_url, acct_name, acct_key)
		self.tts = BtTtsAPI(api_url, acct_name, acct_key)

