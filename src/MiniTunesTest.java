import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * The test class MiniTunesTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MiniTunesTest
{
    Playlist pl1;
    Playlist pl2;
    String [][] songs1;
    String [][] songs2;
    
    /**
     * Default constructor for test class MiniTunesTest
     */
    public MiniTunesTest(){
        
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        songs1 = new String[][]
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Numb", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
        pl1 = new Playlist(songs1);
        
        songs2 = new String [][]
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Dreams", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
        pl2 = new Playlist(songs2);     
    }

    
    @Test
    public void shouldDefineTheNameOfAPlaylist(){
        String namePl = "name";
        MiniTunes miniTest = new MiniTunes();
        
        miniTest.define(namePl);
        
        assertEquals("", miniTest.toString(namePl));
    }
    
    @Test 
    public void shouldAssignAListToAName(){
        String[][] listToAssign = 
         {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Numb", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Dreams", "Radiohead", "Rock", null, "*****"}}; 
             
        String plName = "pl1";
        
        MiniTunes miniTest = new MiniTunes();
        
        miniTest.define(plName);
        miniTest.assign(plName, listToAssign);
        
        Playlist test = new Playlist(listToAssign);
        
        assertEquals(test, miniTest.getPlaylist(plName));
    }
    
    @Test
    public void shouldQueryTheNamesOfAList(){ 
        MiniTunes miniTest =  new MiniTunes();
        
        miniTest.define("pl1");
        miniTest.define("pl2");
        miniTest.define("pl3");
        
        String listOflNames = miniTest.toString();
        assertEquals("pl1,pl2,pl3", listOflNames );
    }
    
    @Test
    public void shouldQueryTheNamesOfSongs(){
        MiniTunes miniTest = new MiniTunes();
        songs1 = new String[][]
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Numb", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
        
        miniTest.define("pl1");
        miniTest.assign("pl1", songs1);
        
        String songsList = miniTest.toString("pl1");
        
        assertEquals("One, Numb, Numb, Creep, Dreams", songsList);
    }
    
    @Test
    public void shouldDoTheUnionOfTwoPlaylists(){
        String[][] songsAfterUnion = 
         {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Numb", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Dreams", "Radiohead", "Rock", null, "*****"}};
             
        Playlist unionDone = new Playlist(songsAfterUnion); 
        MiniTunes miniTest = new MiniTunes();
        
        miniTest.define("pl1");
        miniTest.define("pl2");
        miniTest.define("plResult");
        
        miniTest.assign("pl1", songs1); 
        miniTest.assign("pl2", songs2);
        
        miniTest.assignBinary("plResult", "pl1", 'u', "pl2");
        
        Playlist itWillBeTested = miniTest.getPlaylist("plResult");

        assertEquals(unionDone, itWillBeTested);
    }
    
    @Test
    public void shouldDoTheIntersectionOfTwoPlaylist(){
        String [][] songsAfterIntersection = 
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
             
        Playlist intersectionDone = new Playlist(songsAfterIntersection);
        MiniTunes miniTest = new MiniTunes();
        
        miniTest.define("pl1");
        miniTest.define("pl2");
        miniTest.define("plResults");
        
        miniTest.assign("pl1", songs1);
        miniTest.assign("pl2", songs2);
        
        miniTest.assignBinary("plResults", "pl1", 'i', "pl2");
        
        Playlist itWillBeTested = miniTest.getPlaylist("plResults");
        
        assertEquals(intersectionDone, itWillBeTested);
    }
    
    @Test
    public void shouldDoTheDifferenceOfTwoPlaylist(){
        String [][] songsAfterDifference =
            {{"Numb", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"}};
        
        Playlist differenceDone = new Playlist(songsAfterDifference);
        MiniTunes miniTest = new MiniTunes();
        
        miniTest.define("pl1");
        miniTest.define("pl2");
        miniTest.define("plresult");
        
        miniTest.assign("pl1", songs1);
        miniTest.assign("pl2", songs2);
        
        miniTest.assignBinary("plresult", "pl1", 'd', "pl2");
        
        Playlist itWillBeTested = miniTest.getPlaylist("plresult");
        
        assertEquals(differenceDone, itWillBeTested);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        
    }
}