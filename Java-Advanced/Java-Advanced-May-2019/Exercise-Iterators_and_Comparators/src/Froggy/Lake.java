package Froggy;

import java.util.Iterator;

public class Lake implements Iterable<Integer> {
    private int[] nums;

    public Lake(int[] nums) {
        this.nums = nums;
    }

    private class Frog implements Iterator<Integer> {
        private int index = 0;
        private boolean isOdd = false;

        @Override
        public boolean hasNext() {
            if (this.index > nums.length - 1 && !this.isOdd) {
                this.isOdd = true;
                this.index = 1;
            }

            return this.index < nums.length;
        }

        @Override
        public Integer next() {
            int value = nums[this.index];
            this.index += 2;
            return value;
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Frog();
    }
}
