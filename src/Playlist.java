//Each song is described by its title, artist, genre, duration, and rating.
//The title and artist are mandatory. The genre, duration, and rating may be unknown.
//The combination (title, artist) must be unique. Two songs cannot have the same title and artist.
//The duration (minutes) must be between 1 and 9.
//The rating must be between * and *****.

public class Playlist {
    
    
    public Playlist(String [][] songs){
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
        return -1;
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
}
