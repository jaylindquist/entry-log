package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.DeletedEvent

public class EntryDeletedEvent extends DeletedEvent {
	final Long id
	final EntryDetails details
	final boolean deletionCompleted
	
	private EntryDeletedEvent(final Long id) {
		this.id = id
	}
	
	EntryDeletedEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
		this.deletionCompleted = true
	}
	
	static EntryDeletedEvent deletionForbidden(final Long id, final EntryDetails details) {
		def event = new EntryDeletedEvent(id, details)
		event.entityFound = true
		event.deletionCompleted = false
	}
	
	static EntryDeletedEvent notFound(final Long id) {
		def event = new EntryDeletedEvent(id)
		event.entityFound = false
		event.deletionCompleted = false
	}
}
