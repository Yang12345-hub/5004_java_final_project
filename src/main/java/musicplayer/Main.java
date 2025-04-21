package musicplayer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * The Main class is the graphical user interface (GUI) for the Mini Music Player application.
 * It allows users to import music from a folder, manage a playlist, and play or remove songs.
 */
public class Main {

  /** Music library object, responsible for importing music files */
  private Library lib = new Library();

  /** Playlist object, responsible for playback control */
  private Playlist playlist = new Playlist();

  /**
   * The main entry point for the program.
   * Creates and shows the GUI for the music player.
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    new Main().createGUI();
  }

  /**
   * Sets up and displays the graphical user interface.
   * Initializes buttons, song list, input fields, and their corresponding event listeners.
   */
  public void createGUI() {
    // Create the main window
    JFrame frame = new JFrame("Mini Music Player");
    frame.setSize(800, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Song list model and component (used to display music file names)
    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> songList = new JList<>(listModel);
    JScrollPane scroll = new JScrollPane(songList); // Add scroll bar to song list

    // Various buttons and input fields
    JButton importBtn = new JButton("Import Music Folder"); // Button to import music folder
    JButton playAllBtn = new JButton("Play All");           // Button to play all music
    JButton playSelectedBtn = new JButton("Play Selected"); // Button to play selected music
    JLabel lengthInputLabel = new JLabel("Play Length:");   // Label for playback length
    JTextField lengthInputText = new JTextField("10", 2);   // Input field for playback length (default 10 seconds)
    JLabel timeUnit = new JLabel("s");                      // Unit label (seconds)
    JButton removeSelectedBtn = new JButton("Remove Selected"); // Button to remove selected song

    /**
     * Event handler for importing music from a folder.
     * Opens a directory chooser and loads all music files into the library and playlist.
     */
    importBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser(); // Create file chooser
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only allow directory selection
        int res = chooser.showOpenDialog(frame); // Show file chooser dialog
        if (res == JFileChooser.APPROVE_OPTION) { // If user clicked "OK"
          File folder = chooser.getSelectedFile(); // Get the selected folder
          lib.importFolder(folder.getAbsolutePath()); // Import music folder into the library
          listModel.clear(); // Clear the existing list
          playlist = new Playlist(); // Reset the playlist
          for (Music m : lib.getMusicList()) {
            listModel.addElement(m.getFileName()); // Display file name
            playlist.addMusic(m);                  // Add to playlist
          }
        }
      }
    });

    /**
     * Event handler for "Play All" button.
     * Plays all songs in the playlist for the specified number of seconds.
     */
    playAllBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int playLength = Integer.parseInt(lengthInputText.getText()); // Get playback duration
        new Thread(() -> playlist.playAll(playLength)).start(); // Start a new thread to play all music
      }
    });

    /**
     * Event handler for "Play Selected" button.
     * Plays the selected song from the playlist for the specified number of seconds.
     */
    playSelectedBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int selected = songList.getSelectedIndex(); // Get the selected index
        if (selected != -1) {
          int playLength = Integer.parseInt(lengthInputText.getText()); // Playback duration
          new Thread(() -> playlist.getMusic(selected).play(playLength)).start(); // Play the selected song
        }
      }
    });

    /**
     * Event handler for "Remove Selected" button.
     * Removes the selected song from the music library, playlist, and display list.
     */
    removeSelectedBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int selected = songList.getSelectedIndex(); // Get selected index
        if (selected != -1) {
          lib.removeMusic(selected);         // Remove from music library
          playlist.removeMusic(selected);    // Remove from playlist
          listModel.remove(selected);        // Remove from visible list
        }
      }
    });

    // Button panel placed at the bottom of the window
    JPanel buttons = new JPanel();
    buttons.add(importBtn);
    buttons.add(lengthInputLabel);
    buttons.add(lengthInputText);
    buttons.add(timeUnit);
    buttons.add(playAllBtn);
    buttons.add(playSelectedBtn);
    buttons.add(removeSelectedBtn);

    // Place scroll list in the center, buttons at the bottom
    frame.add(scroll, BorderLayout.CENTER);
    frame.add(buttons, BorderLayout.SOUTH);
    frame.setVisible(true); // Show the window
  }
}
