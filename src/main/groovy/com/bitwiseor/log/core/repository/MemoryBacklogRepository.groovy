package com.bitwiseor.log.core.repository

import com.bitwiseor.log.core.domain.BacklogEntry
import com.bitwiseor.log.core.exception.RepositoryException

class MemoryBacklogRepository implements BacklogRepository {
	private Map<Long,BacklogEntry> entries

	MemoryBacklogRepository(final Map<Long,BacklogEntry> entries) {
		this.entries = Collections.unmodifiableMap(entries)
	}
	
	@Override
	public BacklogEntry read(Long id) {
		if(entries.containsKey(id)) {
		return entries[id]
		} else {
			throw new RepositoryException("Entry ${id} does not exist in repository")
		}
	}

	@Override
	public List<BacklogEntry> readAll() {
		return Collections.unmodifiableList(entries.values())
	}

	@Override
	public void create(BacklogEntry item) {
		item.id = nextId()
		if(!entries.containsKey(item.id)) {
			def modifiable = new HashMap<Long,BacklogEntry>(entries)
			modifiable[item.id] = item
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RepositoryException("Entry ${item.id} already exists in repository")
		}
	}

	@Override
	public void update(BacklogEntry item) {
		if(entries.containsKey(item.id)) {
			def modifiable = new HashMap<Long,BacklogEntry>(entries)
			modifiable[item.id] = item
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RepositoryException("Entry ${item.id} does not exist in repository")
		}
	}

	@Override
	public void delete(Long id) {
		if(entries.containsKey(id)) {
			def modifiable = new HashMap<Long,BacklogEntry>(entries)
			modifiable.remove(id)
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RepositoryException("Entry ${id} does not exist in repository")
		}
	}

	private Long nextId() {
		return entries.keySet().max() + 1
	}
}