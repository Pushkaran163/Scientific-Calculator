# Scientific-Calculator

A simple scientific calculator implemented in Java using Swing. This calculator supports basic arithmetic operations as well as some scientific functions.

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, division.
- Scientific functions: sine, cosine, tangent, logarithm.
- Parentheses for grouping operations.
- Clear (C) and Clear Entry (CE) functionalities.

## Screenshots

![Capture](https://github.com/user-attachments/assets/031f5870-aab4-4d02-8ef0-b8071b06ab19)


## Usage

- Numbers and Operators: Click on buttons to input numbers and operators.
- Scientific Functions: Click on functions like sin, cos, tan, and log to apply these operations to the input value.
- Parentheses: Use ( and ) to group operations.
- Clear: Click C to clear the entire input.
- Clear Entry: Click CE to remove the last character from the input.
- Evaluate: Click = to calculate the result of the current expression.

## Code Overview

- ScientificCalculator.java: Contains the main class that defines the GUI and logic for the calculator.
- Constructor: Sets up the calculator UI and initializes components.
- actionPerformed: Handles button clicks and updates the display.
- evaluate: Evaluates mathematical expressions using stacks for operators and operands.
- applyOperation: Applies arithmetic and scientific operations.
- precedence: Determines the precedence of operators.
