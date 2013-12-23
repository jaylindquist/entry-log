package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.CreatedEvent

public class EntryCreatedEvent extends CreatedEvent {
	private final Integer id
	private final EntryDetails details
	
	private EntryCreatedEvent(Integer id) {
		this.id = id
	}
	
	EntryCreatedEvent(final Integer id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
	
	static EntryCreatedEvent notfound(final Integer id) {
		def event = new EntryCreatedEvent(id)
		event.entityFound = false
		return event
	}
}
