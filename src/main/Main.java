package main;

import core.Axis;
import core.hooks.Hooks;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {


        var point = Hooks.combinationToken('n', "hola", Axis.getBox(), new int[]{1,2,3,4});

        System.out.println(Arrays.toString(point));

        var asd = Hooks.decryptToken("a", "hola", point);

        System.out.println(asd);

    }



}
