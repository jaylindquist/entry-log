package com.bitwiseor.log.core.repository

interface CrudRepository<T> {
	T read(Integer id)
	Collection<T> readAll()
	void create(T item)
	void update(T item)
	void delete(Integer id)
}
