package com.bitwiseor.log.core.event.entry

import groovy.transform.ToString;

import com.bitwiseor.log.core.event.DeletedEvent

@ToString
public class EntryDeletedEvent extends DeletedEvent {
	final Integer id
	final EntryDetails details
	protected boolean deletionCompleted
	
	private EntryDeletedEvent(final Integer id) {
		this.id = id
	}
	
	EntryDeletedEvent(final EntryDetails details) {
		this.id = details.entryId
		this.details = details
		this.deletionCompleted = true
	}
	
	static EntryDeletedEvent deletionForbidden(final EntryDetails details) {
		def event = new EntryDeletedEvent(details)
		event.entityFound = true
		event.deletionCompleted = false
		return event
	}
	
	static EntryDeletedEvent notFound(final Integer id) {
		def event = new EntryDeletedEvent(id)
		event.entityFound = false
		event.deletionCompleted = false
		return event
	}
	
	public boolean isDeletionCompleted() {
		return deletionCompleted
	}
}
