package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.RequestReadEvent

public class RequestEntryEvent extends RequestReadEvent {
	final Long id
	
	RequestEntryEvent(final Long id) {
		this.id = id
	}
}
