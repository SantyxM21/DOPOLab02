import java.util.TreeMap;
import java.util.Set;
import java.util.ArrayList;

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
        playlists.put(name, null);
    }
     
    //Assign a playlist to an existing playlist name
    //a := playlist
    //*****************IMPORTANTE********************
    //pueden haber 2 playlists con exactamente el mismo nombre????
    public void assign(String a, String [] [] playlist){
        Playlist playListToAssign = new Playlist(playlist);
        playlists.put(a, playListToAssign);
    }    


    //Return a playlist's size
    public int size(String a){
        Playlist playList = playlists.get(a);
        if (playList != null ){
            return playList.size();
        }
        return 0;
    }
    
    //Returns the playlist names in alphabetical order. comma-separated
    //DATO INTERESANTE--> en el treemap se guarda orden alfabetico
    public String toString(){
        ArrayList <String> names = new ArrayList <>(playlists.keySet());
        if (names.size() == 0) return "";
        else if (names.size() == 1){
            return names.get(0);
        }
        String namesComma = String.join(", ", names);
        return namesComma;
    }
    
    // Returns the string representation of a playlist.
    public String toString(String name){
        Playlist actualPlaylist = playlists.get(name);
        if (actualPlaylist == null) return "";
        for (int i = 0; i < actualPlaylist.size(); i++ ){
            
        }
        
        
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