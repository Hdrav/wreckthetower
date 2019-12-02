package controller.audio;

import java.net.URISyntaxException;
import javafx.scene.media.AudioClip;

public class AudioManager {

	private AudioClip music;

	/*
	 * constructor
	 * */
	public AudioManager() {
		
		try {
			this.music= new AudioClip(getClass().getResource("/sounds/soundtrack/glorMorning.wav").toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		
	}

	/**
	 *@return the music 
	 * */
	public AudioClip getMusic() {
		return music;
	}

	/**
	 * @param the music to be set
	 * */
	public void setMusic(AudioClip music) {
		this.music = music;
	}
}
