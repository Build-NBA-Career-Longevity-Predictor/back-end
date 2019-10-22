package com.lambdaschool.nbapredictor.repository;

import com.lambdaschool.nbapredictor.models.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	@Query(value = "SELECT * FROM PLAYERS WHERE userid = :userid", nativeQuery = true)
	ArrayList<Player> getAllPlayersFromUser(long userid);
}
