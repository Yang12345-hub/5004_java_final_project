package musicplayer;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the {@link Music} class.
 * This class tests methods related to file name and path retrieval.
 */
public class MusicTest {

  /** Music object used for testing */
  private Music music;

  /**
   * Sets up a music instance before each test.
   * The path used is a dummy file path for testing purposes.
   */
  @Before
  public void setUp() {
    music = new Music("/path/to/test_song.mp3");
  }

  /**
   * Tests that {getFileName} correctly returns only the file name
   * from a full file path.
   */
  @Test
  public void testGetFileName() {
    assertEquals("test_song.mp3", music.getFileName());
  }

  /**
   * Tests that {getFilePath} correctly returns the full file path
   * used when constructing the {@link Music} object.
   */
  @Test
  public void testGetFilePath() {
    assertEquals("/path/to/test_song.mp3", music.getFilePath());
  }
}
