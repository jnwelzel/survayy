package com.jonwelzel;

import java.util.Scanner;

/**
 * Simple main application
 */
public class App {

    public static void main(String[] args) {
        System.out.print("Please type the survey file path: ");
        Scanner terminalInput = new Scanner(System.in);

        String surveyFilePath = terminalInput.nextLine();

        System.out.print("Now the survey response file path: ");

        String surveyResponseFilePath = terminalInput.nextLine();

        System.out.println(surveyFilePath + "\n" + surveyResponseFilePath);
    }

}
