package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.RequestDeleteEvent

public class RequestDeleteEntryEvent extends RequestDeleteEvent {
	final Integer id
	final EntryDetails details
	
	RequestDeleteEntryEvent(final Integer id, final EntryDetails details) {
		this.id = id
		this.details = details
	}	
}
