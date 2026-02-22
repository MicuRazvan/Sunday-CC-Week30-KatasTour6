package org.example;

import java.util.Arrays;

//https://www.codewars.com/kata/601c18c1d92283000ec86f2b
/*
The status of each element of an array of integers can be determined by its position in the array and the value of the other elements in the array. The status of an element E in an array of size N is determined by adding the position P, 0 <= P < N, of the element in the array to the number of array elements in the array that are less than E.

For example, consider the array containing the integers: 6 9 3 8 2 3 1. The status of the element 8 is 8 because its position is 3 and there are 5 elements in the array that are less than 8.

You will return the elements of the original array from low to high status order. In the event there is a tie for status of two or more elements, you will output them in order of appearance in the array.

EXAMPLE:

status([6, 9, 3, 8, 2, 3, 1]) = [6, 3, 2, 1, 9, 3, 8]
 */
public class Kyu7 {
    public static void test(){
        System.out.println(Arrays.toString(
                status(new int[]{5, -2, -3, 5, -2, 5, 1, -3, -4, 8, 7, 3, 4, 8, 9, 6})
        ));
    }

    public static int[] status(int[] nums) {
        int n = nums.length;

        class Item {
            int value, status, index;
            Item(int v, int s, int i) {
                value = v;
                status = s;
                index = i;
            }
        }

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            int countLess = 0;

            for (int j = 0; j < n; j++) {
                if (nums[j] < nums[i]) {
                    countLess++;
                }
            }

            items[i] = new Item(nums[i], i + countLess, i);
        }

        Arrays.sort(items, (a, b) -> {
            if (a.status != b.status) {
                return Integer.compare(a.status, b.status);
            }
            return Integer.compare(a.index, b.index);
        });

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = items[i].value;
        }

        return result;
    }
}
