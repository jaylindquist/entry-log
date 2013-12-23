package com.bitwiseor.log.core.event.entry

public abstract class RequestUpdateEntryEvent {
	private final Integer id
	private final EntryDetails details
	
	RequestUpdateEntryEvent(final Integer id, final EntryDetails details) {
		this.id = id
		this.details = details
	}
}
