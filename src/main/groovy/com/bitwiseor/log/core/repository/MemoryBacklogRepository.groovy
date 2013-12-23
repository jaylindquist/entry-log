package com.bitwiseor.log.core.repository

import com.bitwiseor.log.core.domain.BacklogEntry
import com.bitwiseor.log.core.exception.RepositoryException

class MemoryBacklogRepository implements BacklogRepository {
	private Map<Integer,BacklogEntry> entries

	MemoryBacklogRepository(final Map<Integer,BacklogEntry> entries) {
		this.entries = Collections.unmodifiableMap(entries)
	}
	
	@Override
	public BacklogEntry read(Integer id) {
		if(entries.containsKey(id)) {
			return entries[id]
		} else {
			throw new RepositoryException("Entry ${id} does not exist in repository")
		}
	}

	@Override
	public Collection<BacklogEntry> readAll() {
		return Collections.unmodifiableCollection(entries.values())
	}

	@Override
	public void create(BacklogEntry item) {
		if(!entries.containsKey(item.id)) {
			def modifiable = new HashMap<Integer,BacklogEntry>(entries)
			modifiable[item.id] = item
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RepositoryException("Entry ${item.id} already exists in repository")
		}
	}

	@Override
	public void update(BacklogEntry item) {
		if(entries.containsKey(item.id)) {
			def modifiable = new HashMap<Integer,BacklogEntry>(entries)
			modifiable[item.id] = item
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RepositoryException("Entry ${item.id} does not exist in repository")
		}
	}

	@Override
	public void delete(Integer id) {
		if(entries.containsKey(id)) {
			def modifiable = new HashMap<Integer,BacklogEntry>(entries)
			modifiable.remove(id)
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RepositoryException("Entry ${id} does not exist in repository")
		}
	}

	private Integer nextId() {
		return entries.keySet().max() + 1
	}
}
