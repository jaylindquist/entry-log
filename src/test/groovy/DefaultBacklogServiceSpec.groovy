import spock.lang.Specification

import com.bitwiseor.log.core.domain.BacklogEntry
import com.bitwiseor.log.core.domain.Game
import com.bitwiseor.log.core.event.entry.RequestEntryEvent
import com.bitwiseor.log.core.repository.MemoryBacklogRepository
import com.bitwiseor.log.core.service.BacklogService
import com.bitwiseor.log.core.service.DefaultBacklogService


class DefaultBacklogServiceSpec extends Specification {
	BacklogService service
	
	void setup() {
		def repo = new MemoryBacklogRepository([
			1, new BacklogEntry(id = 1, game = new Game('Final Fantasy'))])
		
		service = new DefaultBacklogService(repo)
	}
	
	def 'found entry'() {
		when:
		def request = new RequestEntryEvent(1)
		
		then:
		assert service.requestEntry(new RequestEntryEvent(1)).details.id == 1
	}
}
