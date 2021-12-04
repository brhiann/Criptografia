package core.hooks;

import jdk.jfr.Description;

sealed public abstract class Function {

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
