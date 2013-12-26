package com.bitwiseor.log.core.event.entry

import groovy.transform.ToString;

import com.bitwiseor.log.core.event.RequestCreateEvent

@ToString
public class RequestCreateEntryEvent extends RequestCreateEvent {
	final EntryDetails details
	
	RequestCreateEntryEvent(final EntryDetails details) {
		this.details = details
	}	
}
