<?php
/* 
 * btTelco Basic REST API - PHP Implementation
 * 
 * Version 1.21
 *
 * btTelco
 * A bitTrust Service.
 * Identikrecord Group
 * Copyright 2013 (C) All rights reserved.
 * 
 */


class BtRestAPI {
	private $_api_url;
	private $_acct_name;
	private $_acct_key;

	private function _request_url($url) {
		$ch = curl_init();

		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);

		if (curl_exec($ch) === false) {
			error_log('btTelco PHP API: curl_exec(): cURL error: ' . curl_error($ch));
			return false;
		}

		$res = curl_multi_getcontent($ch);

		curl_close($ch);

		if (($res_json = json_decode($res, true)) == NULL) {
			error_log('btTelco PHP API: json_decode(): Unable to decode JSON response');
			return false;
		}

		return $res_json;
	}

	public function __construct($api_url, $acct_name, $acct_key) {
		$this->_api_url = $api_url . '/index.php';
		$this->_acct_name = $acct_name;
		$this->_acct_key = $acct_key;
	}

	public function request_sms($op, $to, $msg, $from = false, $timestamp = false, $mid = false) {
		$url = $this->_api_url . '/sms/';

		if ($from === false)
			$from = 'false';

		if ($timestamp === false)
			$timestamp = 'false';

		if ($mid === false) {
			$url .= $op . '/' .
				$this->_acct_name . '/' .
				$this->_acct_key . '/' .
				$to . '/' .
				rawurlencode($msg) . '/' .
				$from . '/' .
				$timestamp;
		} else {
			$url .= $op . '/' .
				$this->_acct_name . '/' .
				$this->_acct_key . '/' .
				$mid;
		}

		return $this->_request_url($url);
	}

	public function request_ussd($op, $to, $msg, $from = false, $timestamp = false, $mid = false) {
		$url = $this->_api_url . '/ussd/';

		if ($from === false)
			$from = 'false';

		if ($timestamp === false)
			$timestamp = 'false';

		if ($mid === false) {
			$url .= $op . '/' .
				$this->_acct_name . '/' .
				$this->_acct_key . '/' .
				$to . '/' .
				rawurlencode($msg) . '/' .
				$from . '/' .
				$timestamp;
		} else {
			$url .= $op . '/' .
				$this->_acct_name . '/' .
				$this->_acct_key . '/' .
				$mid;
		}

		return $this->_request_url($url);
	}

	public function request_tts($op, $to, $msg, $from = false, $language = false, $gender = false, $timestamp = false, $mid = false) {
		$url = $this->_api_url . '/tts/';

		if ($from === false)
			$from = 'false';

		if ($language === false)
			$language = 'false';

		if ($gender === false)
			$gender = 'false';

		if ($timestamp === false)
			$timestamp = 'false';

		if ($mid === false) {
			$url .= $op . '/' .
				$this->_acct_name . '/' .
				$this->_acct_key . '/' .
				$to . '/' .
				rawurlencode($msg) . '/' .
				$from . '/' .
				$language . '/' .
				$gender . '/' .
				$timestamp;
		} else {
			$url .= $op . '/' .
				$this->_acct_name . '/' .
				$this->_acct_key . '/' .
				$mid;
		}

		return $this->_request_url($url);
	}
}

class BtSmsAPI {
	private $_rest;

	public function __construct($api_url, $acct_name, $acct_key) {
		$this->_rest = new BtRestAPI($api_url, $acct_name, $acct_key);
	}

	public function send($to, $msg, $from = false, $timestamp = false) {
		return $this->_rest->request_sms('send', $to, $msg, $from, $timestamp);
	}

	public function report($mid) {
		return $this->_rest->request_sms('report', false, false, false, false, $mid);
	}
}

class BtUssdAPI {
	private $_rest;

	public function __construct($api_url, $acct_name, $acct_key) {
		$this->_rest = new BtRestAPI($api_url, $acct_name, $acct_key);
	}

	public function send($to, $msg, $from = false, $timestamp = false) {
		return $this->_rest->request_ussd('send', $to, $msg, $from, $timestamp);
	}

	public function report($mid) {
		return $this->_rest->request_ussd('report', false, false, false, false, $mid);
	}
}

class BtTtsAPI {
	private $_rest;

	public function __construct($api_url, $acct_name, $acct_key) {
		$this->_rest = new BtRestAPI($api_url, $acct_name, $acct_key);
	}

	public function send($to, $msg, $from = false, $language = false, $gender = false, $timestamp = false) {
		return $this->_rest->request_tts('send', $to, $msg, $from, $language, $gender, $timestamp);
	}

	public function report($mid) {
		return $this->_rest->request_tts('report', false, false, false, false, false, false, $mid);
	}
}

class BtAPIBasic {
	public $sms;
	public $ussd;
	public $tts;

	public function __construct($api_url, $acct_name, $acct_key) {
		$this->acct_name = $acct_name;
		$this->acct_key = $acct_key;
		$this->sms = new BtSmsAPI($api_url, $acct_name, $acct_key);
		$this->sms = new BtUssdAPI($api_url, $acct_name, $acct_key);
		$this->tts = new BtTtsAPI($api_url, $acct_name, $acct_key);
	}
}


