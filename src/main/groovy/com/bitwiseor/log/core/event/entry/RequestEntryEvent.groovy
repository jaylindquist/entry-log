package com.bitwiseor.log.core.event.entry

import groovy.transform.ToString;

import com.bitwiseor.log.core.event.RequestReadEvent

@ToString
public class RequestEntryEvent extends RequestReadEvent {
	final Integer id
	
	RequestEntryEvent(final Integer id) {
		this.id = id
	}
}
