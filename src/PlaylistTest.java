import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PlaylistTest{

    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }

    
     @Test
    public void shouldCreateAEmptyPlaylist(){
        String [][] songs = {};
        Playlist pl=new Playlist(songs);
        assertEquals(0, pl.size());     
    }    
   
    @Test
    public void shouldCreateAPlaylist(){
        String [][] songs=
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
        Playlist pl=new Playlist(songs);
        assertEquals(5, pl.size());   
    }    
    
    @Test
    public void shouldNotCreateABadPlaylist(){
        String [][] songs=
            {{"One", "U2", "Rock", "4", "*******"},
             {"Numb", "Linkin Park", "Rock", "Rock", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", null, "Rock", null, "*****"},
             {null, "Fleetwood Mac", null, "4", "****"}};
        Playlist pl=new Playlist(songs);
        assertEquals(1, pl.size());   
    }  
    
    @Test
    public void shouldRecognizeEqualPlaylists(){
       String [][] songs=
            {{"One", "U2", "Rock", "4", "*******"},
             {"Numb", "Linkin Park", "Rock", "Rock", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", null, "Rock", null, "*****"},
             {null, "Fleetwood Mac", null, "4", "****"}}; 
       String [][] sameSongs=
            {{"ONE", "U2", "Rock", "4", "*******"},
             {"   Numb", "Linkin Park   ", "Rock", "Rock", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"},
             {"Creep", null, "ROCK", null, "*****"},
             {null, "Fleetwood Mac", null, "4", "**   **"}};
       assertEquals(new Playlist(songs),new Playlist(sameSongs));
    }

    @Test
    public void shouldDefineAPlaylistName(){
        MiniTunes mt = new MiniTunes();
        mt.define("Vallenatos");

        assertEquals(0, mt.size("Vallenatos"));
    }

    @Test
    public void shouldAssignAPlaylistToAName(){
        MiniTunes mt = new MiniTunes();
        mt.define("Rockcitos");
        String [][] songs=
            {{"Numb", "Linkin Park", "9", "Rock", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},};
        mt.assign("Rockcitos", songs);

        assertEquals(2, mt.size("Rockcitos"));
    }

    @Test
    public void shouldGiveAListOfPlaylistNames(){
        MiniTunes mt = new MiniTunes();
        mt.define("Vallenatos");
        mt.define("Rock");
        mt.define("Salsa");

        assertEquals("Rock,Salsa,Vallenatos", mt.toString());
    }

    @Test
    public void nombre1(){
        String [][] songs = {{"Numb", "Linkin Park", "Rock", "9", null}};
        Playlist pl = new Playlist(songs);
        
        String[] newSong = {"Alive", "Pearl Jam", "Rock", "5", "****"};
        pl.add(newSong);

        assertEquals(2, pl.size());
    }

    @Test
    public void nombre2(){
        String [][] songs=
            {{"ONE", "U2", "Rock", "4", "****"},
             {"   Numb", "Linkin Park   ", "Rock", "7", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"},
             {"Creep", null, "ROCK", null, "*****"},
             {null, "Fleetwood Mac", null, "4", "**   **"}};
            
        String[] songToDelete = {"ALIVE", "Pearl           JAM", "Rock", "5", "****"};

        String [][] expResult=
            {{"ONE", "U2", "Rock", "4", "****"},
             {"   Numb   ", "Linkin     Park   ", "Rock", "7", null}};

        Playlist pl = new Playlist(songs);
        pl.delete(songToDelete);

        
        assertEquals(2, pl.size());
        assertEquals(new Playlist(expResult), pl);
    }













    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
    }
}
