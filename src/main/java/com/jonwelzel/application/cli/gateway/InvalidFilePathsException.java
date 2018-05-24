package com.jonwelzel.application.cli.gateway;

public class InvalidFilePathsException extends RuntimeException {
    public InvalidFilePathsException() {
        super("Both survey and survey response file paths are required.");
    }
}
