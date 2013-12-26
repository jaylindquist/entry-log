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

import com.bitwiseor.log.core.event.entry.RequestAllEntriesEvent
import com.bitwiseor.log.core.event.entry.RequestEntryEvent
import com.bitwiseor.log.core.service.BacklogService


class BacklogQueryControllerSpec extends Specification {
	MockMvc mockMvc
	
	@Mock
	BacklogService service
	
	@InjectMocks
	BacklogQueryController controller
	
	void setup() {
		MockitoAnnotations.initMocks(this)
		mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build()
	}
	
	def 'all entry renders correctly'() {		
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
	
		
}
