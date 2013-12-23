package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.RequestDeleteEvent

public class RequestDeleteEntryEvent extends RequestDeleteEvent {
	final Long id
	final EntryDetails details
	
	RequestDeleteEntryEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
	}	
}
