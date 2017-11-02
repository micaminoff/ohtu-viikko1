package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kayttokelvotonVarasto() {
        Varasto temp = new Varasto(-10);
        assertEquals(0, temp.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus2() {
        Varasto temp = new Varasto(11, 5);
        assertEquals(temp.getTilavuus(), 11, vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonPerusTilavuusOnNolla() {
        Varasto temp = new Varasto(-11, 5);
        assertEquals(temp.getTilavuus(), 0, vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiLisaaPuuttuvaaTavaraa() {
        Varasto temp = new Varasto(10, -5);
        assertEquals(temp.getSaldo(), 0, vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLisaaTavaraa() {
        Varasto temp = new Varasto(10, 5);
        assertEquals(temp.getSaldo(), 5, vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiLisaaLiikaaTavaraa() {
        Varasto temp = new Varasto(10, 15);
        assertEquals(temp.getSaldo(), 10, vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiLisaaNegatiivisiaArvoja() {
        varasto.lisaaVarastoon(-4);
        assertEquals(varasto.paljonkoMahtuu(), 10, vertailuTarkkuus);
    }
    
    @Test
    public void lisaysEiLisaaLiikaa() {
        varasto.lisaaVarastoon(20);
        assertEquals(varasto.getSaldo(), 10, vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otamminenEiPoistaNegatiivisaMaaria() {
        varasto.otaVarastosta(-2);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenEiPoistaEnempaaKuinOn() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(3);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        assertEquals(varasto.toString(), "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu());
    }

}