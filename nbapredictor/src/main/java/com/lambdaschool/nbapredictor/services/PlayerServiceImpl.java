package com.lambdaschool.nbapredictor.services;

import com.lambdaschool.nbapredictor.exceptions.ResourceNotFoundException;
import com.lambdaschool.nbapredictor.models.Player;
import com.lambdaschool.nbapredictor.models.SimilarPlayer;
import com.lambdaschool.nbapredictor.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	PlayerRepository playerRepo;

	@Override
	public List<Player> findAllMyPlayers(long userid) {

		return playerRepo.getAllPlayersFromUser(userid);
	}

	@Override
	public Player findPlayerById(long id) {
		System.out.println("Inside " + playerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player Id: " + id + " Not found.")));
		return playerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player Id: " + id + " Not found."));
	}

	@Transactional
	@Override
	public Player save(Player player) {
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
		newPlayer.setUser(player.getUser());

		ArrayList<SimilarPlayer> simList = new ArrayList<>();


		for(SimilarPlayer s : player.getSimilarplayers()){
			SimilarPlayer newSimilar = new SimilarPlayer();

			newSimilar.setImgurl(s.getImgurl());
			newSimilar.setName(s.getName());
			newSimilar.setPosition(s.getPosition());
			newSimilar.setWeight(s.getWeight());
			newSimilar.setHeight(s.getHeight());
			newSimilar.setCollege(s.getCollege());
			newSimilar.setDraft_year(s.getDraft_year());
			newSimilar.setDraft_pick(s.getDraft_pick());
			newSimilar.setDrafted_by(s.getDrafted_by());

			newSimilar.setPts_pg(s.getPts_pg());
			newSimilar.setRebounds_pg(s.getRebounds_pg());
			newSimilar.setAssists_pg(s.getAssists_pg());
			newSimilar.setMins_pg(s.getMins_pg());

			newSimilar.setPlayer(newPlayer);

			simList.add(newSimilar);
		}

		newPlayer.setSimilarplayers(simList);

		return playerRepo.save(newPlayer);
	}

	@Transactional
	@Override
	public Player update(Player player, long id) {

		Player currentPlayer = playerRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player Id: " + id + " Not found."));

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

		currentPlayer.setUser(player.getUser());

		return playerRepo.save(currentPlayer);
	}


	@Transactional
	@Override
	public void delete(long playerid) {
		playerRepo.deleteById(playerid);
	}
}
