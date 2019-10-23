package com.lambdaschool.nbapredictor.services;

import com.lambdaschool.nbapredictor.NbaCareerLongevityPredictorApplication;
import com.lambdaschool.nbapredictor.models.Player;
import com.lambdaschool.nbapredictor.models.SimilarPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NbaCareerLongevityPredictorApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerServiceImplTest {
	@Autowired
	private PlayerService playerService;

	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void A_findAllMyPlayers() {
		assertEquals(1, playerService.findAllMyPlayers(4).size());
	}

	@Test
	public void B_findPlayerById() {
		assertEquals("MJ", playerService.findPlayerById(9).getName());
	}

	@Test
	public void C_save() {
		ArrayList<SimilarPlayer> s = new ArrayList<>();
		Player p1 = new Player();
		p1.setName("KB");
		p1.setSimilarplayers(s);
		p1.setUser(userService.findUserById(4));
		playerService.save(p1);

		assertEquals(2, playerService.findAllMyPlayers(4).size());
	}

	@Test
	public void D_update() {
		ArrayList<SimilarPlayer> s = new ArrayList<>();
		Player p1 = new Player();
		p1.setName("KB");
		p1.setSimilarplayers(s);
		p1.setUser(userService.findUserById(4));
		p1 = playerService.save(p1);

		p1.setName("LW");
		p1 = playerService.update(p1, p1.getPlayerid() );

		assertEquals("LW", p1.getName() );

	}

	@Test
	public void E_delete() {
		playerService.delete(9);
		assertEquals(2, playerService.findAllMyPlayers(4).size());
	}
}