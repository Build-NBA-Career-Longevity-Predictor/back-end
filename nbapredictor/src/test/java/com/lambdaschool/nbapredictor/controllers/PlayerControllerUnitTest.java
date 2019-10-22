package com.lambdaschool.nbapredictor.controllers;

import com.lambdaschool.nbapredictor.services.PlayerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
	private PlayerService userService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getMyPlayers() {
	}

	@Test
	public void getPlayerById() {
	}

	@Test
	public void createPlayer() {
	}

	@Test
	public void updatePlayer() {
	}

	@Test
	public void deletePlayer() {
	}
}