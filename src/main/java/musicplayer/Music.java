package musicplayer;

import javazoom.jl.player.Player;
import java.io.*;

/**
 * The class represents a single music file and provides methods to play it.
 * It uses the JLayer library to handle MP3 playback.
 */
public class Music {

  /** Full path of the music file */
  private String filePath;

  /**
   * Constructs a {@code Music} object with the specified file path.
   *
   * @param filePath the full path of the music file
   */
  public Music(String filePath) {
    this.filePath = filePath; // Constructor to set the file path
  }

  /**
   * Plays this music file for the specified number of seconds.
   * Uses a separate thread to handle playback and interrupts after the given duration.
   *
   * @param seconds the number of seconds to play the song
   */
  public void play(int seconds) {
    try {
      FileInputStream fis = new FileInputStream(filePath); // Read the music file
      Player player = new Player(fis); // Use JLayer to create the player

      System.out.println("Now playing: " + getFileName()); // Print the name of the currently playing file

      // Create a thread to play the music
      Thread t = new Thread(() -> {
        try {
          player.play(); // Play the entire music file
        } catch (Exception e) {
          System.out.println("Failed to play: " + filePath); // Display error if playback fails
        }
      });

      t.start(); // Start the playback thread

      // Main thread waits for the specified time before interrupting playback
      Thread.sleep((long) seconds * 1000);
      player.close(); // Stop playback

      System.out.println("Played: " + getFileName() + " for " + seconds + " seconds"); // Print playback completion message
    } catch (Exception e) {
      System.out.println("Error during playback."); // Exception during playback
    }
  }

  /**
   * Returns the file name (without path) of the music file.
   *
   * @return the name of the file
   */
  public String getFileName() {
    return new File(filePath).getName();
  }

  /**
   * Returns the full path of the music file.
   *
   * @return the full file path
   */
  public String getFilePath() {
    return filePath;
  }
}
