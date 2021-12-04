package core.process;

import core.hooks.Function;

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
