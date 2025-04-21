package musicplayer;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

/**
 * Unit tests for the Playlist class.
 * Verifies the behavior of adding, removing, retrieving, and playing music in the playlist.
 */
public class PlaylistTest {

  /** Playlist instance used for each test */
  private Playlist playlist;

  /**
   * Initializes a fresh Playlist before each test case.
   */
  @Before
  public void setUp() {
    playlist = new Playlist();
  }

  /**
   * Tests that music added to the playlist can be retrieved correctly by index.
   */
  @Test
  public void testAddAndGetMusic() {
    Music mockMusic = mock(Music.class);
    playlist.addMusic(mockMusic);

    assertEquals(mockMusic, playlist.getMusic(0));
  }

  /**
   * Tests that removing a song from the playlist updates the list properly.
   * The second song should shift to index 0 after the first is removed.
   */
  @Test
  public void testRemoveMusic() {
    Music mockMusic1 = mock(Music.class);
    Music mockMusic2 = mock(Music.class);
    playlist.addMusic(mockMusic1);
    playlist.addMusic(mockMusic2);

    playlist.removeMusic(0);
    assertEquals(mockMusic2, playlist.getMusic(0));
  }

  /**
   * Tests that the playAll method calls the {@code play} method
   * on each music object in the playlist with the correct duration.
   */
  @Test
  public void testPlayAllInvokesPlay() {
    Music mockMusic1 = mock(Music.class);
    Music mockMusic2 = mock(Music.class);

    playlist.addMusic(mockMusic1);
    playlist.addMusic(mockMusic2);

    playlist.playAll(2);

    // Give time for the background thread (not ideal, but acceptable for demo)
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {}

    verify(mockMusic1).play(2);
    verify(mockMusic2).play(2);
  }
}
