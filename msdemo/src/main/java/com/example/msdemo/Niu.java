package com.example.msdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Niu {

    public static void main(String[] args) {
        System.out.println(sort("4Cb365AD"));
    }

    public static String sort(String text) {
        // write code here
        if (text == null || "".equals(text)) {
            return "";
        }

        List<Character> charList = new ArrayList<>();
        List<Character> numList = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                charList.add(c);
            } else if (c >= '1' && c <= '9') {
                numList.add(c);
            }
        }

        Collections.sort(charList, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        });

        Collections.sort(numList, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charList.size(); i++) {
            sb.append(charList.get(i));
        }
        for (int i = 0; i < numList.size(); i++) {
            sb.append(numList.get(i));
        }
        return sb.toString();
    }
}
