package com.bitwiseor.log.core.event.entry

import groovy.transform.ToString
import groovy.util.logging.Slf4j

import com.bitwiseor.log.core.event.RequestUpdateEvent

@Slf4j
@ToString
public class RequestUpdateEntryEvent extends RequestUpdateEvent {
	private final Integer id
	private final EntryDetails details
	
	RequestUpdateEntryEvent(final EntryDetails details) {
		log.info("${details.toString()}")
		this.id = details.entryId
		this.details = details
	}
}
