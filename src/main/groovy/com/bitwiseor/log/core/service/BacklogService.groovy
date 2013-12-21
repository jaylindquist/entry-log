package com.bitwiseor.log.core.service

import com.bitwiseor.log.core.event.entry.*

interface BacklogService {
	AllEntriesEvent requestAll(RequestAllEntriesEvent event)
	EntryReadEvent requestEntry(RequestEntryEvent event)
	EntryDeletedEvent requestDeleteEntry(RequestDeleteEntryEvent event)
	EntryUpdatedEvent requestUpdateEntry(RequestUpdateEntryEvent event)
	EntryCreatedEvent requestCreateEntry(RequestCreateEntryEvent event)
	
}
