package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.UpdatedEvent

public class EntryUpdatedEvent extends UpdatedEvent {
	private final Integer id
	private final EntryDetails details
	
	protected EntryUpdatedEvent(Integer id) {
		this.id = id
	}
		
	EntryUpdatedEvent(final Integer id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
	
	static EntryUpdatedEvent notFound(final Integer id) {
		def event = new EntryUpdatedEvent(id)
		event.entityFound = false
		return event
	}
}
