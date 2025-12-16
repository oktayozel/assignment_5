package src.Utils.Sound;

import javax.sound.sampled.*;
import java.io.File;


// manages sound and audio playback using singleton pattern

public class SoundManager {
    private static SoundManager instance;
    private Clip clip;
    private static final String AUDIO_FILE = "src/Assets/audio.wav";

    private SoundManager() {
    }

    // singleton pattern
    public static synchronized SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void play() {
        try {
            if (clip != null && clip.isRunning()) {
                return;
            }

            File audioFile = new File(AUDIO_FILE);
            if (!audioFile.exists()) {
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            // System.out.println("Error playing audio.");
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }
    }
}
