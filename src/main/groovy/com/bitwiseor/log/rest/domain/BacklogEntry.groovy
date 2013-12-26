package com.bitwiseor.log.rest.domain

import groovy.transform.ToString

import com.bitwiseor.log.core.event.entry.EntryDetails
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

@ToString
class BacklogEntry {
	final String gameName
	final Integer entryId
	
	@JsonCreator
	BacklogEntry(@JsonProperty('entryId') Integer entryId, @JsonProperty('gameName') String gameName) {
		this.entryId = entryId
		this.gameName = gameName
	}
	
	static BacklogEntry fromEntryDetails(EntryDetails details) {
		return new BacklogEntry(details.entryId, details.gameName)
	}
	
	EntryDetails toEntryDetails() {
		return new EntryDetails(entryId: entryId, gameName: gameName)
	}
}
