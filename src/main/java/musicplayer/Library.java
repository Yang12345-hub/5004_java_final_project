package musicplayer;

import java.io.File;
import java.util.*;

/**
 * The Library class handles importing and managing music files.
 * It supports loading .mp3 files from a folder and storing them as a list of {@link Music} objects.
 */
public class Library {

  /** Stores the list of music files */
  final private List<Music> musicList;

  /**
   * Constructs an empty music library.
   */
  public Library() {
    musicList = new ArrayList<>(); // Initialize as an empty list
  }

  /**
   * Imports all .mp3 music files from the specified folder path.
   *
   * @param folderPath the path to the folder containing music files
   */
  public void importFolder(String folderPath) {
    File folder = new File(folderPath);     // Create a folder object from the path
    File[] files = folder.listFiles();      // Get all files in the folder
    if (files != null) {                    // Check if the folder is not empty
      for (File f : files) {
        // Check if the file is a .mp3 file (case-insensitive)
        if (f.getName().toLowerCase().endsWith(".mp3")) {
          // Create a Music object and add it to the list
          musicList.add(new Music(f.getAbsolutePath()));
        }
      }
    }
  }

  /**
   * Removes the music at the specified index from the library.
   *
   * @param index the index of the music to remove
   */
  public void removeMusic(int index) {
    musicList.remove(index);
  }

  /**
   * Returns the list of all music files in the library.
   *
   * @return the list of {@link Music} objects
   */
  public List<Music> getMusicList() {
    return musicList;
  }
}
