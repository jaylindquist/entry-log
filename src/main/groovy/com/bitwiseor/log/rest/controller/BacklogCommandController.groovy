package com.bitwiseor.log.rest.controller

import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.util.UriComponentsBuilder

import com.bitwiseor.log.core.event.entry.EntryDetails
import com.bitwiseor.log.core.event.entry.RequestCreateEntryEvent
import com.bitwiseor.log.core.event.entry.RequestDeleteEntryEvent
import com.bitwiseor.log.core.event.entry.RequestUpdateEntryEvent
import com.bitwiseor.log.core.service.BacklogService
import com.bitwiseor.log.rest.domain.BacklogEntry

@Slf4j
@Controller
@RequestMapping('/backlog')
class BacklogCommandController {
	
	@Autowired
	private BacklogService backlogService
	
	/**
	 * Delete an entry from the service. Error is entry doesn't exist
	 * @param id ID of the entry
	 * @return deleted entry
	 */
	@RequestMapping(method=RequestMethod.DELETE, value='/{id}')
	ResponseEntity<BacklogEntry> removeEntry(@PathVariable Integer id) {
		def event = backlogService.request(new RequestDeleteEntryEvent(id))
		
		if(!event.entityFound) {
			return new ResponseEntity<BacklogEntry>(HttpStatus.NOT_FOUND)
		}
		
		def entry = BacklogEntry.fromEntryDetails(event.details)
		
		if(!event.deletionCompleted) {
			return new ResponseEntity<BacklogEntry>(entry, HttpStatus.FORBIDDEN)
		}
		
		return new ResponseEntity<BacklogEntry>(entry, HttpStatus.OK)
	}
	
	/**
	 * Update an entry, or create if it doesn't exist
	 * @param entry Entry to update
	 * @return New or updated entry
	 */
	@RequestMapping(method=RequestMethod.PUT, value='/{id}')
	ResponseEntity<BacklogEntry> updateEntry(@PathVariable Integer id, @RequestBody BacklogEntry entry, UriComponentsBuilder builder) {
		def details = entry.toEntryDetails(id)
		def event = backlogService.request(new RequestUpdateEntryEvent(details))
		log.info("${event.toString()}")
		
		if(!event.entityExists) {
			return create(details, builder)
		}
		
		def updatedEntry = BacklogEntry.fromEntryDetails(event.details)
		log.info("${updatedEntry.toString()}")
		return new ResponseEntity<BacklogEntry>(updatedEntry, HttpStatus.OK)
		
	}
	
	/**
	 * Create an entry based on details
	 * @param details
	 * @return
	 */
	ResponseEntity<BacklogEntry> create(EntryDetails details, UriComponentsBuilder builder) {
		def event = backlogService.request(new RequestCreateEntryEvent(details))
		
		def createdEntry = BacklogEntry.fromEntryDetails(event.details)
		
		def headers = new HttpHeaders()
		headers.setLocation(builder.path("/backlog/{id}")
			.buildAndExpand(event.id.toString()).toUri())
		
		return new ResponseEntity<BacklogEntry>(createdEntry, headers, HttpStatus.CREATED)	
	}
	
	
	/**
	 * Create new entry
	 * 
	 * @param entry new entry
	 * @param builder
	 * @return new entry
	 */
	@RequestMapping(method=RequestMethod.POST)
	ResponseEntity<BacklogEntry> createEntry(@RequestBody BacklogEntry entry, UriComponentsBuilder builder) {
		return create(entry.toEntryDetails(), builder)		
	}
}
