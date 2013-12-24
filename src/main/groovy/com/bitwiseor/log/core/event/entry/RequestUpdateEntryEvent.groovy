package com.bitwiseor.log.core.event.entry

public class RequestUpdateEntryEvent {
	private final Integer id
	private final EntryDetails details
	
	RequestUpdateEntryEvent(final EntryDetails details) {
		this.id = details.entryId
		this.details = details
	}
}
