# Number Guessing Game

A simple and interactive number guessing game built in Java. Test your luck and logic by guessing a randomly generated number within a given range!

## Features

- Random number generation between 1 and 100
- Input validation to handle invalid entries
- Feedback on whether your guess is too high or too low
- Attempt counter with a maximum of 10 tries
- Performance rating based on number of attempts
- Option to play again after each round

## Skills Demonstrated

- **Core Java Concepts**: Random number generation, loops (while)
- **User Input Handling**: Scanner class with input validation
- **Conditional Statements**: if-else logic for game feedback

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/NumberGuessingGame.git
   cd NumberGuessingGame
   ```

2. Compile the Java file:
   ```bash
   javac NumberGuessingGame.java
   ```

3. Run the game:
   ```bash
   java NumberGuessingGame
   ```

## Sample Output

```
----------------------------------
  THE ULTIMATE NUMBER CHALLENGE   
 I'm thinking of a number (1-100) 
   You have 7 attempts to win! 
----------------------------------

Enter your guess (Attempt 1/10): 50
Too HIGH! Try a lower number.
Attempts remaining: 9

Enter your guess (Attempt 2/10): 25
Too LOW! Try a higher number.
Attempts remaining: 8

Enter your guess (Attempt 3/10): 37
Congratulations! You guessed it!
```

## Project Structure

```
NumberGuessingGame/
├── NumberGuessingGame.java    # Main game logic
└── README.md                  # Project documentation
```

## Requirements

- Java JDK 8 or higher

## License

This project is open-source and available under the MIT License.
