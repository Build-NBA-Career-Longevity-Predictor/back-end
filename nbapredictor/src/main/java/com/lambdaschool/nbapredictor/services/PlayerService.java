package com.lambdaschool.nbapredictor.services;

import com.lambdaschool.nbapredictor.models.Player;
import com.lambdaschool.nbapredictor.models.User;

import java.util.List;

public interface PlayerService {
	List<Player> findAllMyPlayers(long userid);

	Player findPlayerById(long id);

	Player save(Player player);

	Player update(Player player, long id);

	void delete(long playerid);
}
