import java.util.ArrayList;
import java.util.TreeMap;

/** MiniTunes.java
 * 
 * @author ESCUELA 2026-02
 */
    
public class MiniTunes{
    
    private TreeMap<String,Playlist> playlists;
    
    
    public MiniTunes(){
        playlists = new TreeMap<>();
    }

    /**
     * Set the name of a new playlist.
     * @param name  the name of the new playlist
     */
    public void define(String name){
        playlists.put(name, null);
    }
     
    //Assign a playlist to an existing playlist name
    //a := playlist
    //*****************IMPORTANTE********************
    //pueden haber 2 playlists con exactamente el mismo nombre???? NO
    /**
     * Assign a playlist to an existing playlist name.
     * @param name  the name of an existing playlist
     * @param playlist  the playlist to assign
     */
    public void assign(String a, String [] [] playlist){
        Playlist playListToAssign = new Playlist(playlist);
        // si "a" no existe no deberia asignar nada
        if (playlists.containsKey(a)) {
            playlists.put(a, playListToAssign);
        }
    }    


    //Return a playlist's size
    /**
     * Return a playlist's size.
     * @param name  the name of the playlist
     */
    public int size(String a){
        Playlist playList = playlists.get(a);
        if (playList != null ){
            return playList.size();
        }
        return 0;
    }
    
    //Returns the playlist names in alphabetical order. comma-separated
    //DATO INTERESANTE--> en el treemap se guarda orden alfabetico
    /**
     * Return the playlist names in alphabetical order as a String, comma-separated.
     */
    public String toString(){
        ArrayList <String> names = new ArrayList <>(playlists.keySet());
        if (names.size() == 0) return "";
        else if (names.size() == 1){
            return names.get(0);
        }
        String namesComma = String.join(",", names);
        return namesComma;
    }
    
    // Returns the string representation of a playlist.
    /**
     * Return the string representation of a playlist.
     * @param name  the name of a playlist
     */
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
        Playlist playlistB = playlists.get(b);
        Playlist playlistC = playlists.get(c);
        Playlist playlistA;
        
        //Revisar si son necesarios los return o si deberia seguir evaluando casos
        if (op == 'u'){ //union
            if (playlistC == null){
                if (playlistB == null){
                    playlistA = null;
                    return;
                }
                playlistA = playlistB;
                return;
            }
            else if (playlistB == null) {
                playlistA = playlistC;
                return;
            }
            playlistA = union(playlistB, playlistC);
        }
    
        else if (op == 'i'){ //Intersection
            if (playlistC == null || playlistB == null){
                playlistA = null;
                return;
            }
            playlistA = intersection(playlistB, playlistC);
        }
        
        else if (op == 'd'){ //diference
            if (playlistB == null){
                playlistA = null;
                return;
            }
            else if (playlistB != null && playlistC == null){
                playlistA = playlistB;
                return;
            }
            playlistA = difference(playlistB, playlistC);
        }
        //**************Hace falta revisar como se guarda, porque este proceso no esta quedando en ningun lado.
    }
  
    private Playlist union(Playlist b, Playlist c){
        String [][] songsB = b.getSongs();
        String [][] songsC = c.getSongs();
        
        Playlist a = new Playlist(songsB);
        
        boolean isAlready;
        for (int i = 0; i < c.size(); i++){
            isAlready = false;
            for (int j = 0; j < b.size(); j++){
                if (songsC [i][0].equals(songsB[j][0]) && songsC[i][1].equals(songsB[j][1])){
                    //si ya está, no se adiciona
                    isAlready = true;
                    break;
                }
            }
            if (!isAlready) a.add(songsC[i]);
        }
        return a;
    }
    
    private Playlist intersection (Playlist b, Playlist c){
        String [][] songsB = b.getSongs();
        String [][] songsC = c.getSongs();
        
        Playlist a = new Playlist(new String [0][0]);
        
        boolean isInCommon;
        for (int i = 0; i < c.size(); i++){
            isInCommon = false;
            for (int j = 0; j < b.size(); j++){
                if (songsC [i][0].equals(songsB[j][0]) && songsC[i][1].equals(songsB[j][1])){
                    //si son iguales, se adiciona.
                    isInCommon = true;
                    break;
                }
            } //si no se encontró ninguna coincidencia, pasa a la siguiente iteracion.
            if (isInCommon) a.add(songsC[i]); //si la cancion estaba en comun, la agrega
        }
        return a;
    }
    
    private Playlist difference(Playlist b, Playlist c){
        String [][] songsB = b.getSongs();
        String [][] songsC = c.getSongs();
        
        Playlist a = new Playlist(songsB);
        boolean justInB;
        
        for (int i = 0; i < c.size(); i++){
            justInB =  true;
            for (int j = 0; j < b.size(); j++){
                if (songsC[i][0].equals(songsB[j][0]) && songsC[i][1].equals(songsB[j][1])){
                    justInB = false; //Si no esta solo en B, debe eliminarse del resultado
                    break;
                }
            }//Si solo esta en b, no se le hace nada
            if (!justInB) a.delete(songsC[i]);
        }
        return a;
    }
   
    //If the last operation was successfully completed
    public boolean ok(){
        return false;
    }
}