package org.example;

//https://www.codewars.com/kata/5b4e779c578c6a898e0005c5
/*
Given a number n, draw stairs using the letter "I", n tall and n wide, with the tallest in the top left.

For example n = 3 result in:

"I\n I\n  I"
or printed:

I
 I
  I
Another example, a 7-step stairs should be drawn like this:

I
 I
  I
   I
    I
     I
      I
 */
public class Kyu8 {
    public static void test(){
        System.out.println(draw(3));
    }

    public static String draw(int n) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                result.append(" ");
            }
            result.append("I");

            if (i < n - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
