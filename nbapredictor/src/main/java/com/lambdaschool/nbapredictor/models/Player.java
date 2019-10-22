package com.lambdaschool.nbapredictor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "players")
public class Player extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long playerid;

	private String imgurl;

	private String name;
	private String position;
	private String height;
	private String weight;
	private String college;
	private int draft_year;
	private int draft_pick;
	private String drafted_by;
	private double pts_pg;
	private double rebounds_pg;
	private double assists_pg;
	private double mins_pg;
	private int prediction;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("player")
	private List<SimilarPlayer> similarplayers;

	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIgnoreProperties("players")
	private User user;

	public Player() {
	}

	public Player(String imgurl, String name, String position, String height, String weight, String college, int draft_year, int draft_pick, String drafted_by, double pts_pg, double rebounds_pg, double assists_pg, double mins_pg, int prediction, List<SimilarPlayer> similarplayers) {
		this.imgurl = imgurl;
		this.name = name;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.college = college;
		this.draft_year = draft_year;
		this.draft_pick = draft_pick;
		this.drafted_by = drafted_by;
		this.pts_pg = pts_pg;
		this.rebounds_pg = rebounds_pg;
		this.assists_pg = assists_pg;
		this.mins_pg = mins_pg;
		this.prediction = prediction;
		for(SimilarPlayer s : similarplayers){
			s.setPlayer(this);
		}
		this.similarplayers = similarplayers;
	}

	public long getPlayerid() {
		return playerid;
	}

	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public int getDraft_year() {
		return draft_year;
	}

	public void setDraft_year(int draft_year) {
		this.draft_year = draft_year;
	}

	public int getDraft_pick() {
		return draft_pick;
	}

	public void setDraft_pick(int draft_pick) {
		this.draft_pick = draft_pick;
	}

	public String getDrafted_by() {
		return drafted_by;
	}

	public void setDrafted_by(String drafted_by) {
		this.drafted_by = drafted_by;
	}

	public double getPts_pg() {
		return pts_pg;
	}

	public void setPts_pg(double pts_pg) {
		this.pts_pg = pts_pg;
	}

	public double getRebounds_pg() {
		return rebounds_pg;
	}

	public void setRebounds_pg(double rebounds_pg) {
		this.rebounds_pg = rebounds_pg;
	}

	public double getAssists_pg() {
		return assists_pg;
	}

	public void setAssists_pg(double assists_pg) {
		this.assists_pg = assists_pg;
	}

	public double getMins_pg() {
		return mins_pg;
	}

	public void setMins_pg(double mins_pg) {
		this.mins_pg = mins_pg;
	}

	public int getPrediction() {
		return prediction;
	}

	public void setPrediction(int prediction) {
		this.prediction = prediction;
	}

	public List<SimilarPlayer> getSimilarplayers() {
		return similarplayers;
	}

	public void setSimilarplayers(List<SimilarPlayer> similarplayers) {
		this.similarplayers = similarplayers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

