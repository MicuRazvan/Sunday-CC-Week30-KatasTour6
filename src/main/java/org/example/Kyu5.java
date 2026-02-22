package org.example;
import java.util.LinkedHashMap;
//https://www.codewars.com/kata/52bc74d4ac05d0945d00054e
/*
Write a function that takes a string input, and returns the first character that is not repeated anywhere in the string.

For example, if given the input "stress", the function should return 't', since the letter t only occurs once in the string, and occurs first in the string.

As an added challenge, upper- and lowercase characters are considered the same character, but the function should return the correct case for the initial character. For example, the input "sTreSS" should return "T".

If a string contains only repeating characters, return an empty string ("");

Note: despite its name in some languages, your function should handle any Unicode codepoint:

"@#@@*"    --> "#"
"かか何"   --> "何"
"🐐🦊🐐" --> "🦊"
 */
public class Kyu5 {
    public static void test(){
        System.out.println(firstNonRepeatingLetter("moonmoon"));
    }

    public static String firstNonRepeatingLetter(String s) {
        String strToLower = s.toLowerCase();
        LinkedHashMap<String, Integer> kvp = new LinkedHashMap<>();

        strToLower.codePoints().forEach(cp -> {
            String ch = new String(Character.toChars(cp));
            kvp.put(ch, kvp.getOrDefault(ch, 0) + 1);
        });

        var result = "";

        for (var entry : kvp.entrySet()) {
            if(entry.getValue() == 1){

            }
            if (entry.getValue() == 1) {
                if (s.contains(String.valueOf(entry.getKey()))) {
                    result = String.valueOf(entry.getKey());
                } else {
                    result = String.valueOf(entry.getKey()).toUpperCase();
                }

                break;

            }
        }

        return result;
    }
}
