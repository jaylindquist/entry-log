package com.bitwiseor.log.core.repository

interface CrudRepository<K, T> {
	T read(K id)
	Collection<T> readAll()
	K create(T item)
	void update(T item)
	void delete(Integer id)
}
