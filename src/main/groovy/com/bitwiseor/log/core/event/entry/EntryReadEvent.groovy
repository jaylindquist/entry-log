package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.ReadEvent

public class EntryReadEvent extends ReadEvent {
	private final Integer id
	private final EntryDetails details
	
	private EntryReadEvent(Integer id) {
		this.id = id
	}
	
	EntryReadEvent(final Integer id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
	
	static EntryReadEvent notFound(final Integer id) {
		def event = new EntryReadEvent(id)
		event.entityFound = false
		return event
	}
}
