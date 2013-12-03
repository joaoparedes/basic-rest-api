<?php

/* Include Basic API php file */
include('bt_api_basic.php');

/* btTelco API URL */
$bttelco_api_url = 'https://bttelco.bittrust.net/api';

/* Update with your sub-account information */
$acct_name  = '0123456789abcdef0123456789abcdef'; // Your btTelco sub-account name
$acct_key  = '0123456789abcdef0123456789abcdef'; // Your btTelco sub-account key

/* Set the message properties */
$ussd_to   = '351961234567'; // Destination number. Do not include any prefix like 00 nor plus (+) sign.
$ussd_body = 'Hello. This is a test message from btTelco. Have a nice day.'; // Your message
// $ussd_from = 'YourBrand';
// $ussd_from = 'YourNumber';
$ussd_from = false;	/* When set to false, your account default number will be used as Sender ID */

/* Initialize the API object */
$bttelco = new BtAPIBasic($bttelco_api_url, $acct_name, $acct_key);

/* Send the USSD */
$send_data = $bttelco->ussd->send($ussd_to, $ussd_body, $ussd_from);
// $send_data = $bttelco->ussd->send($ussd_to, $ussd_body); // This is also valid. Default values will be used.

/* Check for errors */
if ($send_data === false) {
	/* Something went wrong */
	die('Unable to send USSD.');
}

/* Retrieve Message ID */
$mid = $send_data['mid'];

/* Check if the message was accepted for delivery */
if ($send_data['status'] != 'ACCEPTED') {
	/* Report that it was rejected */
	die('Message ID ' . $mid . ' was rejected.<br />');
}

/* Report that it is being delivered */
echo('Message ID ' . $mid . ' was accepted and is being delivered.<br />');

/* Wait some time before checking for delivery receipt */
sleep(30);

/* Check the message status */
$report_data = $bttelco->ussd->report($mid);

/* Check for errors */
if ($report_data === false) {
	/* Something went wrong */
	die('Unable to retrieve Message ID ' . $mid . ' report.');
}

/* Check if the message was delivered (3 is the DELIVERED status) */
if ($report_data['msg_status'] != 3) {
	/* Report that message wasn't delivered yet */
	die('Message ID ' . $mid . ' was _NOT_ delivered yet (after 30 seconds).<br />');
}

/* Report that message was delivered */
echo('Message ID ' . $mid . ' was delivered.<br />');

/* Everything is good */
exit();

