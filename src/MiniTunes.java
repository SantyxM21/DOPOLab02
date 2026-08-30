import java.util.TreeMap;
/** MiniTunes.java
 * A minitune where will be added playlists with or without and songs, and
 * which allows modificacions
 * @author ESCUELA 2026-02
 */
    
public class MiniTunes{
    
    private TreeMap<String,Playlist> playlists;
    private boolean lastOk;
    
    /**
     * Create a minitune which will save playlists.
     */
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
        if (a == null || playlist == null || !playlists.containsKey(a)) {
            lastOk = false;
            return;
        }
        playlists.put(a, new Playlist(playlist));
        lastOk = true;
    }


    /**
     * Return a playlist's size.
     * @param name  the name of the playlist
     * @return the size of a playlist
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
     * @return string with the playlists names in alphabetical order 
     */
    public String toString(){
        return String.join(",", playlists.keySet());
    }
    
    // Returns the string representation of a playlist.
    /**
     * Return the string representation of a playlist. the names of a list
     * @param name  the name of a playlist
     * @return A string with the name of the songs in a playlist. 
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
        if (a == null) {
            lastOk = false;
            return;
        }
        Playlist bPlaylist = getPlaylist(b);
        if(bPlaylist == null) { 
            lastOk = false; 
            return; 
        }

        switch(op){
            case 'a':
                String[][] bAdd = bPlaylist.add(values).getSongs();
                define(a);
                assign(a, bAdd);
                break;
            case 'd':
                String[][] bDel = bPlaylist.delete(values).getSongs();
                define(a);
                assign(a, bDel);
                break;
            case 's':
                String[][] bSel = bPlaylist.select(values).getSongs();
                define(a);
                assign(a, bSel);
                break;
            default:
                lastOk = false;
        }
    }
      
    
    /**
     * 
     *Assigns the value of a binary operation to a playlist name
     * a = b op c
     *The operator characters are:  'u' union, 'i' intersection, 'd' difference
     *Songs preserve their original order in the resulting playlist.
     *@param a The name of a playlist where operations will be saved
     *@param b The name of a playlist which will be operated with another one
     *@param op 'u' union, 'i' intersection, 'd' difference
     *@param c The name of a playlist which will be operated with another one
     **/
    public void assignBinary(String a, String b, char op, String c){
        if (a == null){
            lastOk = false;
            return;
        }

        Playlist playlistB = getPlaylist(b);
        Playlist playlistC = getPlaylist(c);

        if (playlistB == null) playlistB = new Playlist(new String[0][]);
        if (playlistC == null) playlistC = new Playlist(new String[0][]);

        Playlist playlistA = null;

        switch(op){
            case 'u':
                playlistA = union(a, playlistB, playlistC);
                break;

            case 'i':
                playlistA = intersection(a, playlistB, playlistC);
                break;

            case 'd':
                playlistA = difference(a, playlistB, playlistC);
                break;

            default:
                lastOk = false; 
                return;
        }

        if (playlistA == null){
            lastOk = false;
            return;
        }
        playlists.put(a, playlistA);
        lastOk = true;
    }
    
    /**
     * Joins two playlist without repeating songs.
     * @param a 
     * @param playlistB a playlist
     * @param playlistC a playlist
     * @return a Playlist after join two different playlists
     */
    private Playlist union(String a, Playlist playlistB, Playlist playlistC){
        String[][] songsB = playlistB.getSongs();
        String[][] songsC = playlistC.getSongs();
        
        if (!playlists.containsKey(a)) define(a);
        assign(a, songsB);
        
        boolean isAlready;
        
        for (int i = 0; i < songsC.length; i++){
            isAlready = false;
            for (int j = 0; j < songsB.length; j++){
                if (songsC [i][0].equals(songsB[j][0]) && songsC[i][1].equals(songsB[j][1])){
                    //si ya está, no se adiciona
                    isAlready = true;
                    break;
                }
            }
            if (!isAlready){
                assignUnary(a, a ,'a', songsC[i]);
            }
        }
        return getPlaylist(a);
    }
    
    /**
     * Do the intersection of two playlist adding the songs in common.
     * @param a 
     * @param playlistB a playlist
     * @param playlistC a playlist
     * @return a Playlist after do the intersection two different playlists
     */
    private Playlist intersection (String a, Playlist playlistB, Playlist playlistC){
        String [][] songsB = playlistB.getSongs();
        String [][] songsC = playlistC.getSongs();
        
        if (!playlists.containsKey(a)) define(a);
        assign(a, new String [0][0]);
        
        boolean isInCommon;
        
        for (int i = 0; i < songsB.length; i++){
            isInCommon = false;
            for (int j = 0; j < songsC.length; j++){
                if (songsB[i][0].equals(songsC[j][0]) && songsB[i][1].equals(songsC[j][1])){
                    //si estan en comun, hacen parte de la interseccion.
                    isInCommon = true;
                    break;
                }
            } //si no se encontró ninguna coincidencia, pasa a la siguiente iteracion.
            if (isInCommon){
                assignUnary(a,a,'a', songsB[i]); //si la cancion estaba en comun, la agrega.
            } 
        }
        return getPlaylist(a);
    }
    
    /**
     * Do the difference of two playlists in the order given.
     * @param a 
     * @param playlistB a playlist
     * @param playlistC a playlist
     * @return a Playlist after do the difference of two playlists given
     */
    private Playlist difference(String a, Playlist playlistB, Playlist playlistC){
        String [][] songsB = playlistB.getSongs();
        String [][] songsC = playlistC.getSongs();
        
        if (!playlists.containsKey(a)) define(a);
        assign(a, songsB);
        
        boolean justInB;
        
        for (int i = 0; i < songsC.length; i++){
            justInB =  true;
            for (int j = 0; j < songsB.length; j++){
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
   
    /**
     * If the last operation was successfully completed
     * @return true if the last operation was successfullty completed
     */
    public boolean ok(){
        return lastOk;
    }

    public Playlist getPlaylist(String plName){
        if (plName == null) return null; 
        return playlists.get(plName);
    }
}