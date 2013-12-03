#!/usr/bin/python

# 
# btTelco Basic REST API - Python Implementation - USSD Example
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

# Include Basic API python module
sys.path.append('.')
import bt_api_basic

# btTelco API URL
BTTELCO_API_URL = 'https://bttelco.bittrust.net/api'

# Update with your sub-account information
ACCT_NAME = '0123456789abcdef0123456789abcdef' # Your btTelco sub-account name
ACCT_KEY = '0123456789abcdef0123456789abcdef'  # Your btTelco sub-account key

# Set the message properties
USSD_TO = '351961234567' # Destination number. Do not include any prefix such as 00 nor plus (+) sign
USSD_BODY = 'Hello. This is a test message from btTelco. Have a nice day.' # Your message
# USSD_FROM = 'YourBrand'
# USSD_FROM = 'YourNumber'
USSD_FROM = False	# When set to false, your account default number will be used as Sender ID

# MAIN
if __name__ == '__main__':
	# Initialize the API object
	bttelco = bt_api_basic.BtAPIBasic(BTTELCO_API_URL, ACCT_NAME, ACCT_KEY)

	# Send the USSD
	send_data = bttelco.ussd.send(USSD_TO, USSD_BODY, USSD_FROM)
	# send_data = bttelco.ussd.send(USSD_TO, USSD_BODY) # This is also valid. Default values will be used.

	# Check for errors
	if send_data == False:
		# Something went wrong
		print('Unable to send USSD.')
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
	report_data = bttelco.ussd.report(mid)

	# Check for errors
	if report_data == False:
		# Something went wrong
		print('Unable to retrieve Message ID %s report.' % mid)
		sys.exit(1)

	# Check if the message was delivered (3 is the DELIVERED status)
	if report_data['msg_status'] != 3:
		# Report that message wasn't delivered yet
		print('Message ID %s was _NOT_ delivered yet (after 30 seconds).' % mid)

	# Report that message was delivered
	print('Message ID %s was delivered' % mid)

	# Everything is good
	sys.exit(0)

