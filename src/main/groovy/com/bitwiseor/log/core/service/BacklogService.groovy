package com.bitwiseor.log.core.service

import com.bitwiseor.log.core.event.entry.*

interface BacklogService {
	AllEntriesEvent request(RequestAllEntriesEvent event)
	EntryReadEvent request(RequestEntryEvent event)
	EntryDeletedEvent request(RequestDeleteEntryEvent event)
	EntryUpdatedEvent request(RequestUpdateEntryEvent event)
	EntryCreatedEvent request(RequestCreateEntryEvent event)
	
}
