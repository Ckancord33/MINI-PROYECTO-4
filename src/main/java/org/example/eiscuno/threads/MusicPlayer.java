package org.example.eiscuno.threads;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Class MusicPlayer
 * This class represents a music player that can play, pause, stop, and resume audio tracks.
 * It also allows volume adjustment and track changes. It extends Thread to manage audio playback.
 */
public class MusicPlayer extends Thread {
    private Clip clip;
    private boolean isPlaying;
    private int track = 0;
    private FloatControl volumeControl;
    private boolean isPaused;
    private int pausePosition = 0;
    private boolean running = true;
    private static MusicPlayer instance;

    String t1 = "src/main/resources/org/example/eiscuno/sounds/1-03.-Subwoofer-Lullaby.wav";
    String t2 = "src/main/resources/org/example/eiscuno/sounds/1-05.-Living-Mice.wav";
    String t3 = "src/main/resources/org/example/eiscuno/sounds/1-06.-Moog-City.wav";
    String t4 = "src/main/resources/org/example/eiscuno/sounds/1-07.-Haggstrom.wav";
    String t5 = "src/main/resources/org/example/eiscuno/sounds/1-08.-Minecraft.wav";

    public MusicPlayer() {}

    /**
     * Returns the singleton instance of the MusicPlayer.
     *
     * @return MusicPlayer instance
     */
    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    /**
     * Deletes the singleton instance of the MusicPlayer and stops any music playing.
     */
    public static void deleteInstance() {
        if (instance != null) {
            instance.stopMusic();  // Detiene la música antes de eliminar la instancia
            instance = null;
        }
    }

    /**
     * Starts the music player thread and manages music playback lifecycle.
     */
    @Override
    public void run() {
        while (running) {
            synchronized (this) {
                if (isPaused) {
                    try {
                        wait();  // Pausa el hilo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Starts playing music from the specified file.
     *
     * @param filename Path to the music file
     */
    public synchronized void startMusic(String filename) {
        if (!isPlaying) {
            isPlaying = true;
            isPaused = false;
            playTrack(filename, -25.0f);
            if(isInterrupted()) {
                start();  // Iniciar el hilo
            }
        }
    }

    /**
     * Stops the currently playing music and resets the state.
     */
    public synchronized void stopMusic() {
        if (isPlaying) {
            isPlaying = false;
            isPaused = false;
            running = false;
            clip.stop();
        }
    }

    /**
     * Pauses the currently playing music and saves the frame position.
     */
    public synchronized void pauseMusic() {
        if (isPlaying && !isPaused) {
            pausePosition = clip.getFramePosition();
            clip.stop();
            isPaused = true;
        }
    }

    /**
     * Resumes the music from the last paused position.
     */
    public synchronized void resumeMusic() {
        if (isPaused) {
            clip.setFramePosition(pausePosition);
            clip.start();
            isPaused = false;
            notify();  // Reanuda el hilo
        }
    }

    /**
     * Sets the current track number.
     *
     * @param track Track number to be set
     */
    public void setTrack(int track){
        this.track = track;
    }

    /**
     * Returns the current track number.
     *
     * @return Track number
     */
    public int getTrack(){
        return track;
    }

    /**
     * Changes the current track and starts playing the selected track.
     *
     * @param track Track number to change to
     */
    public void changeTrack(int track){
        stopMusic();
        if(track == 0){
            startMusic(t1);
        }else if(track == 1){
            startMusic(t2);
        }else if(track == 2){
            startMusic(t3);
        }else if(track == 3){
            startMusic(t4);
        }else if(track == 4){
            startMusic(t5);
        }
    }

    /**
     * Plays the specified track with the given volume.
     *
     * @param nombreSonido Path to the music file
     * @param volumen Desired volume
     */
    public void playTrack(String nombreSonido, float volumen){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            if (volumeControl  != null && -80.0 < volumen && volumen < 6.0) {
                volumeControl .setValue(volumen);
            }

        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    /**
     * Increases the volume by a predefined step.
     */
    public void increaseVolume() {
        if (volumeControl != null) {
            float currentVolume = volumeControl.getValue();
            float maxVolume = volumeControl.getMaximum();
            if (currentVolume < maxVolume) {
                volumeControl.setValue(currentVolume + 4.0f);
            }
        }
    }

    /**
     * Decreases the volume by a predefined step.
     */
    public void decreaseVolume() {
        if (volumeControl != null) {
            float currentVolume = volumeControl.getValue();
            float minVolume = volumeControl.getMinimum();
            if (currentVolume > minVolume) {
                volumeControl.setValue(currentVolume - 4.0f);
            }
        }
    }

    /**
     * Returns the current volume level.
     *
     * @return Current volume as a float value
     */
    public float getCurrentVolume() {
        if (volumeControl != null) {
            return volumeControl.getValue();
        }
        return 0.0f;  // Si no hay control de volumen, devolver 0
    }

}
