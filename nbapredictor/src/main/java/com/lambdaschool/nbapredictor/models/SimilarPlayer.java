package com.lambdaschool.nbapredictor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name = "similiarplayers")
public class SimilarPlayer extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long similiarplayerid;

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

	@ManyToOne
	@JoinColumn(name = "playerid")
	@JsonIgnoreProperties("similiarplayers")
	private Player player;

	public SimilarPlayer() {
	}

	public SimilarPlayer(String imgurl, String name, String position, String height, String weight, String college, int draft_year, int draft_pick, String drafted_by, double pts_pg, double rebounds_pg, double assists_pg, double mins_pg) {
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


	public long getSimiliarplayerid() {
		return similiarplayerid;
	}

	public void setSimiliarplayerid(long similiarplayerid) {
		this.similiarplayerid = similiarplayerid;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "SimilarPlayer{" + "similiarplayerid=" + similiarplayerid + ", imgurl='" + imgurl + '\'' + ", name='" + name + '\'' + ", position='" + position + '\'' + ", height='" + height + '\'' + ", weight='" + weight + '\'' + ", college='" + college + '\'' + ", draft_year=" + draft_year + ", draft_pick=" + draft_pick + ", drafted_by='" + drafted_by + '\'' + ", pts_pg=" + pts_pg + ", rebounds_pg=" + rebounds_pg + ", assists_pg=" + assists_pg + ", mins_pg=" + mins_pg + ", player=" + player + '}';
	}
}
