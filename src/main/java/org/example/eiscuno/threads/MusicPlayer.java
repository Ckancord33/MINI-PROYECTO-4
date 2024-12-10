package org.example.eiscuno.threads;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

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

    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }
    public static void deleteInstance() {
        if (instance != null) {
            instance.stopMusic();  // Detiene la música antes de eliminar la instancia
            instance = null;
        }
    }
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

    public synchronized void startMusic(String filename) {
        if (!isPlaying) {
            isPlaying = true;
            isPaused = false;
            playTrack(filename, 0.0f);
            if(isInterrupted()) {
                start();  // Iniciar el hilo
            }
        }
    }

    public synchronized void stopMusic() {
        if (isPlaying) {
            isPlaying = false;
            isPaused = false;
            running = false;
            clip.stop();
        }
    }

    public synchronized void pauseMusic() {
        if (isPlaying && !isPaused) {
            pausePosition = clip.getFramePosition();
            clip.stop();
            isPaused = true;
        }
    }

    public synchronized void resumeMusic() {
        if (isPaused) {
            clip.setFramePosition(pausePosition);
            clip.start();
            isPaused = false;
            notify();  // Reanuda el hilo
        }
    }
    public void setTrack(int track){
        this.track = track;
    }
    public int getTrack(){
        return track;
    }
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

    // Método para aumentar el volumen
    public void increaseVolume() {
        if (volumeControl != null) {
            float currentVolume = volumeControl.getValue();
            float maxVolume = volumeControl.getMaximum();
            if (currentVolume < maxVolume) {
                volumeControl.setValue(currentVolume + 2.0f);
            }
        }
    }
    // Método para disminuir el volumen
    public void decreaseVolume() {
        if (volumeControl != null) {
            float currentVolume = volumeControl.getValue();
            float minVolume = volumeControl.getMinimum();
            if (currentVolume > minVolume) {
                volumeControl.setValue(currentVolume - 2.0f);
            }
        }
    }
    // Método para obtener el volumen actual
    public float getCurrentVolume() {
        if (volumeControl != null) {
            return volumeControl.getValue();
        }
        return 0.0f;  // Si no hay control de volumen, devolver 0
    }




}
