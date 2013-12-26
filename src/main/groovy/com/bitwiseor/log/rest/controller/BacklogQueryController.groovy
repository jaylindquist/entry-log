package com.bitwiseor.log.rest.controller

import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import com.bitwiseor.log.core.event.entry.AllEntriesEvent
import com.bitwiseor.log.core.event.entry.EntryReadEvent
import com.bitwiseor.log.core.event.entry.RequestAllEntriesEvent
import com.bitwiseor.log.core.event.entry.RequestEntryEvent
import com.bitwiseor.log.core.service.BacklogService
import com.bitwiseor.log.rest.domain.*

@Slf4j
@Controller
@RequestMapping('/backlog')
class BacklogQueryController {
	
	@Autowired
	private BacklogService backlogService

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	List<BacklogEntry> getAllEntries() {	
		AllEntriesEvent event = backlogService.request(new RequestAllEntriesEvent())
		return event.entries.collect { details -> BacklogEntry.fromEntryDetails(details) }
	}
	
	@RequestMapping(method = RequestMethod.GET, value = '/{id}')
	ResponseEntity<BacklogEntry> viewEntry(@PathVariable Integer id) {
		EntryReadEvent event = backlogService.request(new RequestEntryEvent(id))
		
		if(!event.entityFound) {
			return new ResponseEntity<BacklogEntry>(HttpStatus.NOT_FOUND)
		} else {
			return new ResponseEntity<BacklogEntry>(BacklogEntry.fromEntryDetails(event.details), HttpStatus.OK)
		}
	}
}
