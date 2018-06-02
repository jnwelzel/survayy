package com.jonwelzel.application.cli;

import com.jonwelzel.application.cli.builder.CLIGetSurveySummaryUseCaseBuilder;

import java.util.Scanner;

/**
 * CLI main application
 */
public class CLIApp {

    public static void main(String[] args) {
        Scanner terminalInput = new Scanner(System.in);

        System.out.print("Please type the survey file path: ");
        String surveyFilePath = terminalInput.nextLine();

        System.out.print("Now the survey response file path: ");
        String surveyResponseFilePath = terminalInput.nextLine();

        new CLIGetSurveySummaryUseCaseBuilder().getUseCase().
                execute(new String[]{surveyFilePath, surveyResponseFilePath});
    }

}
