# Selenium Web and Appium Automation Test Framework

## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Folder Structure](#folder-structure)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Linting](#linting)
- [Testing Coverage](#testing-coverage)
- [Contributing](#contributing)
- [References](#references)

## Introduction
This is the Selenium Automation Test Framework designed to automate web application testing using Selenium.

## Prerequisites
1. Java Development Kit (JDK) 8 or higher
2. Maven

- For Appium:-
1. Install NodeJS
2. Install Android Studio
3. Install Appium Server using npm
```shell
npm install -g appium
Command to check installed appium version: `appium --version`
```
4. Add below Android SDK path in the environment variable

- ANDROID_HOME = <path to Sdk folder>
- %ANDROID_HOME%\tools
- %ANDROID_HOME%\tools\bin
- %ANDROID_HOME%\platform-tools
5. Install Appium Inspector

## Getting Started
To get started with the framework, follow these steps:
1. Clone this repository
2. Navigate to the project's root directory
3. Install the required dependencies defined in pom.xml
```Shell
mvn clean install
```
- For Appium:-
1. Start Android Emulator from Android Studio or real device if connected to system
2. Start Appium server using command prompt
```Shell
appium --allow-cors
```

## Folder Structure
- pageObjects: It is an elements pool for each page.
- reports: Using ExtentReports to generate HTML report assets, this folder is git ignored.
- resources: It stores resources like log4j configuration files.
- support: Common support utility methods.
- testData: Test data for test flows and text messages for assertions, as we aim not to use hard-coded data in tests.
- testResults: It stores screenshots or videos generated during test execution, this folder is git ignored.
- tests: (test/java) All the automation tests are stored here, grouped by features. Mobile tests are stored under Mobile folder.

## Running Tests
- Running tests through Maven :-
```Shell
mvn test -Dsurefire.suiteXmlFiles=<provide the testng xml to execute>
```
- Create or Select the required testng xml -> Right click and select Run

## Test Reports
The Extent Reports - HTML Reporter shows a full report of the tests allowing you to filter the report by
passed tests, failed tests, skipped tests and flaky tests. 
- Reports can be found under test -> reports

## Linting
Ensure your code adheres to coding standards using Checkstyle.
``
mvn checkstyle:check
``

#### Automatically fix linting issues
Manually review and fix issues reported by Checkstyle.

## Contributing
We appreciate all contributions to this framework. If you'd like to contribute,
please reach out to Vijay Kumar.