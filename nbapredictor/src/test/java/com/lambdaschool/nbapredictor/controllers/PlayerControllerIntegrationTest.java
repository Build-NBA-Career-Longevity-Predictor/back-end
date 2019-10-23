package com.lambdaschool.nbapredictor.controllers;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.get;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	public static String asJsonString(final Object obj)
	{
		try
		{
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	@Before
	public void setUp() throws Exception {
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@After
	public void tearDown() throws Exception {
	}

	@WithUserDetails("admin")
	@Test
	public void A_getMyPlayers() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/players/all"))
				.andExpect(status().isOk()).andExpect(content().string(containsString("admin")));

	}

	@WithUserDetails("admin")
	@Test
	public void B_getPlayerById() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/players/{playerid}", 9))
				.andExpect(status().isOk()).andExpect(content().string(containsString("MJ")));

	}

	@WithUserDetails("admin")
	@Test
	public void C_createPlayer() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/players/createplayer")
				.content("{\"imgurl\":\"urlstringhere\",\"name\":\"Kobe Bryant\"," +
						"\"position\":\"Guard\",\"height\":\"6-4\",\"weight\":\"230lb\"," +
						"\"college\":\"SOme college\",\"draft_year\":1994,\"draft_pick\":1," +
						"\"draft_by\":\"LAK\",\"pts_pg\":27,\"rebounds_pg\":7," +
						"\"assists_pg\":8,\"mins_pg\":36,\"prediction\":12,\"similarplayers" +
						"\":[]}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@WithUserDetails("admin")
	@Test
	public void D_updatePlayer() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.put("/players/update/9")
				.content("{\"name\": \"UPDATED\"}").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@WithUserDetails("admin")
	@Test
	public void Z_deletePlayer() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/players/delete/9"))
				.andExpect(status().isOk());
	}
}