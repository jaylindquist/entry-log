package com.bitwiseor.log.core.service

import groovy.transform.ToString
import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.bitwiseor.log.core.domain.BacklogEntry
import com.bitwiseor.log.core.event.entry.*
import com.bitwiseor.log.core.exception.RepositoryException
import com.bitwiseor.log.core.repository.BacklogRepository

@Slf4j
@ToString
@Service
class DefaultBacklogService implements BacklogService {
	@Autowired
	private final BacklogRepository backlogRepository
	
	DefaultBacklogService(){}
	
	DefaultBacklogService(BacklogRepository backlogRepository) {
		this.backlogRepository = backlogRepository
	}

	@Override
	public AllEntriesEvent request(RequestAllEntriesEvent event) {
		def sorted = new ArrayList(backlogRepository.readAll())
		Collections.sort(sorted, new Comparator<BacklogEntry>() {
				int compare(BacklogEntry left, BacklogEntry right) {
					return right.id - left.id
				}
			})
		return new AllEntriesEvent(sorted*.toEntryDetails()) 
	}

	@Override
	public EntryReadEvent request(RequestEntryEvent event) {
		try {
			def details = backlogRepository.read(event.id).toEntryDetails()
			return new EntryReadEvent(details)
		} catch(RepositoryException ex) {
			log.warn("Unable to complete requestEntry", ex)
			return EntryReadEvent.notFound(event.id)
		}
	}

	@Override
	public EntryDeletedEvent request(RequestDeleteEntryEvent event) {
		try {
			def details = backlogRepository.read(event.id).toEntryDetails()
			backlogRepository.delete(event.id)
			return new EntryDeletedEvent(details)
		} catch(RepositoryException ex) {
			log.warn("Unable to complete requestDeleteEntry", ex)
			return EntryDeletedEvent.notFound(event.id)
		}
	}

	@Override
	public EntryUpdatedEvent request(RequestUpdateEntryEvent event) {
		log.info("${event.toString()}")
		try {
			backlogRepository.update(BacklogEntry.fromEntryDetails(event.details))
			return new EntryUpdatedEvent(event.details)
		} catch(RepositoryException ex) {
			log.warn("Unable to complete requestUpdateEntry", ex)
			return EntryUpdatedEvent.notFound(event.id)
		}
	}

	@Override
	public EntryCreatedEvent request(RequestCreateEntryEvent event) {
		log.info("${event.toString()}")
		def entry = BacklogEntry.fromEntryDetails(event.details)
		def id = backlogRepository.create(entry)
		entry.id = id
		return new EntryCreatedEvent(entry.toEntryDetails())
	}

}
