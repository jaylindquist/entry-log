package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.UpdatedEvent

public class EntryUpdatedEvent extends UpdatedEvent {
	private final Long id
	private final EntryDetails details
	
	protected EntryUpdatedEvent(Long id) {
		this.id = id
	}
		
	EntryUpdatedEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
	
	static EntryUpdatedEvent notFound(final Long id) {
		def event = new EntryUpdatedEvent(id)
		event.entityFound = false
	}
}
