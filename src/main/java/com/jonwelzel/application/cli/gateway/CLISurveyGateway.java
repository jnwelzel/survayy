package com.jonwelzel.application.cli.gateway;

import com.jonwelzel.application.cli.entity.GenericQuestionEntity;
import com.jonwelzel.application.cli.exception.InvalidParametersException;
import com.jonwelzel.application.cli.pojo.GenericAnswer;
import com.jonwelzel.application.cli.pojo.GenericQuestion;
import com.jonwelzel.application.cli.pojo.QuestionHeaderPositions;
import com.jonwelzel.application.cli.pojo.QuestionType;
import com.jonwelzel.core.gateway.survey.SurveyDataParseException;
import com.jonwelzel.core.gateway.survey.SurveyGateway;
import com.jonwelzel.core.pojo.*;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.jonwelzel.application.cli.util.FormatUtils.submittedAtFormatter;
import static com.jonwelzel.application.cli.util.NumberUtils.stringToInteger;
import static com.jonwelzel.application.cli.util.NumberUtils.stringToLong;

public class CLISurveyGateway implements SurveyGateway {
    @Override
    public Survey getSurveyFromRawData(Object rawData) throws SurveyDataParseException {
        failIfParamIsNotArrayOfString(rawData);
        String[] paths = (String[]) rawData;
        failIfAnyPathsAreMissing(paths);
        Survey result;

        try {
            List<String[]> surveyQuestions = readCSV(paths[0]);
            List<String[]> surveyResponses = readCSV(paths[1]);

            List<String> headers = Arrays.asList(surveyQuestions.get(0));
            QuestionHeaderPositions headerPositions = new QuestionHeaderPositions(headers.indexOf("type"),
                    headers.indexOf("theme"), headers.indexOf("text"));
            failIfAnyQuestionHeaderIsMissing(headerPositions);

            List<GenericQuestion> genericQuestions = GenericQuestionEntity.extractGenericQuestions(surveyQuestions,
                    surveyResponses, headerPositions);
            List<RatingQuestion> ratingQuestions = getRatingQuestions(genericQuestions.stream().filter(
                    question -> question.getQuestionType().equals(QuestionType.RATING)).collect(Collectors.toList()));
            List<SingleSelectQuestion> singleSelectQuestions = getSingleSelectQuestions(genericQuestions.stream().filter(
                    question -> question.getQuestionType().equals(QuestionType.SINGLE_SELECT)).collect(Collectors.toList()));

            int totalParticipantCount = surveyResponses.size();
            int totalResponseCount = getTotalResponseCount(surveyResponses);

            result = new Survey(1L, ratingQuestions, singleSelectQuestions, totalParticipantCount,
                    totalResponseCount);
        } catch (IOException | InvalidParametersException e) {
            throw new SurveyDataParseException(e.getMessage());
        }

        return result;
    }

    private List<String[]> readCSV(String path) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(path));
        List<String[]> result = reader.readAll();
        reader.close();

        return result;
    }

    private List<RatingQuestion> getRatingQuestions(List<GenericQuestion> genericQuestions) {
        List<RatingQuestion> result = new ArrayList<>();
        AtomicLong ids = new AtomicLong(1L);

        for (GenericQuestion gq : genericQuestions) {
            RatingQuestion question = new RatingQuestion(ids.getAndIncrement(), gq.getTheme(), gq.getText(),
                    new ArrayList<>());
            getRatingAnswers(gq.getAnswers(), question);
            result.add(question);
        }

        return result;
    }

    private void getRatingAnswers(List<GenericAnswer> answers, RatingQuestion question) {
        AtomicLong ids = new AtomicLong(1L);

        for (GenericAnswer ga : answers) {
            question.getAnswers().add(new RatingAnswer(ids.getAndIncrement(), ga.getEmailValue(),
                    stringToLong(ga.getEmployeeIdValue()), submittedAtFormatter(ga.getSubmittedAtValue()), question,
                    stringToInteger(ga.getAnswerValue())));
        }
    }

    private List<SingleSelectQuestion> getSingleSelectQuestions(List<GenericQuestion> genericQuestions) {
        List<SingleSelectQuestion> result = new ArrayList<>();
        AtomicLong ids = new AtomicLong(1L);

        for (GenericQuestion gq : genericQuestions) {
            SingleSelectQuestion question = new SingleSelectQuestion(ids.getAndIncrement(), gq.getTheme(), gq.getText(),
                    new ArrayList<>());
            getSingleSelectAnswers(gq.getAnswers(), question);
            result.add(question);
        }

        return result;
    }

    private Integer getTotalResponseCount(List<String[]> surveyResponses) {
        Integer responseCount = 0;

        final int submittedAtIndex = 2;
        for (String[] sr : surveyResponses) {
            if (submittedAtFormatter(sr[submittedAtIndex]) != null) {
                responseCount++;
            }
        }

        return responseCount;
    }

    private void getSingleSelectAnswers(List<GenericAnswer> answers, SingleSelectQuestion question) {
        AtomicLong ids = new AtomicLong(1L);

        for (GenericAnswer ga : answers) {
            question.getAnswers().add(new SingleSelectAnswer(ids.getAndIncrement(), ga.getEmailValue(),
                    stringToLong(ga.getEmployeeIdValue()), submittedAtFormatter(ga.getSubmittedAtValue()), question,
                    ga.getAnswerValue()));
        }
    }

    private void failIfAnyPathsAreMissing(String[] paths) throws SurveyDataParseException {
        if (paths.length != 2) {
            throw new SurveyDataParseException("Both survey and survey response file paths are required.");
        }
    }

    private void failIfParamIsNotArrayOfString(Object rawData) throws SurveyDataParseException {
        if (!(rawData instanceof String[])) {
            throw new SurveyDataParseException("Parameter must be an instance of 'String[]'");
        }
    }

    private void failIfAnyQuestionHeaderIsMissing(QuestionHeaderPositions hp) throws SurveyDataParseException {
        if (hp.getText() == -1 || hp.getTheme() == -1 || hp.getType() == -1) {
            throw new SurveyDataParseException("The question headers should all be present and follow the pattern: ['theme', 'type', 'text']");
        }
    }
}
