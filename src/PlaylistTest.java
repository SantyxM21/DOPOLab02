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
        String [][] finalSongs=
            {{"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},};
        Playlist finalPl = new Playlist(finalSongs);


        MiniTunes mt = new MiniTunes();
        mt.define("Rockcitos");
        String [][] songs=
            {{"Numb", "Linkin    Park", "9", "Rock", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},};
        mt.assign("Rockcitos", songs);

        assertEquals(2, mt.size("Rockcitos"));
        assertEquals(new Playlist(finalSongs), mt.getPlaylist("Rockcitos"));
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
        pl = pl.add(newSong);

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
        pl = pl.delete(songToDelete);

        
        assertEquals(2, pl.size());
        assertEquals(new Playlist(expResult), pl);
    }

    @Test
    public void nombre3(){
        String [][] songs=
            {{"ONE", "U2", "Rock", "4", "****"},
             {"   Numb", "Linkin Park   ", "Rock", "7", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"}};

        String[][] expStrings =
            {{"ONE", "U2", "Rock", "4", "****"},
             {"   Numb", "Linkin Park   ", "Rock", "7", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"}};

        String[] newSong = new String[]{"Creep", "Radiohead", "Rock", null, "*****"};

        MiniTunes mt = new MiniTunes();
        mt.define("Favs");
        mt.assign("Favs", songs);

        mt.define("Nuevas favoritas");

        mt.assignUnary("Nuevas favoritas", "Favs", 'a', newSong);

        assertEquals(new Playlist(songs), mt.getPlaylist("Favs"));
        assertEquals(new Playlist(expStrings), mt.getPlaylist("Nuevas favoritas"));
    }

    @Test
    public void nombre4(){
        String [][] songs=
            {{"ONE", "U2", "Rock", "4", "****"},
             {"   Numb", "Linkin Park   ", "Rock", "7", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Pop", null, "*****"}};

        String[][] expStrings =
            {{"ONE", "U2", "Rock", "4", "****"},
             {"   Numb", "Linkin Park   ", "Rock", "7", null},
             {"Creep", "Radiohead", "Pop", null, "*****"}};

        String[] songToDelete = new String[]{"ALIVE", "Pearl     JAM", "Rock", "5", "****"};

        MiniTunes mt = new MiniTunes();
        mt.define("Favs");
        mt.assign("Favs", songs);

        mt.define("Sin Alive");

        mt.assignUnary("Sin Alive", "Favs", 'd', songToDelete);

        assertEquals(new Playlist(songs), mt.getPlaylist("Favs"));
        assertEquals(3, mt.size("Sin Alive"));
        assertEquals(new Playlist(expStrings), mt.getPlaylist("Sin Alive"));
    }

    @Test
    public void nombre5(){
        String [][] songs=
            {{"ONE", "U2", "Rock", "4", "****"},
             {"   Numb", "Linkin Park   ", "Rock", "7", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Pop", null, "*****"}};

        String[][] expStrings =
            {{"ONE", "U2", "Rock", "4", "****"},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"}};

        String[] pattern = new String[]{null, null, "Rock", null, "* *  **"};

        MiniTunes mt = new MiniTunes();
        mt.define("Favs");
        mt.assign("Favs", songs);

        mt.define("Rock de cuatro estrellas");

        mt.assignUnary("Rock de cuatro estrellas", "Favs", 's', pattern);

        assertEquals(new Playlist(songs), mt.getPlaylist("Favs"));
        assertEquals(2, mt.size("Rock de cuatro estrellas"));
        assertEquals(new Playlist(expStrings), mt.getPlaylist("Rock de cuatro estrellas"));
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
