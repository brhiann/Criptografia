package core.process;

import core.hooks.Function;

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
