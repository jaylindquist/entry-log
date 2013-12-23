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
	public AllEntriesEvent requestAll(RequestAllEntriesEvent event) {
		return new AllEntriesEvent(repo.readAll()*.toEntryDetails()) 
	}

	@Override
	public EntryReadEvent requestEntry(RequestEntryEvent event) {
		try {
			def details = repo.read(event.id).toEntryDetails()
			return new EntryReadEvent(details.entryId, details)
		} catch(RepositoryException ex) {
			log.warn(ex)
			return EntryReadEvent.notFound(event.id)
		}
	}

	@Override
	public EntryDeletedEvent requestDeleteEntry(RequestDeleteEntryEvent event) {
		try {
			repo.delete(event.id)
			return new EntryDeletedEvent(event.id, event.details)
		} catch(RepositoryException ex) {
			log.warn(ex)
			return EntryDeletedEvent.notFound(event.id)
		}
	}

	@Override
	public EntryUpdatedEvent requestUpdateEntry(RequestUpdateEntryEvent event) {
		try {
			repo.update(BacklogEntry.fromEntryDetails(event.details))
			return new EntryUpdatedEvent(event.id, event.details)
		} catch(RepositoryException ex) {
			log.warn(ex)
			return EntryUpdatedEvent.(event.id)
		}
	}

	@Override
	public EntryCreatedEvent requestCreateEntry(RequestCreateEntryEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
