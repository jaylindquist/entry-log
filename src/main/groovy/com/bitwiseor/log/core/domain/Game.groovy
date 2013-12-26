package com.bitwiseor.log.core.domain

import groovy.transform.ToString;

@ToString
class Game {
	String title
	
	Game(String title) {
		this.title = title
	}
}
