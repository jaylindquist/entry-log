package com.bitwiseor.log.core.event.entry

import groovy.transform.ToString;

import com.bitwiseor.log.core.event.CreatedEvent

@ToString
public class EntryCreatedEvent extends CreatedEvent {
	private final Integer id
	private final EntryDetails details
	
	private EntryCreatedEvent(Integer id) {
		this.id = id
	}
	
	EntryCreatedEvent(final EntryDetails details) {
		this.id = details.entryId
		this.details = details
	}
	
	static EntryCreatedEvent exists(final Integer id) {
		def event = new EntryCreatedEvent(id)
		event.entityExists = true
		return event
	}
}
