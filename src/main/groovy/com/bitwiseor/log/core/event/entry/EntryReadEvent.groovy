package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.ReadEvent

public class EntryReadEvent extends ReadEvent {
	private final Long id
	private final EntryDetails details
	
	private EntryReadEvent(Long id) {
		this.id = id
	}
	
	EntryReadEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
	
	static EntryReadEvent notFound(final Long id) {
		def event = new EntryReadEvent(id)
		event.entityFound = false
	}
}
