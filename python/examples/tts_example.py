#!/usr/bin/python

# 
# btTelco Basic REST API - Python Implementation - Text-to-Speech Example
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


import sys
import time

# Import Basic API python module
sys.path.append('.')
import bt_api_basic

# btTelco API URL
BTTELCO_API_URL = 'https://bttelco.bittrust.net/api'

# Update with your sub-account information
ACCT_NAME = '0123456789abcdef0123456789abcdef' # Your btTelco sub-account name
ACCT_KEY = '0123456789abcdef0123456789abcdef'  # Your btTelco sub-account key

# Set the message properties
TTS_TO = '351961234567' # Destination number. Do not include any prefix like 00 nor plus (+) sign
TTS_BODY = 'Hello. This is a test message from B T telco. Have a nice day.' # Your message
#TTS_FROM = 'YourNumber'
TTS_FROM = False     # When set to false, your account default number will be used as Caller ID
TTS_LANGUAGE = False # Set to 1 for en-US, 2 for en-UK, 20 for pt-PT, etc. When false, en-US will be used
TTS_GENDER = False   # Set to 1 for Male voice, 2 for Female voice. When false, Male will be the default

# MAIN
if __name__ == '__main__':
	# Initialize the API object
	bttelco = bt_api_basic.BtAPIBasic(BTTELCO_API_URL, ACCT_NAME, ACCT_KEY)

	# Send the TTS
	send_data = bttelco.tts.send(TTS_TO, TTS_BODY, TTS_FROM, TTS_LANGUAGE, TTS_GENDER)
	# send_data = bttelco.tts.send(TTS_TO, TTS_BODY) # This is also valid. Default values will be used.

	# Check for errors
	if send_data == False:
		# Something went wrong
		print('Unable to send TTS.')
		sys.exit(1)

	# Retrieve Message ID
	mid = send_data['mid']

	# Check if the message was accepted for delivery
	if send_data['status'] != 'ACCEPTED':
		# Report that it was rejected
		print('Message ID %s was rejected' % mid)
		sys.exit(1)

	# Report that it is being delivered
	print('Message ID %s was accepted and is being delivered.' % mid)

	# Wait some time before checking for delivery receipt
	time.sleep(30)

	# Check the message status
	report_data = bttelco.tts.report(mid)

	# Check for errors
	if report_data == False:
		# Something went wrong
		print('Unable to retrieve Message ID %s report.' % mid)
		sys.exit(1)

	# Check for errors
	if report_data['msg_status'] != 3:
		# Report that message wasn't delivered yet
		print('Message ID %s was _NOT_ delivered yet (after 30 seconds).' % mid)

	# Report that message was delivered
	print('Message ID %s was delivered' % mid)

	# Everything is good
	sys.exit(0)

