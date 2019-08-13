package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTests {
    private static final int[] expected = {-1, 2, 4, 5};

    @Test
    public void sortWithDescOrder() {
        int[] arr = {5, 4, 2, -1};
        Bubble.sort(arr);
        Assert.assertArrayEquals(arr, expected);
    }

    @Test
    public void sortWithAscOrder() {
        int[] arr = {-1, 2, 4, 5};
        Bubble.sort(arr);
        Assert.assertArrayEquals(arr, expected);
    }

    @Test
    public void testSortEndIndex() {
        int[] arr = {-1, 2, 5, 4};
        Bubble.sort(arr);
        Assert.assertArrayEquals(arr, expected);
    }

    @Test
    public void testSortStartIndex() {
        int[] arr = {2, -1, 4, 5};
        Bubble.sort(arr);
        Assert.assertArrayEquals(arr, expected);
    }
}
