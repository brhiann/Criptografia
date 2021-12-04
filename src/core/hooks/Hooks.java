package core.hooks;

import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Hooks {

    @Description("""
            T D L R
            n n n n
                        
            Where:
            T : Top
            D : Down
            L : Left
            R : Right
            """)
    public static int[] rotationFactor(String target, String key) {
        var factor = new int[4];
        for (int i = 0; i < factor.length; i++) {
            factor[i] = abstractToken(target, key)[i];
        }
        return factor;
    }

    public static Integer[] abstractToken(String target, String key) {

        var letters = target.toCharArray();
        var lettersKey = key.toCharArray();
        List<Integer> preCalc = new ArrayList<>();

        for (int i = 0; i < letters.length; i++) {
            var restring = (i % 2 == 0) ? Math.sin((byte) lettersKey[i]) : Math.cos((byte) lettersKey[i]);
            var duck = ((byte) letters[i] - new Function.LogarithmicFunction(Math.round(restring), lettersKey[i])
                    .eval((byte) lettersKey[i] / letters.length));
            preCalc.add((int) (duck - ((byte) (letters[i]) * 0.5)));
        }

        return Arrays.stream((preCalc.stream().map(String::valueOf).reduce("", (a, b) -> a + b).split(""))).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
    }

    public static String[] combinationToken(
            char target,
            String key,
            String[][][] box,
            int[] rotationFactor) {
        var coordinates = new String[4];

        final var top = rotationFactor[0];
        final var down = rotationFactor[1];
        final var left = rotationFactor[2];
        final var right = rotationFactor[3];

        for (int i = 0; i < top; i++) {

        }
        for (int i = 0; i < down; i++) {

        }
        for (int i = 0; i < left; i++) {

        }
        for (int i = 0; i < right; i++) {

        }

        return coordinates;
    }





    public static String decryptToken(String target, String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            sb.append((char)(target.charAt(i) ^ key.charAt(i % key.length())));
        }
        return sb.toString();
    }

    public static String decryptToken(String target, String key, String[] combinationToken) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            for (int j = 0; j < combinationToken.length; j++) {
                sb.append((char)(target.charAt(i) ^ combinationToken[j].charAt(i % combinationToken[j].length())));
            }
        }
        return sb.toString();
    }

//    public static int[] transposeColum(String[][][] box) {
//
//
//    }
}
