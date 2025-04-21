package musicplayer;

import java.util.*;

/**
 * The Playlist class stores and manages a list of objects.
 * It supports adding, removing, retrieving, and playing songs in the playlist.
 */
public class Playlist {

  /** List for storing music objects */
  final private List<Music> songs;

  /**
   * Constructs an empty playlist.
   */
  public Playlist() {
    songs = new ArrayList<>(); // Initialize the playlist as empty
  }

  /**
   * Adds a music object to the playlist.
   *
   * @param m the {@link Music} object to add
   */
  public void addMusic(Music m) {
    songs.add(m);
  }

  /**
   * Removes a music object from the playlist by its index.
   *
   * @param index the index of the song to remove
   */
  public void removeMusic(int index) {
    songs.remove(index);
  }

  /**
   * Retrieves a music object from the playlist by its index.
   *
   * @param index the index of the song to retrieve
   * @return the {@link Music} object at the given index
   */
  public Music getMusic(int index) {
    return songs.get(index);
  }

  /**
   * Plays all songs in the playlist, each for a specified duration in seconds.
   *
   * @param seconds the number of seconds to play each song
   */
  public void playAll(int seconds) {
    for (Music m : songs) {
      m.play(seconds); // Call the play method in the Music class
    }
  }
}
