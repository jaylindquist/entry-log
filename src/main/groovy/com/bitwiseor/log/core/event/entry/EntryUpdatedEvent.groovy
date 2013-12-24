package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.UpdatedEvent

public class EntryUpdatedEvent extends UpdatedEvent {
	private final Integer id
	private final EntryDetails details
	
	protected EntryUpdatedEvent(Integer id) {
		this.id = id
	}
		
	EntryUpdatedEvent(final EntryDetails details) {
		this.id = details.entryId
		this.details = details
	}
	
	static EntryUpdatedEvent notFound(final Integer id) {
		def event = new EntryUpdatedEvent(id)
		event.entityExists = false
		return event
	}
}
