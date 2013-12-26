package com.bitwiseor.log.rest.fixture

import com.bitwiseor.log.core.event.entry.AllEntriesEvent
import com.bitwiseor.log.core.event.entry.EntryDetails

class RestDataFixture {
	static final String GAME_NAME = 'GAME NAME'
	static Integer id = 0
	
	static AllEntriesEvent allEntries() {
		return new AllEntriesEvent([
			newEntryDetails(),
			newEntryDetails(),
			newEntryDetails()])
	}
	
	static EntryDetails newEntryDetails() {
		return new EntryDetails(entryId: ++id, gameName: GAME_NAME)
	}
	
	static String entryJson(Integer id, String game) {
		def json =  /{"entryId":${id},"gameName":"${game}"}/
		return json
	}
}
