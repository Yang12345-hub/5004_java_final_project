package musicplayer;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

/**
 * Unit tests for the Library class.
 * This class verifies the behavior of music file importing and removal functions.
 */
public class LibraryTest {

  /** Instance of Library used for each test */
  private Library library;

  /**
   * Sets up a fresh {@link Library} instance before each test runs.
   */
  @Before
  public void setUp() {
    library = new Library();
  }

  /**
   * Tests that the {@code importFolder} method only imports files with a .mp3 extension
   * and ignores non-mp3 files (e.g., .txt).
   *
   * Creates a fake folder with one .txt file and one .mp3 file, then checks that only the
   * .mp3 file is added to the library.
   */
  @Test
  public void testImportFolderIgnoresNonMp3Files() {
    String folderPath = "src/test/resources/fakefolder"; // contains .txt and .mp3
    new File(folderPath).mkdirs();
    try {
      new File(folderPath + "/test1.txt").createNewFile();
      new File(folderPath + "/song1.mp3").createNewFile();
    } catch (Exception e) {
      fail("Could not set up test folder.");
    }

    library.importFolder(folderPath);
    List<Music> musicList = library.getMusicList();
    assertEquals(1, musicList.size());
    assertTrue(musicList.get(0).getFilePath().endsWith("song1.mp3"));

    // cleanup
    new File(folderPath + "/test1.txt").delete();
    new File(folderPath + "/song1.mp3").delete();
    new File(folderPath).delete();
  }

  /**
   * Tests the {@code removeMusic} method to ensure that removing a song
   * from the library by index properly updates the music list.
   */
  @Test
  public void testRemoveMusic() {
    Music mockMusic = new Music("fake/path.mp3");
    library.getMusicList().add(mockMusic);
    assertEquals(1, library.getMusicList().size());

    library.removeMusic(0);
    assertEquals(0, library.getMusicList().size());
  }
}
