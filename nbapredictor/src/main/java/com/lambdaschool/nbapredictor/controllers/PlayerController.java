package com.lambdaschool.nbapredictor.controllers;

import com.lambdaschool.nbapredictor.models.Player;
import com.lambdaschool.nbapredictor.models.User;
import com.lambdaschool.nbapredictor.services.PlayerService;
import com.lambdaschool.nbapredictor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/all", produces = {"application/json"})
	public ResponseEntity<?> getMyPlayers(Authentication authentication){
		User me = userService.findByName(authentication.getName());
		return new ResponseEntity<>(playerService.findAllMyPlayers(me.getUserid()), HttpStatus.OK);
	}

	@GetMapping(value = "/{playerid}", produces = {"application/json"})
	public ResponseEntity<?> getPlayerById(@PathVariable long playerid){
		System.out.println("Outside " + playerService.findPlayerById(playerid));
		return new ResponseEntity<>(playerService.findPlayerById(playerid), HttpStatus.OK);
	}

	@PostMapping(value = "/createplayer", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> createPlayer(@Valid @RequestBody Player newPlayer, Authentication authentication){
		User me = userService.findByName(authentication.getName());
		newPlayer.setUser(me);
		playerService.save(newPlayer);
		return new ResponseEntity<>(playerService.findAllMyPlayers(me.getUserid()), HttpStatus.CREATED);
	}

	@PutMapping(value = "update/{playerid}", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> updatePlayer(@RequestBody Player player, @PathVariable long playerid, Authentication authentication){
		User me = userService.findByName(authentication.getName());
		player.setUser(me);
		playerService.update(player, playerid);
		return new ResponseEntity<>(playerService.findAllMyPlayers(me.getUserid()), HttpStatus.OK);
	}

	@DeleteMapping(value = "delete/{playerid}", produces = {"application/json"})
	public ResponseEntity<?> deletePlayer(@PathVariable long playerid, Authentication authentication){
		User me = userService.findByName(authentication.getName());
		playerService.delete(playerid, me.getUserid());
		return new ResponseEntity<>(playerService.findAllMyPlayers(me.getUserid()), HttpStatus.OK);
	}
}
