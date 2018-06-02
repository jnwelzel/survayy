[![CircleCI](https://circleci.com/gh/jnwelzel/survayy/tree/master.svg?style=svg)](https://circleci.com/gh/jnwelzel/survayy/tree/master)
[![Maintainability](https://api.codeclimate.com/v1/badges/c6f787366c60ed7482f3/maintainability)](https://codeclimate.com/github/jnwelzel/survayy/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/c6f787366c60ed7482f3/test_coverage)](https://codeclimate.com/github/jnwelzel/survayy/test_coverage)
[![codecov](https://codecov.io/gh/jnwelzel/survayy/branch/master/graph/badge.svg)](https://codecov.io/gh/jnwelzel/survayy)

# survayy (it's not a typo)
A surveys processor

# Architecture and additional information
- Uncle Bob's `clean architecture` because it's something I've already worked with and have experienced first-hand
the speed boost in development it offers in the mid to long-term.
- Uncle Bob's rules for TDD (red, green, refactor) for the most part.
- Split my code into two main packages: `core` and `application`. The idea behind this is to provide an easy way to 
separate them into two projects in the future.
- Tried to keep things as extensible as possible by not tying any features to any particular implementation (only care
about the interfaces).

# Requirements
- Java 8
- Maven

# Try it
- `$ mvn integration-test`
- `$ java -jar target/survayy-1.0.0-SNAPSHOT.jar`

# //TODO
- [x] Results from unsubmitted surveys should not be considered in the rating questions average
- [ ] Think of a name/signature for `SurveyGateway` method `getSurveyFromRawData(Object rawData)` that makes more sense
- [x] Ignore `pojo` packages in coverage
- [ ] Refactor `CLISurveyGateway` getter/setter methods
- [ ] Separate `core` from `application`
