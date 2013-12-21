package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.RequestCreateEvent

public class RequestCreateEntryEvent extends RequestCreateEvent {
	final EntryDetails details
	
	RequestCreateEntryEvent(final EntryDetails details) {
		this.details = details
	}	
}
