package com.bitwiseor.log.core.event.entry

public abstract class RequestUpdateEntryEvent {
	private final Long id
	private final EntryDetails details
	
	RequestUpdateEntryEvent(final Long id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
}
