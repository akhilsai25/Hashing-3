package FavoriteGames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// This solution first creates a song to genre map to get easy lookup
// We then traverse through the user map and for each user create a map of max interested genres
// During the traversal if a max crosses the max so far we add those new values
public class FavoriteGame {

    static Map<String, List<String>> userSongs = new HashMap<>();
    static Map<String, List<String>> songGenres = new HashMap<>();

    static Map<String, String> songToGenre = new HashMap<>();
    static Map<String, List<String>> userToGenre = new HashMap<>();

    private static void generateSongToGenreMap() {

        for(Map.Entry<String, List<String>> entry: songGenres.entrySet()) {
            String genre = entry.getKey();
            List<String> songs = entry.getValue();

            for(String song:songs) {
                songToGenre.put(song, genre);
            }
        }
    }

    private static void getFavoriteGenreByUser() {

        for(Map.Entry<String, List<String>> entry: userSongs.entrySet()) {
            String user = entry.getKey();
            List<String> songs = entry.getValue();

            Map<String, Integer> genreCount = new HashMap();
            List<String> genres = new ArrayList<>();
            int max = 0;
            for(String song:songs) {
                String genre = songToGenre.get(song);
                genreCount.put(genre, genreCount.getOrDefault(genre, 0)+1);
                if(genreCount.get(genre)>max) {
                    max=genreCount.get(genre);
                    genres.clear();
                    genres.add(genre);
                } else if(genreCount.get(genre)==max) {
                    genres.add(genre);
                }
            }

            userToGenre.put(user, genres);
            System.out.println("user "+user+" list: "+genres.size());
        }
    }

    public static void main(String[] args) {
        userSongs.put("David", List.of("song1", "song2", "song3", "song4", "song8"));
        userSongs.put("Emma", List.of("song5", "song6", "song7"));

        songGenres.put("Rock", List.of("song1", "song3"));
        songGenres.put("Dubstep", List.of("song7"));
        songGenres.put("Techno", List.of("song2", "song4"));
        songGenres.put("Pop", List.of("song5", "song6"));
        songGenres.put("Jazz", List.of("song8", "song9"));

        generateSongToGenreMap();
        getFavoriteGenreByUser();
    }
}
