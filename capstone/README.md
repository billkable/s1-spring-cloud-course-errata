# README

Welcome to the Spring Cloud Developer course!

## Use of git

This course is organized in a way to teach individual distributed
application pattern implementations provided by Spring Cloud.
The labs are designed to be independent from one another,
and in most cases,
a given lab does not depend on the previous lab.

This provided codebase is a standalone repository,
but each lab is implemented as an independent branch with a pair
of tags:

-   `{lab-name}-start` -> start point of the lab

-   `{lab-name}-solution` -> solution of the lab,
    you may use this to compare to your solution,
    particularly useful if you get stuck on a particular lab.

The `{lab-name}` branch is set to point HEAD at the `{lab-name}-start`
tag so the student can merely checkout to the branch and work the lab.

## Build Tools

This course supports use of either Maven or Gradle.
The basic build artifacts are provided for you,
and each lab that modifies or introduces changes in build or 
dependencies will include instructions for both tools.

## Scripts

The `scripts` directory includes tools for executing REST requests
over http.
Four tools are supported:

- cURL
- HTTPie
- Postman
- IntelliJ REST client

Both Postman and IntelliJ REST clients include correlations between
requests and test assertions that allow to run as automated acceptance
test script.