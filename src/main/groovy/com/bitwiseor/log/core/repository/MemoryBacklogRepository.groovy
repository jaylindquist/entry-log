package com.bitwiseor.log.core.repository

import groovy.util.logging.Slf4j

import org.springframework.stereotype.Repository

import com.bitwiseor.log.core.domain.BacklogEntry
import com.bitwiseor.log.core.exception.RepositoryException

@Slf4j
@Repository
class MemoryBacklogRepository implements BacklogRepository {
	private Map<Integer,BacklogEntry> entries = [:]
	
	@Override
	public BacklogEntry read(Integer id) {
		log.info("${id.toString()}")
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
	public Integer create(BacklogEntry item) {
		log.info("${item.toString()}")
		def modifiable = new HashMap<Integer,BacklogEntry>(entries)
		if(item.id && entries.containsKey(item.id)) {
			throw new RepositoryException("Entry ${item.id} does exists in repository")
		} else {
			def id = item.id ?: nextId()
			modifiable[id] = item
			this.entries = Collections.unmodifiableMap(modifiable)
			return id
		}
	}

	@Override
	public void update(BacklogEntry item) {
		log.info("${item.toString()}")
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
		log.info("${id.toString()}")
		if(entries.containsKey(id)) {
			def modifiable = new HashMap<Integer,BacklogEntry>(entries)
			modifiable.remove(id)
			this.entries = Collections.unmodifiableMap(modifiable)
		} else {
			throw new RepositoryException("Entry ${id} does not exist in repository")
		}
	}

	private Integer nextId() {
		return entries.isEmpty()? 1:entries.keySet().max() + 1
	}
}
