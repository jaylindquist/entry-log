package com.bitwiseor.log.rest.fixture

import com.bitwiseor.log.core.event.entry.EntryCreatedEvent
import com.bitwiseor.log.core.event.entry.EntryDeletedEvent
import com.bitwiseor.log.core.event.entry.EntryDetails
import com.bitwiseor.log.core.event.entry.EntryReadEvent
import com.bitwiseor.log.core.event.entry.EntryUpdatedEvent

class RestEventFixture {
	static EntryReadEvent entryNotFound(Integer id) {
		return EntryReadEvent.notFound(id)
	}
	static EntryReadEvent entryFound(Integer id) {
		return new EntryReadEvent(new EntryDetails(entryId: id, gameName: RestDataFixture.GAME_NAME))
	}
	static EntryDeletedEvent entryDeleted(Integer id) {
		return new EntryDeletedEvent(new EntryDetails(entryId: id, gameName: RestDataFixture.GAME_NAME))
	}
	static EntryDeletedEvent entryDeletedNotFound(Integer id) {
		return EntryDeletedEvent.notFound(id)
	}
	static EntryDeletedEvent entryDeletedForbidden(Integer id) {
		return EntryDeletedEvent.deletionForbidden(new EntryDetails(entryId: id, gameName: RestDataFixture.GAME_NAME))
	}
	static EntryUpdatedEvent entryUpdated(Integer id, String game) {
		return new EntryUpdatedEvent(new EntryDetails(entryId: id, gameName: game))
	}
	static EntryUpdatedEvent entryUpdatedNotFound(Integer id) {
		return EntryUpdatedEvent.notFound(id)
	}
	static EntryCreatedEvent entryCreated(Integer id, String gameName) {
		return new EntryCreatedEvent(new EntryDetails(entryId: id, gameName: gameName))
	}
	
}
