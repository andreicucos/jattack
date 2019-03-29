package math;

import org.junit.Assert;
import org.junit.Test;

public class MathTest {

    @Test
    public void add_TwoPlusTwo_ReturnsFour() {
        // Arrange
        final int expected = 4;

        // Act
        final int actual = Math.add(2, 2);

        // Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void divide_TenDividedByFive_ReturnsTwo() {
        final double expected = 2.0;

        final double actual = Math.divide(10, 5);

        Assert.assertEquals(actual, expected, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divide_TenDividedByZero_ThrowsIllegalArgumentException() {
        Math.divide(10, 0);
    }

}
