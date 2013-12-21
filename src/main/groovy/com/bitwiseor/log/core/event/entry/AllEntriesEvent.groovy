package com.bitwiseor.log.core.event.entry

import com.bitwiseor.log.core.event.ReadEvent

public class AllEntriesEvent extends ReadEvent {
	final List<EntryDetails> entries
	
	AllEntriesEvent(final List<EntryDetails> entries) {
		this.entries = Collections.unmodifiableList(entries)
	}
}
