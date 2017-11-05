/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author squid
 */
public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void konstruktoriToimii() {
        assertNotEquals(stats, null);
    }
    
    @Test
    public void etsintaToimii() {
        assertNotEquals(stats.search("Kurri"), null);
    }
    
    @Test
    public void etsintaPalauttaaNullJosEiLoydy() {
        assertEquals(stats.search("Hurri"), null);
    }
    
    @Test
    public void teamLisaaPelaajatJoukkueeseen() {
        assertEquals(stats.team("EDM").size(), 3);
    }
    
    @Test
    public void topScorersLoytaaParhaanPelaajan() {
        assertEquals(stats.topScorers(1).get(0).getName(), "Gretzky");
    }
}