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
    public void shouldAddASongToAPlaylist(){
        String [][] songs = {{"Numb", "Linkin Park", "Rock", "9", null}};
        Playlist pl = new Playlist(songs);
        
        String[] newSong = {"Alive", "Pearl Jam", "Rock", "5", "****"};
        pl = pl.add(newSong);

        assertEquals(2, pl.size());
    }

    @Test
    public void shouldDeleteASongFromAPlaylist(){
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
    public void shouldAssignTheAdditionOfASongToAnotherName(){
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
    public void shouldAssignTheDeletionOfASongToAnotherName(){
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
    public void shouldAssignTheSelectionOfSongsToAnotherName(){
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
    
    @Test 
    public void shouldAddASongWithSpacesInItsRating(){
        String[][] empty = {};
        Playlist play = new Playlist(empty);
        String[] song = {"Alive", "PEARL   JAM", "Rock", "5", "** **"};

        play = play.add(song);
        assertEquals(1, play.size());
    }

    @Test
    public void shouldNotAddInvalidSongs(){
        String[][] empty = {};
        Playlist play = new Playlist(empty);
        String[] songNoTitle = {null, "PEARL   JAM", "Rock", "5", "****"};
        play = play.add(songNoTitle);

        String[] songNoArtist = {"Alive", null, "Rock", "5", "****"};
        play = play.add(songNoArtist);

        String[] songIncorrectMin = {"Alive", "PEARL   JAM", "Rock", "5.5", "**"};
        play = play.add(songIncorrectMin);

        String[] songWrongStarts = {"Alive", "PEARL   JAM", "Rock", "2", "5"};
        play = play.add(songWrongStarts);

        assertEquals(0, play.size());
    }

    @Test 
    public void shouldNotAddARepeatedSong(){
        String[][] songs = {{"Alive", "PEARL   JAM", "Rock", "5", "** **"}};
        Playlist play = new Playlist(songs);

        String[] similarSong = {"ALIVE", "PeaRL JaM", "ROCK", null, "*"};

        play = play.add(similarSong);

        assertEquals(1, play.size());
        assertEquals(new Playlist(songs), play);
    }

    @Test
    public void shouldNotDeleteASongThatIsNotInThePlaylist(){
        String[][] songs = {{"Alive", "Pearl Jam", "Rock", "5", "****"}};
        Playlist play = new Playlist(songs);

        String[] absentSong = {"Creep", "Radiohead", "Rock", null, "*****"};
        play = play.delete(absentSong);

        assertEquals(1, play.size());
        assertEquals(new Playlist(songs), play);
    }

    @Test
    public void shouldBeOkAfterASuccessfulOperation(){
        String [][] songs = {{"Alive", "Pearl Jam", "Rock", "5", "****"}};
        String[] newSong = {"Creep", "Radiohead", "Rock", null, "*****"};

        MiniTunes mt = new MiniTunes();
        mt.define("Favs");
        assertTrue(mt.ok());

        mt.assign("Favs", songs);
        assertTrue(mt.ok());

        mt.size("Favs");
        assertTrue(mt.ok());

        mt.assignUnary("Nuevas", "Favs", 'a', newSong);
        assertTrue(mt.ok());

        mt.assignBinary("Union", "Favs", 'u', "Nuevas");
        assertTrue(mt.ok());
    }

    @Test
    public void shouldNotBeOkAfterAnUnsuccessfulOperation(){
        MiniTunes mt = new MiniTunes();
        mt.define("Favs");

        mt.define("Favs");                                 
        assertFalse(mt.ok());

        mt.assign("Desconocida", new String[0][]);         
        assertFalse(mt.ok());

        mt.size("Desconocida");                           
        assertFalse(mt.ok());

        mt.assignUnary("Nuevas", "Desconocida", 'a', null); 
        assertFalse(mt.ok());

        mt.assignBinary("Nuevas", "Favs", 'x', "Favs");   
        assertFalse(mt.ok());
    }

    @Test
    public void shouldPass(){
        MiniTunes mt = new MiniTunes();
        mt.define("Rock");
        mt.assign("Rock", new String[][]{{"Alive", "Pearl Jam", "Rock", "5", "****"}});


        assertEquals(1, mt.size("Rock"));
    }

    @Test
    public void shouldFail(){
        MiniTunes mt = new MiniTunes();
        mt.define("Rock");
        mt.define("Rock");   

        assertTrue("Se esperaba que ok() fuera true", mt.ok());
    }

    @Test
    public void shouldErr(){
        MiniTunes mt = new MiniTunes();
        mt.define("Rock");

        Playlist pl = mt.getPlaylist("Salsa");
        assertEquals(0, pl.size());
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
