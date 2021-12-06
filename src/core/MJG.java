package core;

import jdk.jfr.Description;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MJG {


    public MJG() {

    }

    sealed public static abstract class Function {

        private int n;
        private double[] x;

        public Function(int n) {
            this.n = n;
            this.x = new double[n];
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public double[] getX() {
            return x;
        }

        public void setX(double[] x) {
            this.x = x;
        }

        public abstract double eval(double x);

        @Description("Linear function f(x) = mx + b")
        public static final class LinearFunction extends Function {

            private double b;
            private double gradient;

            public LinearFunction(double[] x1, double[] x2) {
                super(1);
                this.gradient = gradient(x1, x2);
                this.b = evalYAxis(x1);
                setX(new double[]{gradient});
            }

            public double getB() {
                return b;
            }

            public void setB(double b) {
                this.b = b;
            }

            public double getGradient() {
                return gradient;
            }

            public void setGradient(double gradient) {
                this.gradient = gradient;
            }

            private double gradient(double[] x1, double[] x2) {
                return (x2[1] - x1[1]) / (x2[0] - x1[0]);
            }

            private double evalYAxis(double[] x1) {
                return -1 * (getGradient() * x1[0]) + (x1[1]);
            }

            @Override
            public String toString() {
                return "f(x) = " + getGradient() + "x" + " + " + getB();
            }

            @Override
            public double eval(double x) {
                return (x * getGradient()) + getB();
            }
        }

        @Description("Quadratic function f(x) = ax^2 + bx + c")
        public static final class QuadraticFunction extends Function {

            public QuadraticFunction(double a, double b, double c) {
                super(1);
                setX(new double[]{a, b, c});
            }

            @Override
            public String toString() {
                return "f(x) = " + getX()[0] + "x^2" + " + " + getX()[1] + "x" + " + " + getX()[2];
            }

            @Override
            public double eval(double x) {
                return (getX()[0] * Math.pow(x, 2)) + (getX()[1] * x) + getX()[2];
            }
        }

        @Description("Exponential function f(x) = ae^(bx)")
        public static final class ExponentialFunction extends Function {

            public ExponentialFunction(double a, double b) {
                super(1);
                setX(new double[]{a, b});
            }

            @Override
            public String toString() {
                return "f(x) = " + getX()[0] + "e^(" + getX()[1] + "x)";
            }

            @Override
            public double eval(double x) {
                return getX()[0]* Math.exp(getX()[1] * x);
            }
        }

        @Description("Logarithmic function f(x) = aln(bx)")
        public static final class LogarithmicFunction extends Function {

            public LogarithmicFunction(double a, double b) {
                super(1);
                setX(new double[]{a, b});
            }

            @Override
            public double eval(double x) {
                return (getX()[0])*Math.log(getX()[1]*x);
            }

            @Override
            public String toString() {
                return "f(x) = "+getX()[0]+"ln("+getX()[1]+"x)";
            }
        }
    }

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

//    public static String reverse(String target, String key) {
//
//    }

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

        public static String[] combination(
                char target,
                String key,
                String[][][] box,
                int[] rotationFactor) {
            var coordinates = new String[4];
            var axisDynamic = new AxisDynamic();
            final var top = rotationFactor[0];
            final var down = rotationFactor[1];
            final var left = rotationFactor[2];
            final var right = rotationFactor[3];

            return axisDynamic
                    .yLoop("t", top)
                    .yLoop("d", down)
                    .xLoop("l", left)
                    .xLoop("r", right)
                    .getAxis();
        }


        public static String[][][] finalArea(String[] combination) {
            var preArea = new ArrayList<String[][]>();

            for (int i = 0; i < combination.length; i++) {
                switch (combination[i]) {
                    case "x" -> preArea.add(((Axis.getABC_UP())));
                    case "y" -> preArea.add(((Axis.getABC_NUM())));
                    case "z" -> preArea.add(((Axis.getABC_UP_NUM())));
                    case "-x" -> preArea.add(((Axis.getABC_LOW())));
                    case "-y" -> preArea.add(((Axis.getABC_SPECIAL())));
                    case "-z" -> preArea.add(((Axis.getABC_LOW_NUM())));
                }

            }
            return preArea.toArray(new String[0][0][0]);
        }

        public static String charToBinary(char character) {
            var value = String.valueOf(character).hashCode();
            if (value <= 0) {
                return "0";
            }
            StringBuilder binary = new StringBuilder();
            while (value > 0) {
                short remainder = (short) (value % 2);
                value = value / 2;
                binary.insert(0, String.valueOf(remainder));
            }
            return binary.toString();
        }

        public static final String encode(String target, String key) {
            var reverseKey = new StringBuilder(key).reverse().toString().toCharArray();
            var reverseTarget = new StringBuilder(target).reverse().toString().toCharArray();
            var result = new ArrayList<String>();
            for(int i = 0; i < reverseTarget.length; i++) {

                var p1 = new double[]{0.0, 0.0};
                var p2 = new double[]{0.0, 0.0};

                int displacement = reverseTarget.length > reverseKey.length ? reverseKey.length * i: reverseTarget.length * i  % reverseKey.length;

                var fqp1 = new Function.QuadraticFunction(
                        reverseTarget.length,
                        reverseKey.length,
                        reverseTarget.length >> displacement).eval(i);
                var fqp2 = new Function.QuadraticFunction(
                        reverseTarget.length,
                        reverseKey.length,
                        reverseTarget.length >> displacement).eval(i + 1);

                var fqp3 = new Function.QuadraticFunction(
                        reverseTarget.length,
                        reverseKey.length,
                        reverseTarget.length >> displacement).eval(i + 2);

                var fqp4 = new Function.QuadraticFunction(
                        reverseTarget.length,
                        reverseKey.length,
                        reverseTarget.length >> displacement).eval(i + 3);

                System.out.println(fqp1);
                System.out.println(fqp2);
                System.out.println(fqp3);
                System.out.println(fqp4);

                var fx = Math.round(new Function.LinearFunction(new double[]{fqp1, fqp2}, new double[]{fqp3, fqp4}).getGradient() *2);

                result.add(String.valueOf(fx));

            }
            return result.toString();

        }



//    public static String decryptToken(String target, String key) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < target.length(); i++) {
//            sb.append((char)(target.charAt(i) ^ key.charAt(i % key.length())));
//        }
//        return sb.toString();
//    }
//
//    public static String decryptToken(String target, String key, String[] combinationToken) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < target.length(); i++) {
//            for (int j = 0; j < combinationToken.length; j++) {
//                sb.append((char)(target.charAt(i) ^ combinationToken[j].charAt(i % combinationToken[j].length())));
//            }
//        }
//        return sb.toString();
//    }

//    public static int[] transposeColum(String[][][] box) {
//
//
//    }
    }

    public static class AxisDynamic {
        /**
         *                 z[1]
         *                 |    / y[4]
         *                 |   /
         *                 |  /
         *                 | /
         * -x[2]-----------|------------- x[0]
         *                /|
         *               / |
         *              /  |
         *       -y[5] /   |
         *                 -z[3]
         */
        private String[] axis;

        public AxisDynamic() {
            this.axis = new String[]{"x", "z", "-x", "-z", "y", "-y"};;
        }

        public String[] getAxis() {
            return axis;
        }

        public AxisDynamic setAxis(String[] axis) {
            this.axis = axis;
            return this;
        }

        public AxisDynamic xLoop(String direction, int cycles) {
            var copy = axis.clone();
            var scope = axis.length;
            return switch (direction) {
                case "r" -> {
                    for (int j = 0; j < cycles; j++) {
                        copy = axis.clone();
                        axis[0] = copy[5];
                        axis[1] = copy[1];
                        axis[2] = copy[4];
                        axis[3] = copy[3];
                        axis[4] = copy[0];
                        axis[5] = copy[2];
                    }
                    yield this;
                }
                case "l" -> {
                    for (int j = 0; j < cycles*3; j++) {
                        copy = axis.clone();
                        axis[0] = copy[5];
                        axis[1] = copy[1];
                        axis[2] = copy[4];
                        axis[3] = copy[3];
                        axis[4] = copy[0];
                        axis[5] = copy[2];
                    }
                    yield this;
                }
                default -> this;
            };
        }

        public  AxisDynamic yLoop(String direction, int cycles) {
            var copy = axis.clone();
            var scope = 3;
            return switch (direction) {
                case "t" -> {
                    for (int j = 0; j < cycles; j++) {
                        copy = axis.clone();
                        for (int i = 0; i < scope; i++) {
                            axis[i + 1] = copy[i];
                            if (i == scope - 1) {
                                axis[0] = copy[scope];
                            }
                        }
                    }
                    yield this;
                }
                case "d" -> {
                    for (int j = 0; j < cycles*3; j++) {
                        copy = axis.clone();
                        for (int i = 0; i < scope; i++) {
                            axis[i + 1] = copy[i];
                            if (i == scope - 1) {
                                axis[0] = copy[scope];
                            }
                        }
                    }
                    yield this;
                }
                default -> this;
            };
        }
    }

    public class Derivable {

        private Function function;
        private double x;

        public Derivable(Function function, double x) {
            this.function = function;
            this.x = x;
        }

        public Function getFunction() {
            return function;
        }

        public void setFunction(Function function) {
            this.function = function;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double eval() {
            return switch (function.getClass().getSimpleName()) {
                case "LinearFunction" -> function.getX()[0];
                case "QuadraticFunction" -> function.getX()[0]*2*x + function.getX()[1];
                case "ExponentialFunction" -> (function.getX()[0])*(function.getX()[1])*function.eval(x);
                case "LogarithmicFunction" -> (function.getX()[0])/x;
                default -> throw new IllegalStateException("Error");
            };
        }
    }

    public class Integrate {

        Function function;
        double x;

        public Integrate(Derivable dx) {
            this.function = dx.getFunction();
            this.x = dx.getX();
        }

//    public double[] eval() {
//        return switch (function.getClass().getSimpleName()) {
//            case "LinearFunction" -> {
//
//            };
//            case "QuadraticFunction" -> dx.getX()[0]*2*x + dx.getX()[1];
//            case "ExponentialFunction" -> (dx.getX()[0])*(dx.getX()[1])*dx.eval(x);
//            case "LogarithmicFunction" -> (dx.getX()[0])/x;
//            default -> throw new IllegalStateException("Error");
//        };
//    }

    }

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

        public static String[][][] getArea() {
            return new String[][][]{
                    getABC_UP(), // x>0
                    getABC_UP_NUM(), // z>0
                    getABC_LOW(), // x<0
                    getABC_LOW_NUM(), // z<0
                    getABC_NUM(), // y>0
                    getABC_SPECIAL(), // y<0

            };
        }
    }
}
