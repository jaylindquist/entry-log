package com.bitwiseor.log.core.service

import spock.lang.Specification
import spock.lang.Unroll

import com.bitwiseor.log.core.domain.BacklogEntry
import com.bitwiseor.log.core.domain.Game
import com.bitwiseor.log.core.event.entry.RequestAllEntriesEvent
import com.bitwiseor.log.core.event.entry.RequestCreateEntryEvent
import com.bitwiseor.log.core.event.entry.RequestDeleteEntryEvent
import com.bitwiseor.log.core.event.entry.RequestEntryEvent
import com.bitwiseor.log.core.event.entry.RequestUpdateEntryEvent
import com.bitwiseor.log.core.repository.MemoryBacklogRepository

class DefaultBacklogServiceSpec extends Specification {
	BacklogService service
	MemoryBacklogRepository repo
	
	void setup() {
		repo = new MemoryBacklogRepository([
			1: new BacklogEntry(id:1, game:new Game('Final Fantasy')),
			2: new BacklogEntry(id:2, game:new Game('Fire Emblem'))
		])
		
		service = new DefaultBacklogService(repo)
	}
	
	def 'request all entries'() {
		when:
		def request = new RequestAllEntriesEvent()
		def event = service.request(request)
		
		then:
		event?.entries?.size() == 2
	}
	
	@Unroll
	def 'request single entry'() {
		when:
		def request = new RequestEntryEvent(id)
		def event = service.request(request)
	
		then:
		event?.details?.entryId == result
		event.entityFound == found
		
		where:
		id	|| result	| found
		1	|| 1		| true
		2	|| 2		| true
		3	|| null		| false
	}
	
	def 'request delete entry'() {		
		when:
		def details = entry.toEntryDetails()
		def request = new RequestDeleteEntryEvent(details)
		def event = service.request(request)
		
		then:
		repo.entries.size() == result
		event.entityFound == found
		
		where:
		entry													|| result	| found
		new BacklogEntry(id:1, game:new Game('Final Fantasy'))	|| 1		| true
		new BacklogEntry(id:3, game:new Game('Not available'))	|| 2		| false
	}
	
	@Unroll
	def 'request update entry'() {		
		when:
		def details = entry.toEntryDetails()
		def request = new RequestUpdateEntryEvent(details)
		def event = service.request(request)
		
		then:
		event.entityExists == exists
		event.id == id
		
		where:
		entry													|| id	| exists
		new BacklogEntry(id:1, game:new Game('Updated name'))	|| 1	| true
		new BacklogEntry(id:3, game:new Game('Not available'))	|| 3	| false
	}
	
	@Unroll
	def 'request create entry'() {
		when:
		def details = entry.toEntryDetails()
		def request = new RequestCreateEntryEvent(details)
		def event = service.request(request)
		
		then:
		repo.entries.size() == result
		event.entityExists == found
		
		where:
		entry													|| result	| found
		new BacklogEntry(id:1, game:new Game('Already exists'))	|| 2		| true
		new BacklogEntry(id:3, game:new Game('Not available'))	|| 3		| false
	}
	
}
