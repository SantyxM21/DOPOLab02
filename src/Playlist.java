//Each song is described by its title, artist, genre, duration, and rating.
//The title and artist are mandatory. The genre, duration, and rating may be unknown.
//The combination (title, artist) must be unique. Two songs cannot have the same title and artist.
//The duration (minutes) must be between 1 and 9.
//The rating must be between * and *****.

public class Playlist {
    private String[][] songs;
    
    public Playlist(String [][] songs){
        this.songs = normalize(songs);
        this.songs = songs;
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
        return false;
    }
    
    public boolean equals(Object o){
        return equals((Playlist)o);
    }
    
    
    private String [][] normalize(String[][] song){
        for(int i = 0; i < song.length; i++){
            for(int j = 0; j < song[0].length; j++){
                if(song[i][j] == null) continue;
                else if(song[i][j].contains("*")){
                    String norm = song[i][j].replace(" ", "");
                }
                
                String[] listWords = song[i][j].trim().toLowerCase().split("\\s+");
                
                StringBuilder concat = new StringBuilder();
                
                for (String word : listWords){
                    Character initial = Character.toUpperCase(word.charAt(0));
                    String rest = word.substring(1);
                    concat.append(initial).append(rest).append(" ");
                }
                
                song[i][j] = concat.toString().trim();
            }
        }
        return song;
    }
}
