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
        this.songs = normalize(songs);
    }
    
    public Playlist add(String [] song){
        
        return null;
    }
    
    public Playlist delete(String [] song){
        
        return null;
    }
    
    public Playlist select(String [] values){
        return null;
    }      

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
    
    public boolean equals(Playlist pl){
        boolean result = false;
        if(!Arrays.deepEquals(this.songs, pl.getSongs())) return result;
        return true;
    }
    
    public boolean equals(Object o){
        // if (this == o) return true;
        // if (!(o instanceof Playlist)) return false;
        return equals((Playlist)o);
    }
    
    public String[][] getSongs(){
        return this.songs;
    }
    
    private String [][] normalize(String[][] songs){
        ArrayList<String[]> normSongs = new ArrayList<>();

        for (String[] s : songs) {
            if(!isValidSong(s)) continue; // Si la canción tiene algun formato invalido, se omite 
            
            String[] song = new String[s.length];
            int index = 0;
            for (String str : s) {
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

                // TODO: Validar que la combinación de Titulo-Nombre sea unico y no haya repetidos.
                song[index] = str;
                index++;
            }
            normSongs.add(song);
        }

        System.out.println(Arrays.deepToString(normSongs.toArray(new String[0][])));
        return  normSongs.toArray(new String[0][]);
    }

    private boolean isValidSong(String[] song){
        boolean isValid = true;

        if(song[0] == null) isValid = false; // El titulo de la cancion es obligatorio
        else if(song[1] == null) isValid = false; // Nombre del artista obligatorio
        else if(song[3] != null && !song[3].trim().matches("[1-9]")) isValid = false; // La duracion de la cancion debe ser un número entre 1 y 9
        else if(song[4] != null && song[4].replace(" ", "").length() > 5) isValid = false; // Un cancion no puede tener mas de 5 en calificación

        return isValid;
    }
}
