package com.bitwiseor.log.core.service

import com.bitwiseor.log.core.event.entry.*
import com.bitwiseor.log.core.repository.BacklogRepository

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntryDeletedEvent requestDeleteEntry(RequestDeleteEntryEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntryUpdatedEvent requestUpdateEntry(RequestUpdateEntryEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntryCreatedEvent requestCreateEntry(RequestCreateEntryEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
