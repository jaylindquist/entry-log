package com.bitwiseor.log.core.service

import groovy.util.logging.Slf4j

import com.bitwiseor.log.core.domain.BacklogEntry
import com.bitwiseor.log.core.event.entry.*
import com.bitwiseor.log.core.exception.RepositoryException
import com.bitwiseor.log.core.repository.BacklogRepository

@Slf4j
class DefaultBacklogService implements BacklogService {
	private final BacklogRepository repo
	
	DefaultBacklogService(BacklogRepository repo) {
		this.repo = repo
	}

	@Override
	public AllEntriesEvent request(RequestAllEntriesEvent event) {
		def sorted = new ArrayList(repo.readAll())
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
			def details = repo.read(event.id).toEntryDetails()
			return new EntryReadEvent(details.entryId, details)
		} catch(RepositoryException ex) {
			log.warn("Unable to complete requestEntry", ex)
			return EntryReadEvent.notFound(event.id)
		}
	}

	@Override
	public EntryDeletedEvent request(RequestDeleteEntryEvent event) {
		try {
			repo.delete(event.id)
			return new EntryDeletedEvent(event.id, event.details)
		} catch(RepositoryException ex) {
			log.warn("Unable to complete requestDeleteEntry", ex)
			return EntryDeletedEvent.notFound(event.id)
		}
	}

	@Override
	public EntryUpdatedEvent request(RequestUpdateEntryEvent event) {
		try {
			repo.update(BacklogEntry.fromEntryDetails(event.details))
			return new EntryUpdatedEvent(event.id, event.details)
		} catch(RepositoryException ex) {
			log.warn("Unable to complete requestUpdateEntry", ex)
			return EntryUpdatedEvent.notFound(event.id)
		}
	}

	@Override
	public EntryCreatedEvent request(RequestCreateEntryEvent event) {
		def entry = BacklogEntry.fromEntryDetails(event.details)
		try {
			repo.create(entry)
			return new EntryCreatedEvent(entry.id, entry.toEntryDetails())
		} catch(RepositoryException ex) {
			log.warn("Unable to complete requestCreateEntry", ex)
			return EntryCreatedEvent.exists(entry.id)
		}
	}

}
