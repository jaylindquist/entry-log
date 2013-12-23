package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.RequestReadEvent

public class RequestEntryEvent extends RequestReadEvent {
	final Integer id
	
	RequestEntryEvent(final Integer id) {
		this.id = id
	}
}
