package ch07_dao.kpop;

import java.time.LocalDate;

public class Artist {

	private int aid;
	private String name;
	private LocalDate debut;
	private int hit_song_id;
	public Artist() {
	}
	public Artist(String name, int hit_song_id) {
		this.name = name;
		this.hit_song_id = hit_song_id;
	}
	
	public Artist(String name, LocalDate debut, int hit_song_id) {
		this.name = name;
		this.debut = debut;
		this.hit_song_id = hit_song_id;
	}
	
	public Artist(int aid, String name, LocalDate debut, int hit_song_id) {
		this.aid = aid;
		this.name = name;
		this.debut = debut;
		this.hit_song_id = hit_song_id;
	}
	@Override
	public String toString() {
		return "Artist [aid=" + aid + ", name=" + name + ", debut=" + debut + ", hit_song_id=" + hit_song_id + "]";
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDebut() {
		return debut;
	}
	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}
	public int getHit_song_id() {
		return hit_song_id;
	}
	public void setHit_song_id(int hit_song_id) {
		this.hit_song_id = hit_song_id;
	}
	
	
}
