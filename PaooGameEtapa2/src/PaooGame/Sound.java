package PaooGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/background_music.wav");
        soundURL[1] = getClass().getResource("/sound/walk_sound.wav");
        soundURL[2] = getClass().getResource("/sound/gun_sound.wav");
        soundURL[3] = getClass().getResource("/sound/coin_sound.wav");

    }
    public void play(){
        clip.start();
    }
    public void setFile(int soundNumber){
        try {
            AudioInputStream input = AudioSystem.getAudioInputStream(soundURL[soundNumber]);
            clip = AudioSystem.getClip();
            clip.open(input);

        } catch (Exception e){

        }
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

}
