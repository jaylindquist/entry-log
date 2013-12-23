package com.bitwiseor.log.core.exception

class RepositoryException extends RuntimeException {
	RepositoryException(GString msg) {
		super(msg.toString())
	}
	
	RepositoryException(GString msg, Throwable cause) {
		super(msg.toString(), cause)
	}
}
