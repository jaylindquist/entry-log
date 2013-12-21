package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.CreatedEvent

public class EntryCreatedEvent extends CreatedEvent {
	private final Long id
	private final EntryDetails details
	
	EntryCreatedEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
}
