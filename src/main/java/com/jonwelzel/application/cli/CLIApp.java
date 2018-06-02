package com.jonwelzel.application.cli;

import com.jonwelzel.application.cli.builder.CLIGetSurveySummaryUseCaseBuilder;

import java.util.Scanner;

/**
 * CLI main application
 */
public class CLIApp {

    public static void main(String[] args) {
        System.out.print("Please type the survey file path: ");
        Scanner terminalInput = new Scanner(System.in);

//        String surveyFilePath = terminalInput.nextLine();
        String surveyFilePath = "/home/jwelzel/Documentos/culture_amp/coding-test-application/example-data/survey-1.csv";

        System.out.print("Now the survey response file path: ");

//        String surveyResponseFilePath = terminalInput.nextLine();
        String surveyResponseFilePath = "/home/jwelzel/Documentos/culture_amp/coding-test-application/example-data/survey-1-responses.csv";

        new CLIGetSurveySummaryUseCaseBuilder().getUseCase().
                execute(new String[]{surveyFilePath, surveyResponseFilePath});
    }

}
