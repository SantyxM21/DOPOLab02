import java.util.TreeMap;

/** MiniTunes.java
 * 
 * @author ESCUELA 2026-02
 */
    
public class MiniTunes{
    
    private TreeMap<String,Playlist> playlists;
    
    public MiniTunes(){
    }

    //Define a new playlist name
    public void define(String name){
    }
     
    //Assign a playlist to an existing playlist name
    //a := playlist
    public void assign(String a, String [] [] playlist){
    }    


    //Return a playlist's size
    public int size(String a){
        return -1;
    }
    
    //Returns the playlist names in alphabetical order. comma-separated
    public String toString(){
        return null;
    }
    
    // Returns the string representation of a playlist.
    public String toString(String name){
        return null;
    }    
    
    //Assigns the value of a unary operation to a playlist name
    // a = b op parameters
    //The operator characters are: 'a' (add) , 'd' (delete),'s'(select)
    //For add and delete, the values correspond to the song data. For select, the parameters define the search pattern.
    public void assignUnary(String a, String b, char op, String [] values){
    }
      
    
    //Assigns the value of a binary operation to a playlist name
    // a = b op c
    //The operator characters are:  'u' union, 'i' intersection, 'd' difference
    //Songs preserve their original order in the resulting playlist.
    public void assignBinary(String a, String b, char op, String c){
    }
  
   
    //If the last operation was successfully completed
    public boolean ok(){
        return false;
    }
}
    



