package com.bitwiseor.log.core.domain

import com.bitwiseor.log.core.event.entry.EntryDetails

class BacklogEntry {
	Integer id
	Game game
	
	static BacklogEntry fromEntryDetails(EntryDetails details) {
		def entry = new BacklogEntry()
		entry.id = details.entryId
		entry.game = new Game(details.gameName)
		return entry
	}
	
	EntryDetails toEntryDetails() {
		def details = new EntryDetails()
		details.entryId = id
		details.gameName = game.title
		return details
	}
	
}
