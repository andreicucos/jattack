package math;

import org.junit.*;

public class MathTest {

    @BeforeClass
    public static void beforeAll(){
        System.out.println("running before all tests");
    }

    @AfterClass
    public static void afterAll(){
        System.out.println("running after all tests");
    }

    @Before
    public void setup(){
        System.out.println("setup ran before each test");
    }

    @After
    public void reset(){
        System.out.println("reset after each test");
    }

    @Test
    public void add_TwoPlusTwo_ReturnsFour() {

        System.out.println("running test1");
        // Arrange
        final int expected = 4;

        // Act
        final int actual = Math.add(2, 2);

        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void divide_TenDividedByFive_ReturnsTwo() {
        System.out.println("running test2");
        final double expected = 2.0;

        final double actual = Math.divide(10, 5);

        Assert.assertEquals(expected, actual, 0);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void divide_TenDividedByZero_ThrowsIllegalArgumentException() {
        System.out.println("running test3");
        Math.divide(10, 0);
    }

}
