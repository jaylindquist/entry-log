package com.bitwiseor.log.rest.controller

import static com.bitwiseor.log.rest.fixture.RestDataFixture.*
import static com.bitwiseor.log.rest.fixture.RestEventFixture.*
import static org.hamcrest.collection.IsCollectionWithSize.*
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc

import spock.lang.Specification

import com.bitwiseor.log.core.event.entry.RequestCreateEntryEvent
import com.bitwiseor.log.core.event.entry.RequestDeleteEntryEvent
import com.bitwiseor.log.core.event.entry.RequestUpdateEntryEvent
import com.bitwiseor.log.core.service.BacklogService


class BacklogCommandControllerSpec extends Specification {
	MockMvc mockMvc
	
	@Mock
	BacklogService service
	
	@InjectMocks
	BacklogCommandController controller
	
	void setup() {
		MockitoAnnotations.initMocks(this)
		mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build()
	}
	
	def 'delete entry'() {
		setup:
		def entryId = 2
		when(service.request((RequestDeleteEntryEvent)any())).thenReturn(entryDeleted(entryId))
		
		when:
		def response = mockMvc.perform(delete("/backlog/${entryId}").accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isOk())
				.andExpect(jsonPath('$.entryId').value(entryId))
				.andDo(print())
	}
	
	def 'delete entry not found'() {
		setup:
		def entryId = 2
		when(service.request((RequestDeleteEntryEvent)any())).thenReturn(entryDeletedNotFound(entryId))
		
		when:
		def response = mockMvc.perform(delete("/backlog/${entryId}").accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isNotFound())
				.andDo(print())
		
	}
	
	def 'delete entry forbidden'() {
		setup:
		def entryId = 2
		when(service.request((RequestDeleteEntryEvent)any())).thenReturn(entryDeletedForbidden(entryId))
		
		when:
		def response = mockMvc.perform(delete("/backlog/${entryId}").accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isForbidden())
				.andDo(print())
		
	}
	
	def 'update entry'() {
		setup:
		def entryId = 2
		def gameName = 'New Name'
		when(service.request((RequestUpdateEntryEvent)any())).thenReturn(entryUpdated(entryId, gameName))
		
		when:
		def response = mockMvc.perform(put("/backlog/${entryId}")
						.content(entryJson(entryId, gameName))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isOk())
				.andExpect(jsonPath('$.entryId').value(entryId))
				.andExpect(jsonPath('$.gameName').value(gameName))
				.andDo(print())		
	}
	
	def 'update entry not found'() {
		setup:
		def entryId = 2
		def gameName = 'New Name'
		when(service.request((RequestUpdateEntryEvent)any())).thenReturn(entryUpdatedNotFound(entryId))
		when(service.request((RequestCreateEntryEvent)any())).thenReturn(entryCreated(entryId, gameName))
		
		when:
		def response = mockMvc.perform(put("/backlog/${entryId}")
						.content(entryJson(entryId, gameName))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isCreated())
				.andExpect(jsonPath('$.entryId').value(entryId))
				.andExpect(jsonPath('$.gameName').value(gameName))
				.andDo(print())
	}
	
	def 'create entry'() {
		setup:
		def entryId = 2
		def gameName = 'New Name'
		when(service.request((RequestCreateEntryEvent)any())).thenReturn(entryCreated(entryId, gameName))
		
		when:
		def response = mockMvc.perform(post("/backlog")
						.content(entryJson(null, gameName))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isCreated())
				.andExpect(jsonPath('$.entryId').value(entryId))
				.andExpect(jsonPath('$.gameName').value(gameName))
				.andDo(print())
	}
	
	
	
	
	
	
	/*
	
	
	
	def 'all orders renders correctly'() {		
		setup:
		when(service.request((RequestAllEntriesEvent)any())).thenReturn(allEntries())		
		
		when:
		def response = mockMvc.perform(get('/backlog').accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isOk())
				.andExpect(jsonPath('$', hasSize(3)))
				.andDo(print())
	}
	
	def 'entry 2 found'() {
		setup:
		def entry = 2
		when(service.request((RequestEntryEvent)any())).thenReturn(entryFound(entry))
		
		when:
		def response = mockMvc.perform(get("/backlog/${entry}").accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isOk())
				.andExpect(jsonPath('$.entryId').value(entry))
				.andDo(print())
	}
	
	def 'entry 4 not found'() {
		setup:
		def entry = 4
		when(service.request((RequestEntryEvent)any())).thenReturn(entryNotFound(entry))
		
		when:
		def response = mockMvc.perform(get("/backlog/${entry}").accept(MediaType.APPLICATION_JSON))
		
		then:
		response.andExpect(status().isNotFound())
				.andDo(print())
	}
	*/
		
}
