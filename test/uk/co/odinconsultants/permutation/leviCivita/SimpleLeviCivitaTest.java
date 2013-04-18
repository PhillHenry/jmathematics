package uk.co.odinconsultants.permutation.leviCivita;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.co.odinconsultants.permutation.leviCivita.SimpleLeviCivita;


public class SimpleLeviCivitaTest {
    
    private SimpleLeviCivita toTest;
    
    @Before
    public void setUp() {
        toTest = new SimpleLeviCivita();
    }
    @Test
    public void shouldBeEven() {
        assertEquals(1, toTest.apply(1,2,3));
        assertEquals(1, toTest.apply(3,1,2));
        assertEquals(1, toTest.apply(2,3,1));
        assertEquals(1, toTest.apply(1,2,3,4));
        assertEquals(1, toTest.apply(1,3,2,5,4));
    }
    @Test
    public void shouldBeOdd() {
        assertEquals(-1, toTest.apply(4,1,2,3));
        assertEquals(-1, toTest.apply(1,3,2));
        assertEquals(-1, toTest.apply(3,2,1));
        assertEquals(-1, toTest.apply(2,1,3));
        assertEquals(-1, toTest.apply(1,2,4,3));
        assertEquals(-1, toTest.apply(0, 2, 1));
    }
    @Test
    public void shouldBe0() {
        assertEquals(0, toTest.apply(2,1,1));
    }
}
