
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashSet;
import java.util.Scanner;

// Represents a song
class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    // Two songs are considered duplicates if their titles and artists match
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Song)) {
            return false;
        }

        Song other = (Song) obj;

        return title.equalsIgnoreCase(other.title)
                && artist.equalsIgnoreCase(other.artist);
    }

    @Override
    public int hashCode() {
        return (title.toLowerCase() + "|" + artist.toLowerCase()).hashCode();
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }
}

// Manages the playlist and playback history
public class MediaPlayerQueue {

    private LinkedList<Song> playlist = new LinkedList<>();
    private LinkedList<Song> history = new LinkedList<>();

    // 1. Add a song at the end of the playlist
    public void addSong(String title, String artist) {
        Song song = new Song(title, artist);
        playlist.addLast(song);
        System.out.println("Song added to playlist.");
    }

    // 2. Add a song at the front (priority queueing)
    public void addPrioritySong(String title, String artist) {
        Song song = new Song(title, artist);
        playlist.addFirst(song);
        System.out.println("Priority song added to the front.");
    }

    // 3. Insert a song at a specified position
    public void insertSong(int position, String title, String artist) {
        if (position < 0 || position > playlist.size()) {
            System.out.println("Invalid position.");
            return;
        }

        Song song = new Song(title, artist);
        playlist.add(position, song);

        System.out.println("Song inserted at position " + position + ".");
    }

    // 4. Remove duplicate songs
    public void removeDuplicates() {
        HashSet<Song> uniqueSongs = new HashSet<>();
        LinkedList<Song> uniquePlaylist = new LinkedList<>();

        for (Song song : playlist) {
            if (uniqueSongs.add(song)) {
                uniquePlaylist.add(song);
            }
        }

        int removed = playlist.size() - uniquePlaylist.size();

        playlist = uniquePlaylist;

        System.out.println("Duplicates removed: " + removed);
    }

    // 5. Play the next song and add it to history
    public void playNextSong() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        Song song = playlist.removeFirst();
        history.addLast(song);

        System.out.println("Now playing: " + song);
    }

    // 6. Display playback history (most recent first)
    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No playback history.");
            return;
        }

        System.out.println("\nPlayback History (Most Recent First):");

        ListIterator<Song> iterator =
                history.listIterator(history.size());

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    // 7. Traverse playlist forward and backward
    public void traversePlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        System.out.println("\nPlaylist Forward:");

        ListIterator<Song> iterator = playlist.listIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\nPlaylist Backward:");

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    // 8. Display the playlist
    public void displayPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        System.out.println("\nCurrent Playlist:");

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + playlist.get(i)
            );
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MediaPlayerQueue player = new MediaPlayerQueue();

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== MEDIA PLAYER QUEUE =====");
            System.out.println("1. Add Song");
            System.out.println("2. Add Priority Song");
            System.out.println("3. Insert Song at Position");
            System.out.println("4. Remove Duplicate Songs");
            System.out.println("5. Play Next Song");
            System.out.println("6. Show Playback History");
            System.out.println("7. Traverse Playlist Forward and Backward");
            System.out.println("8. Display Playlist");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter song title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter artist: ");
                    String artist = sc.nextLine();

                    player.addSong(title, artist);
                    break;
                }

                case 2: {
                    System.out.print("Enter song title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter artist: ");
                    String artist = sc.nextLine();

                    player.addPrioritySong(title, artist);
                    break;
                }

                case 3: {
                    System.out.print("Enter position (1-based): ");

                    int position;

                    try {
                        position = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid position.");
                        break;
                    }

                    System.out.print("Enter song title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter artist: ");
                    String artist = sc.nextLine();

                    // Convert 1-based user position to Java's 0-based index
                    player.insertSong(position - 1, title, artist);
                    break;
                }

                case 4:
                    player.removeDuplicates();
                    break;

                case 5:
                    player.playNextSong();
                    break;

                case 6:
                    player.showHistory();
                    break;

                case 7:
                    player.traversePlaylist();
                    break;

                case 8:
                    player.displayPlaylist();
                    break;

                case 0:
                    System.out.println("Exiting media player.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
