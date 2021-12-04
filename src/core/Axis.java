package core;

import jdk.jfr.Description;

public class Axis {

    @Description("static X-axis; Where {x>0}")
    public static String[][] getABC_UP() {
        return new String[][]{
                {"A", "B", "C", "D", "E"},
                {"F", "G", "H", "I", "J"},
                {"K", "L", "M", "N", "Ñ"},
                {"O", "P", "Q", "R", "S"},
                {"T", "U", "V", "W", "X"},
                {"Y", "Z", " ", ".", ","}
        };
    }

    @Description("static X-axis; Where {x<0}")
    public static String[][] getABC_LOW() {
        return new String[][]{
                {"a", "b", "c", "d", "e"},
                {"f", "g", "h", "i", "j"},
                {"k", "l", "m", "n", "ñ"},
                {"o", "p", "q", "r", "s"},
                {"t", "u", "v", "w", "x"},
                {"y", "z", " ", ".", ","}
        };
    }

    @Description("static Y-axis; Where {y>0}")
    public static String[][] getABC_NUM() {
        return new String[][]{
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"},
                {"0", "!", "?"}
        };
    }

    @Description("static Y-axis; Where {y<0}")
    public static String[][] getABC_SPECIAL() {
        return new String[][]{
                {"@", "#", "$", "%", "&"},
                {"*", "(", ")", "-", "_"},
                {"+", "=", "|", "\\", "/"}
        };
    }

    @Description("static Z-axis; Where {z>0}")
    public static String[][] getABC_UP_NUM() {
        return new String[][]{
                {"Á", "É", "Í", "Ó", "Ú"},
        };
    }

    @Description("static Z-axis; Where {z<0}")
    public static String[][] getABC_LOW_NUM() {
        return new String[][]{
                {"á", "é", "í", "ó", "ú"},
        };
    }


    /**
     *                                          z
     *     /--------------/|                    |    / y
     *    /|             / |                    |   /
     *   / |            /  |                    |  /
     *  /  |           /   |                    | /
     * +--------------+ -- /    -x -------------|------------- x
     * |   /          |   /                    /|
     * |  /           |  /                    / |
     * | /            | /                    /  |
     * |______________|/                 -y /   |
     *                                         -z
     *
     *
     */

    public static String[][][] getBox() {
        return new String[][][]{
                getABC_LOW(), // x<0
                getABC_UP(), // x>0
                getABC_SPECIAL(), // y<0
                getABC_NUM(), // y>0
                getABC_LOW_NUM(), // z<0
                getABC_UP_NUM() // z>0

        };
    }
}
