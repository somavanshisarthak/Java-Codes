//Author: Sarthak Somavanshi
import java.util.Scanner;

abstract class Triangle {
    abstract double calculateArea();
}

class RightTriangle extends Triangle {
    private double base;
    private double height;

    public RightTriangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double calculateArea() {
        return (base * height) / 2;
    }
}

public class TriangleAreaCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the base of the right triangle: ");
        double base = scanner.nextDouble();

        System.out.print("Enter the height of the right triangle: ");
        double height = scanner.nextDouble();

        RightTriangle triangle = new RightTriangle(base, height);
        double area = triangle.calculateArea();
        System.out.print