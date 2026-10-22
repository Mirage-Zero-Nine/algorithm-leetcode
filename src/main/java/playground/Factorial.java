package playground;

/**
 * @author BorisMirage
 * Time: 2019/04/02 16:44
 * Created with IntelliJ IDEA
 */

public class Factorial {

    public int factorial(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Factorial is undefined for negative numbers");
        }
        if (num <= 1) {
            return 1;
        }
        return num * factorial(num - 1);
    }

    public static void main(String[] args) {
        Factorial t = new Factorial();
        System.out.println(t.factorial(10));
    }
}


