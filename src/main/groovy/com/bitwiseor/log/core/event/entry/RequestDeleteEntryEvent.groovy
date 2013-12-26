package com.bitwiseor.log.core.event.entry

import groovy.transform.ToString;

import com.bitwiseor.log.core.event.RequestDeleteEvent

@ToString
public class RequestDeleteEntryEvent extends RequestDeleteEvent {
	final Integer id
	
	RequestDeleteEntryEvent(final Integer id) {
		this.id = id
	}	
}
