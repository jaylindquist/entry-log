package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.RequestDeleteEvent

public class RequestDeleteEntryEvent extends RequestDeleteEvent {
	final Long id
	
	RequestDeleteEntryEvent(final Long id) {
		this.id = id
	}	
}
