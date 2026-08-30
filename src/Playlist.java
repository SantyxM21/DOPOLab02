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
     * Create a copy of the given playlist.
     * @param other  the playlist to copy
     */
    private Playlist(Playlist other){
        this.songs = other.getSongs(); 
    }

    /**
     * add a song to the playlist. If the song already exists, it won't be added.
     * @param song  the song to add
     */
    public Playlist add(String [] song){
        if(!isValidSong(song)) {
            Playlist pl = new Playlist(this);
            return pl;
        }
        song = normalizeSong(song);
        if(existsSong(song)) {
            Playlist pl = new Playlist(this);
            return pl;
        }

        String[][] newSongs = new String[this.size() + 1][];
        int index = 0;
        for(String[] s : songs){
            newSongs[index] = s; 
            index++;
        }
        newSongs[index] = song;
        Playlist pl = new Playlist(newSongs);
        return pl;
    }
    
    /**
     * delete a song from the playlist if it exists
     * @param song  the song to delete
     */
    public Playlist delete(String [] song){   //revisar si sobran los normalizesong aca porque se supone que ya se agregaron normalizadas
        if(!isValidSong(song)) {
            Playlist pl = new Playlist(this);
            return pl;
        }
        song = normalizeSong(song);
        if(!existsSong(song)) {
            Playlist pl = new Playlist(this);
            return pl;
        }
        String[][] newSongs = new String[size() - 1][];
        int index = 0;
        for(String[] s : songs){
            if(!s[0].equals(song[0]) || !s[1].equals(song[1])){
                newSongs[index] = s;
                index++;
            }
        }
        Playlist pl = new Playlist(newSongs);
        return pl;
    }
    
    /**
     * select songs from the playlist based on the given values.
     * @param values  the values to match
     * @return  a playlist containing the selected songs
     */
    public Playlist select(String [] values){
        if(values == null) {
            Playlist pl = new Playlist(this);
            return pl;
        }
        values = normalizeSong(values);

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
    public String[][] getSongs(){ // crea una copia y la envia
        if (this.songs == null) {
            return null;
        }

        String[][] copia = new String[this.songs.length][];

        for (int i = 0; i < this.songs.length; i++) {
            if (this.songs[i] != null) {
                copia[i] = this.songs[i].clone(); 
            }
        }

        return copia;
    }
    
    private String [][] normalizeSongs(String[][] songs){
        ArrayList<String[]> normSongs = new ArrayList<>();
        if (songs == null) return new String[0][]; 

        for (String[] s : songs) {
            if(!isValidSong(s)) continue; // Si la canción tiene algun formato invalido, se omite 
            boolean add = true;
            String[] song = normalizeSong(s);

            for(String[] ns : normSongs){
                if(ns[0].equals(song[0]) && ns[1].equals(song[1])) add = false; // Si la canción ya existe en la lista normalizada, se omite
            }
            
            if(add) normSongs.add(song);
        }

        return  normSongs.toArray(new String[0][]);
    }

    private String[] normalizeSong(String[] song){
        String[] normSong = new String[song.length];
        int index = 0;
        for (String str : song) {
            if(str == null){
                // pass
            }else if(str.trim().isEmpty()){
                str = ""; 
            }else if(index == 4){ // Solo la calificación se normaliza quitando espacios
                str = str.replaceAll("\\s", "");
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
        if (song == null || song.length != 5) isValid = false;
        else if(song[0] == null) isValid = false; // El titulo de la cancion es obligatorio
        else if(song[1] == null) isValid = false; // Nombre del artista obligatorio
        else if(song[3] != null && !song[3].trim().matches("[1-9]")) isValid = false; // La duracion de la cancion debe ser un número entre 1 y 9
        else if(song[4] != null){
            String stars = song[4].replaceAll("\\s", "");
            if (stars.replace("*", "").length() > 0 || stars.length() < 1 || stars.length() > 5)
                isValid = false; // La calificación debe tener entre 1 y 5 '*' y ningun otro simbolo
        }

        return isValid;
    }

    private boolean existsSong(String[] song){
        for(String[] s : songs){
            if(s[0].equals(song[0]) && s[1].equals(song[1])) return true;
        }
        return false;
    }
}
