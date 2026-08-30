import java.util.TreeMap;

/** MiniTunes.java
 * 
 * @author ESCUELA 2026-02
 */
    
public class MiniTunes{
    
    private TreeMap<String,Playlist> playlists;
    private boolean lastOk;
    
    
    public MiniTunes(){
        playlists = new TreeMap<>();
        lastOk = true;
    }

    /**
     * Set the name of a new playlist.
     * @param name  the name of the new playlist
     */
    public void define(String name){
        if (name == null || playlists.containsKey(name)) { 
            lastOk = false;
            return;
        }
        Playlist emptyPl = new Playlist(new String[0][]);
        playlists.put(name, emptyPl);
        lastOk = true;
    }
     
    //Assign a playlist to an existing playlist name
    //a := playlist
    /**
     * Assign a playlist to an existing playlist name.
     * @param name  the name of an existing playlist
     * @param playlist  the playlist to assign
     */
    public void assign(String a, String [] [] playlist){
        if (a == null || !playlists.containsKey(a)) {
            lastOk = false;
            return;
        }
        playlists.put(a, new Playlist(playlist));
        lastOk = true;
    }


    /**
     * Return a playlist's size.
     * @param name  the name of the playlist
     */
    public int size(String a){
        Playlist playList = getPlaylist(a);
        lastOk = (playList != null);
        if (playList != null ){
            return playList.size();
        }
        return 0;
    }
    
    /**
     * Return the playlist names in alphabetical order as a String, comma-separated.
     */
    public String toString(){
        return String.join(",", playlists.keySet());
    }
    
    // Returns the string representation of a playlist.
    /**
     * Return the string representation of a playlist. the names of a list
     * @param name  the name of a playlist
     */
    public String toString(String name){
        Playlist actualPlaylist = getPlaylist(name);
        lastOk = (actualPlaylist != null); 

        if (actualPlaylist == null) return "";
        
        String [][] actualSongs = actualPlaylist.getSongs(); 
        if ( actualSongs == null || actualSongs.length == 0) return "";
        
        String songsComma = "";
        for (int i =0; i < actualSongs.length; i++){
            songsComma += actualSongs[i][0];
            if(i < actualSongs.length - 1){
                songsComma += ", ";
            }
        }
        return songsComma;
    }    
    
    //Assigns the value of a unary operation to a playlist name
    // a = b op parameters
    //The operator characters are: 'a' (add) , 'd' (delete),'s'(select)
    //For add and delete, the values correspond to the song data. For select, the parameters define the search pattern.
    public void assignUnary(String a, String b, char op, String [] values){
        Playlist bPlaylist = getPlaylist(b);
        if(bPlaylist == null) { 
            lastOk = false; 
            return; 
        }

        switch(op){
            case 'a':
                String[][] bAdd = bPlaylist.add(values).getSongs();
                assign(a, bAdd);
                break;
            case 'd':
                String[][] bDel = bPlaylist.delete(values).getSongs();
                assign(a, bDel);
                break;
            case 's':
                String[][] bSel = bPlaylist.select(values).getSongs();
                assign(a, bSel);
                break;
            default:
                lastOk = false; 
        }
    }
      
    
    //Assigns the value of a binary operation to a playlist name
    // a = b op c
    //The operator characters are:  'u' union, 'i' intersection, 'd' difference
    //Songs preserve their original order in the resulting playlist.
    public void assignBinary(String a, String b, char op, String c){
        Playlist playlistB = getPlaylist(b);
        Playlist playlistC = getPlaylist(c);
        
        Playlist playlistA = null;
        
        switch(op){
            case 'u':
                if (playlistC == null){
                if (playlistB == null){
                    playlistA = null;
                    lastOk = false;
                    return;
                }
                playlistA = playlistB;
                lastOk = false;
                return;
                }
                else if (playlistB == null) {
                    playlistA = playlistC;
                    lastOk = false;
                    return;
                }
                playlistA = union(a, b, c);
                break;

            case 'i':
                if (playlistC == null || playlistB == null){
                playlistA = null;
                lastOk = false;
                return;
                }
                playlistA = intersection(a, b, c);
                break;

            case 'd':
                if (playlistB == null){
                playlistA = null;
                lastOk = false;
                return;
                }
                else if (playlistB != null && playlistC == null){
                    playlistA = playlistB;
                    lastOk = false;
                    return;
                }
                playlistA = difference(a, b, c);
                break;

            default:
                lastOk = false; 
                return;
        }
        lastOk = (playlistA != null);
        if (playlistA != null){
            playlists.put(a, playlistA);
        }
    }
  
    private Playlist union(String a, String b, String c){
        define(a);
        
        Playlist playlistB = getPlaylist(b);
        Playlist playlistC = getPlaylist(c);
        
        assign(a, playlistB.getSongs());
        
        String[][] songsB = playlistB.getSongs();
        String[][] songsC = playlistC.getSongs();
        
        boolean isAlready;
        
        for (int i = 0; i < playlistC.size(); i++){
            isAlready = false;
            for (int j = 0; j < playlistB.size(); j++){
                if (songsC [i][0].equals(songsB[j][0]) && songsC[i][1].equals(songsB[j][1])){
                    //si ya está, no se adiciona
                    isAlready = true;
                    break;
                }
            }
            if (!isAlready){
                assignUnary(a, a ,'a', songsC[i]);
            };
        }
        return getPlaylist(a);
    }
    
    private Playlist intersection (String a, String b, String c){
        define(a);
        
        Playlist playlistB = getPlaylist(b);
        Playlist playlistC = getPlaylist(c);
        
        assign(a, new String [0][0]);
        
        String [][] songsB = playlistB.getSongs();
        String [][] songsC = playlistC.getSongs();
        
        boolean isInCommon;
        
        for (int i = 0; i < playlistC.size(); i++){
            isInCommon = false;
            for (int j = 0; j < playlistB.size(); j++){
                if (songsC [i][0].equals(songsB[j][0]) && songsC[i][1].equals(songsB[j][1])){
                    //si estan en comun, hacen parte de la interseccion.
                    isInCommon = true;
                    break;
                }
            } //si no se encontró ninguna coincidencia, pasa a la siguiente iteracion.
            if (isInCommon){
                assignUnary(a,a,'a', songsC[i]); //si la cancion estaba en comun, la agrega.
            } 
        }
        return getPlaylist(a);
    }
    
    private Playlist difference(String a, String b, String c){
        define(a);
        
        Playlist playlistB = getPlaylist(b);
        Playlist playlistC = getPlaylist(c);
        
        String [][] songsB = playlistB.getSongs();
        String [][] songsC = playlistC.getSongs();
        
        assign(a, playlistB.getSongs());
        
        boolean justInB;
        
        for (int i = 0; i < playlistC.size(); i++){
            justInB =  true;
            for (int j = 0; j < playlistB.size(); j++){
                if (songsC[i][0].equals(songsB[j][0]) && songsC[i][1].equals(songsB[j][1])){
                    justInB = false; 
                    break;//Si solo esta en b, no se le hace nada
                }
            }
            //Si no esta solo en B, debe eliminarse del resultado
            if (!justInB){
                assignUnary(a, a, 'd', songsC[i]);
            }
        }
        return getPlaylist(a);
    }
   
    //If the last operation was successfully completed
    public boolean ok(){
        return lastOk;
    }

    public Playlist getPlaylist(String plName){
        if (plName == null) return null; 
        return playlists.get(plName);
    }
}