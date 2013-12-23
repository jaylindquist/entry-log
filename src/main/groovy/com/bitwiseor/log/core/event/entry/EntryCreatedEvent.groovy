package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.CreatedEvent

public class EntryCreatedEvent extends CreatedEvent {
	private final Long id
	private final EntryDetails details
	
	private EntryCreatedEvent(Long id) {
		this.id = id
	}
	
	EntryCreatedEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
	
	static EntryCreatedEvent notfound(final Long id) {
		def event = new EntryCreatedEvent(id)
		event.entityFound = false
	}
}
