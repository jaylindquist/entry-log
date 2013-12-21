package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.ReadEvent

public class EntryReadEvent extends ReadEvent {
	private final Long id
	private final EntryDetails details
	
	EntryReadEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
}
