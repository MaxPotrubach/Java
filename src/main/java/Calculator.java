public class Calculator {
    public double sum(double a, double b) throws InvalidInputException {
        valid(a,b);
        return a + b;
    }
    public double sub(double a, double b) throws InvalidInputException {
        valid(a,b);
        return a - b;
    }
    public double mul(double a, double b) throws InvalidInputException {
        valid(a,b);
        return a * b;
    }
    public double div(double a, double b)throws InvalidInputException {
        valid(a, b);
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    public double pow(double a, double b) throws InvalidInputException {
        valid(a,b);
        return Math.pow(a, b);
    }

    public double sqrt(double a) throws InvalidInputException {
        oneNumValid(a);
        if (a < 0) throw new ArithmeticException("Square root of negative number");
        return Math.sqrt(a);
    }

    public double abs(double a) throws InvalidInputException {
        oneNumValid(a);
        return Math.abs(a);
    }

    public void valid(double a, double b) throws InvalidInputException {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new InvalidInputException("Incorrect input");
        }
    }
    public void oneNumValid(double a) throws InvalidInputException {
        if (Double.isNaN(a)) {
            throw new InvalidInputException("Incorrect input");
        }
    }
}
