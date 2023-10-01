/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package first.lab.app;

import first.lab.list.LinkedList;

import static first.lab.utilities.StringUtils.join;
import static first.lab.utilities.StringUtils.split;

import java.util.Scanner;

import org.apache.commons.text.WordUtils;

public class App {
    public static void main(String[] args) {
        System.out.println();
        Scanner line = new Scanner(System.in);
        System.out.println("----------LAB 1, STEP 4----------");

        StringCalculator calculator = new StringCalculator();

        System.out.print("Please, Enter line: ");
        String lineString = line.nextLine();
        int sum = calculator.add(lineString);
        System.out.printf("Result: %d \n", sum);
    }
}
