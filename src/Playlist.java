//Each song is described by its title, artist, genre, duration, and rating.
//The title and artist are mandatory. The genre, duration, and rating may be unknown.
//The combination (title, artist) must be unique. Two songs cannot have the same title and artist.
//The duration (minutes) must be between 1 and 9.
//The rating must be between * and *****.
import java.util.ArrayList;
import java.util.Arrays;
public class Playlist {
    private String[][] songs;
    
    public Playlist(String [][] songs){
        this.songs = normalizeSongs(songs);
    }

    /**
     * add a song to the playlist. If the song already exists, it won't be added.
     * @param song  the song to add
     */
    public Playlist add(String [] song){
        if(!isValidSong(song)) return null;
        song = normalizeSong(song);
        if(existsSong(song)) return null;

        String[][] newSongs = new String[this.size() + 1][];
        int index = 0;
        for(String[] s : songs){
            newSongs[index] = s; 
            index++;
        }
        newSongs[index] = song;
        this.songs = newSongs;
        return this;
    }
    
    /**
     * delete a song from the playlist if it exists
     * @param song  the song to delete
     */
    public Playlist delete(String [] song){
        if(!isValidSong(song)) return null;
        song = normalizeSong(song);
        if(!existsSong(song)) return null;
        String[][] newSongs = new String[size() - 1][];
        int index = 0;
        for(String[] s : songs){
            if(!s[0].equals(song[0]) || !s[1].equals(song[1])){
                newSongs[index] = s;
                index++;
            }
        }
        this.songs = newSongs;
        return this;
    }
    
    /**
     * select songs from the playlist based on the given values.
     * @param values  the values to match
     * @return  a playlist containing the selected songs
     */
    public Playlist select(String [] values){
        if(!isValidSong(values)) return null;
        values = normalizeSong(values);
        if(values == null) return this;

        ArrayList<String[]> selected = new ArrayList<>();
        for(String[] s : songs){
            boolean match = true;
            for(int i = 0; i < values.length && i < s.length; i++){
                if(values[i] != null && !values[i].equals(s[i])){
                    match = false;
                    break;
                }
            }
            if(match) selected.add(s);
        }
        Playlist selectedPlaylist = new Playlist(selected.toArray(new String[0][]));
        return selectedPlaylist;
    }

    /**
     * Return the size of the playlist.
     */
    public int size(){
        return songs.length;
    }    
    
   
    // Songs are in uppercase with unnecessary spaces removed.
    // Columns are aligned and separated by three spaces.
//TITLE    ARTIST          GENRE   DURATION   RATING
//ONE      U2              ROCK           4   *****
//NUMB     LINKIN PARK     ROCK           3
//ALIVE    PEARL JAM       ROCK           5   ****
//CREEP    RADIOHEAD       ROCK               *****
//DREAMS   FLEETWOOD MAC   .              4   ****
    public String toString() {
      return "";
    }
    
    /**
     * Return true if the given playlist is equal to this.playlist.
     * @param name  the playlist to compare with this playlist
     */
    public boolean equals(Playlist pl){
        boolean result = false;
        if(!Arrays.deepEquals(this.songs, pl.getSongs())) return result;
        return true;
    }
    
    /**
     * Return true if the given object is equal to this.playlist.
     * @param o the object to compare with this playlist
     */
    public boolean equals(Object o){
        // if (this == o) return true;
        // if (!(o instanceof Playlist)) return false;
        return equals((Playlist)o);
    }
    
    /**
     * Return the songs of the playlist.
     * @return  the songs of the playlist
     */
    public String[][] getSongs(){
        return this.songs;
    }
    
    private String [][] normalizeSongs(String[][] songs){
        ArrayList<String[]> normSongs = new ArrayList<>();

        for (String[] s : songs) {
            if(!isValidSong(s)) continue; // Si la canción tiene algun formato invalido, se omite 
            boolean add = true;
            String[] song = normalizeSong(s);

            for(String[] ns : normSongs){
                if(ns[0].equals(s[0]) && ns[1].equals(s[1])) add = false; // Si la canción ya existe en la lista normalizada, se omite
            }
            
            if(add) normSongs.add(song);
        }

        System.out.println(Arrays.deepToString(normSongs.toArray(new String[0][])));
        return  normSongs.toArray(new String[0][]);
    }

    private String[] normalizeSong(String[] song){
        String[] normSong = new String[song.length];
        int index = 0;
        for (String str : song) {
            if(str == null){
                // pass
            }else if(str.contains("*")){
                str = str.replace(" ", "");
            }else {
                String[] listWords = str.trim().toLowerCase().split("\\s+");
                StringBuilder concat = new StringBuilder();

                for (String word : listWords){
                    Character initial = Character.toUpperCase(word.charAt(0));
                    String rest = word.substring(1);
                    concat.append(initial).append(rest).append(" ");
                }
                str = concat.toString().trim();
            }

            normSong[index] = str;
            index++;
        }
        return normSong;
    }

    private boolean isValidSong(String[] song){
        boolean isValid = true;

        if(song[0] == null) isValid = false; // El titulo de la cancion es obligatorio
        else if(song[1] == null) isValid = false; // Nombre del artista obligatorio
        else if(song[3] != null && !song[3].trim().matches("[1-9]")) isValid = false; // La duracion de la cancion debe ser un número entre 1 y 9
        else if(song[4] != null && song[4].replace(" ", "").length() > 5) isValid = false; // Un cancion no puede tener mas de 5 en calificación

        return isValid;
    }

    private boolean existsSong(String[] song){
        for(String[] s : songs){
            if(s[0].equals(song[0]) && s[1].equals(song[1])) return true;
        }
        return false;
    }
}
