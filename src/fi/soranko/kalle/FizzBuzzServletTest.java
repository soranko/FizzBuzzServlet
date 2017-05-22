package fi.soranko.kalle;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FizzBuzzServletTest {

    @Test
    public void fizzBuzzTest() {
        FizzBuzzServlet fbs = new FizzBuzzServlet();
        String real1 = fbs.fizzBuzz(3);
        String real2 = fbs.fizzBuzz(5);
        String real3 = fbs.fizzBuzz(15);

        String expected1 = "1 2 Fizz";
        String expected2 = "1 2 Fizz 4 Buzz";
        String expected3 = "1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz";

        assertEquals(expected1, real1);
        assertEquals(expected2, real2);
        assertEquals(expected3, real3);
    }
}
