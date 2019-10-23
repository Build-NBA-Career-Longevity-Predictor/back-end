package com.lambdaschool.nbapredictor.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.nbapredictor.models.Player;
import com.lambdaschool.nbapredictor.models.SimilarPlayer;
import com.lambdaschool.nbapredictor.services.PlayerService;
import com.lambdaschool.nbapredictor.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.Assert.*;

// mocking service to test controller
// Due to reliance on security, cannot test
//     getMyPlayers
//     createPlayer
//		updatePlayer
//		deletePlayer

@RunWith(SpringRunner.class)
@WebMvcTest(value = PlayerController.class, secure = false)
public class PlayerControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PlayerService playerService;

	@MockBean
	private UserService userService;

	private List<Player> players;

	@Before
	public void setUp() throws Exception {
		players = new ArrayList<>();

		ArrayList<SimilarPlayer> s = new ArrayList<>();
		Player p1 = new Player();
		p1.setName("MJ");
		p1.setSimilarplayers(s);
		p1.setPlayerid(1L);
		players.add(p1);

		Player p2 = new Player();
		p2.setName("MJ");
		p2.setSimilarplayers(s);
		p2.setPlayerid(2L);
		players.add(p2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getPlayerById() throws Exception {
		String apiUrl = "/players/2";

		Mockito.when(playerService.findPlayerById(any(Long.class))).thenReturn(players.get(1));

		RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
		MvcResult r = mockMvc.perform(rb).andReturn();
		String tr = r.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		String er = mapper.writeValueAsString(players.get(1));

		assertEquals(er, tr);
	}

}