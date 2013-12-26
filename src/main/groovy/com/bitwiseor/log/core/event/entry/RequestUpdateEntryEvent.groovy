package com.bitwiseor.log.core.event.entry

import groovy.transform.ToString

import com.bitwiseor.log.core.event.RequestUpdateEvent

@ToString
public class RequestUpdateEntryEvent extends RequestUpdateEvent {
	private final Integer id
	private final EntryDetails details
	
	RequestUpdateEntryEvent(final EntryDetails details) {
		this.id = details.entryId
		this.details = details
	}
}
