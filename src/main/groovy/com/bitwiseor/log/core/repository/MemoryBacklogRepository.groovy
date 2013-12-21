package com.bitwiseor.log.core.repository

import java.util.List

import com.bitwiseor.log.core.domain.BacklogEntry;

class MemoryBacklogRepository implements BacklogRepository {
	private Map<Long,BacklogEntry> entries

	MemoryBacklogRepository(final Map<Long,BacklogEntry> entries) {
		this.entries = Collections.unmodifiableMap(entries)
	}
	
	@Override
	public BacklogEntry read(Long id) {
		return entries[id]
	}

	@Override
	public List<BacklogEntry> readAll() {
		return Collections.unmodifiableList(entries.values())
	}

	@Override
	public void create(BacklogEntry item) {
		if(!entries.containsKey(item.id)) {
			def modifiable = new HashMap<Long,BacklogEntry>(entries)
			modifiable[item.id] = item
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RuntimeException("Entry ${item.id} already exists in repository")
		}
	}

	@Override
	public void update(BacklogEntry item) {
		if(entries.containsKey(item.id)) {
			def modifiable = new HashMap<Long,BacklogEntry>(entries)
			modifiable[item.id] = item
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RuntimeException("Entry ${item.id} does not exists in repository")
		}
	}

	@Override
	public void delete(BacklogEntry item) {
		if(entries.containsKey(item.id)) {
			def modifiable = new HashMap<Long,BacklogEntry>(entries)
			modifiable.remove(item.id)
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RuntimeException("Entry ${item.id} does not exists in repository")
		}
	}

}
