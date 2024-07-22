@UI @Linear
Feature: JavaScript Alerts Handling

  Background:
    Given The user is navigating on the JavaScript alerts page
    And The user verifies the result message as ""

  Scenario: Handle JavaScript Alert Action
    When The user triggers the JavaScript "Alert" Button
    Then The user verifies the pop message as "I am a JS Alert"
    And The user "accepts" the pop up alert
    And The user verifies the result message as "You successfully clicked an alert"

  Scenario: Handle JavaScript Confirm Action - Accept Pop Up
    When The user triggers the JavaScript "Confirm" Button
    Then The user verifies the pop message as "I am a JS Confirm"
    When The user "accepts" the pop up alert
    And The user verifies the result message as "You clicked: Ok"

  Scenario: Handle JavaScript Confirm Action - Dismiss Pop Up
    When The user triggers the JavaScript "Confirm" Button
    Then The user verifies the pop message as "I am a JS Confirm"
    When The user "dismisses" the pop up alert
    And The user verifies the result message as "You clicked: Cancel"

  Scenario: Handle JavaScript Prompt Action - Input Text - Accept Pop Up
    When The user triggers the JavaScript "Prompt" Button
    And The user enters input "test input" into the prompt
    Then The user verifies the pop message as "I am a JS prompt"
    When The user "accepts" the pop up alert
    Then The user verifies the result message as "You entered: test input"

  Scenario: Handle JavaScript Prompt Action - Input Text - Cancel Pop Up
    When The user triggers the JavaScript "Prompt" Button
    And The user enters input "test input" into the prompt
    Then The user verifies the pop message as "I am a JS prompt"
    When The user "dismisses" the pop up alert
    Then The user verifies the result message as "You entered: null"

  Scenario: Handle JavaScript Prompt Action - Input Text - Forbiden - Accept Pop Up
    When The user triggers the JavaScript "Prompt" Button
    And The user enters input "forbiden" into the prompt
    Then The user verifies the pop message as "I am a JS prompt"
    When The user "accepts" the pop up alert
    Then The user verifies the result message as "You entered the forbiden word"
