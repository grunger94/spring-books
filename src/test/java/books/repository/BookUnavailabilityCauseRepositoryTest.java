/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package books.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static books.repository.BookUnavailabilityCauseRepository.PATH;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookUnavailabilityCauseRepositoryTest {

	private String causeOfUnavailability = "stolen";
	private String updatedCauseOfUnavailability = "lost";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookUnavailabilityCauseRepository bookUnavailabilityCauseRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		bookUnavailabilityCauseRepository.deleteAll();
	}

	@Test
	public void shouldReturnRepositoryIndex() throws Exception {

		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(
				jsonPath("$._links." + PATH).exists());
	}

	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/" + PATH).content(
				"{\"cause\": \"" + causeOfUnavailability + "\"}")).andExpect(
						status().isCreated()).andExpect(
								header().string("Location", containsString(PATH + "/")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/" + PATH).content(
				"{\"cause\": \"" + causeOfUnavailability + "\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.cause").value(causeOfUnavailability));
	}

	@Test
	public void shouldQueryEntity() throws Exception {

		mockMvc.perform(post("/" + PATH).content(
				"{ \"cause\": \"" + causeOfUnavailability + "\"}")).andExpect(
						status().isCreated());

		mockMvc.perform(
				get("/" + PATH + "/search/findByCause?cause={cause}", causeOfUnavailability)).andExpect(
						status().isOk()).andExpect(
								jsonPath("$._embedded." + PATH + "[0].cause").value(
										causeOfUnavailability));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/" + PATH).content(
				"{\"cause\": \"" + causeOfUnavailability + "\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"cause\": \"" + updatedCauseOfUnavailability + "\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.cause").value(updatedCauseOfUnavailability));
	}

	@Test
	public void shouldPartiallyUpdateEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/" + PATH).content(
				"{\"cause\": \"" + causeOfUnavailability + "\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(
				patch(location).content("{\"cause\": \"" + updatedCauseOfUnavailability + "\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.cause").value(updatedCauseOfUnavailability)).andExpect(
						jsonPath("$.cause").value(updatedCauseOfUnavailability));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/" + PATH).content(
				"{ \"cause\": \"" + causeOfUnavailability + "\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}