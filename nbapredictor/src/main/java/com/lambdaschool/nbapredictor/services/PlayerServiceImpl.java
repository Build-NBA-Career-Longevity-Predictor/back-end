package com.lambdaschool.nbapredictor.services;

import com.lambdaschool.nbapredictor.exceptions.ResourceNotFoundException;
import com.lambdaschool.nbapredictor.models.Player;
import com.lambdaschool.nbapredictor.models.SimilarPlayer;
import com.lambdaschool.nbapredictor.models.User;
import com.lambdaschool.nbapredictor.repository.PlayerRepository;
import com.lambdaschool.nbapredictor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	PlayerRepository playerRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public List<Player> findAllMyPlayers(long userid) {
		return playerRepo.getAllPlayersFromUser(userid);
	}

	@Override
	public Player findPlayerById(long id) {
		return playerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player Id: " + id + " Not found."));
	}

	@Override
	public List<Player> save(Player player, User user) {
		Player newPlayer = new Player();
		newPlayer.setImgurl(player.getImgurl());
		newPlayer.setName(player.getName());
		newPlayer.setPosition(player.getPosition());
		newPlayer.setWeight(player.getWeight());
		newPlayer.setHeight(player.getHeight());
		newPlayer.setCollege(player.getCollege());
		newPlayer.setDraft_year(player.getDraft_year());
		newPlayer.setDraft_pick(player.getDraft_pick());
		newPlayer.setDrafted_by(player.getDrafted_by());
		newPlayer.setPts_pg(player.getPts_pg());
		newPlayer.setRebounds_pg(player.getRebounds_pg());
		newPlayer.setAssists_pg(player.getAssists_pg());
		newPlayer.setMins_pg(player.getMins_pg());
		newPlayer.setPrediction(player.getPrediction());

		for(SimilarPlayer s : player.getSimilarplayers()){
			newPlayer.getSimilarplayers().add(s);
		}

		newPlayer.setUser(user);

		playerRepo.save(newPlayer);

		return findAllMyPlayers(user.getUserid());

	}

	@Override
	public List<Player> update(long id, Player player) {
		Player currentPlayer = new Player();

		if(player.getImgurl() != null){
			currentPlayer.setImgurl(player.getImgurl());
		}

		if(player.getName() != null){
			currentPlayer.setName(player.getName());
		}

		if(player.getPosition() != null){
			currentPlayer.setPosition(player.getPosition());
		}

		if(player.getWeight() != null){
			currentPlayer.setWeight(player.getWeight());
		}

		if(player.getHeight() != null){
			currentPlayer.setHeight(player.getHeight());
		}

		if(player.getCollege() != null){
			currentPlayer.setCollege(player.getCollege());
		}

		if(player.getDraft_year() > 0){
			currentPlayer.setDraft_year(player.getDraft_year());
		}

		if(player.getDraft_pick() > 0){
			currentPlayer.setDraft_pick(player.getDraft_pick());
		}

		if(player.getDrafted_by() != null){
			currentPlayer.setDrafted_by(player.getDrafted_by());
		}

		if(player.getPts_pg() > 0){
			currentPlayer.setPts_pg(player.getPts_pg());
		}

		if(player.getRebounds_pg() > 0){
			currentPlayer.setRebounds_pg(player.getRebounds_pg());
		}

		if(player.getAssists_pg() > 0){
			currentPlayer.setAssists_pg(player.getAssists_pg());
		}

		if(player.getMins_pg() > 0){
			currentPlayer.setMins_pg(player.getMins_pg());
		}

		if(player.getPrediction() > 0){
			currentPlayer.setPrediction(player.getPrediction());
		}

		if(player.getSimilarplayers().size() > 0){
			for(SimilarPlayer s : player.getSimilarplayers()){
				currentPlayer.getSimilarplayers().add(s);
			}
		}

		playerRepo.save(currentPlayer);

		return findAllMyPlayers(player.getUser().getUserid());
	}
}
