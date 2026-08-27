import java.util.Scanner;

//declares valid states for any guess
public enum CompareGuess
{
    CORRECT,
    HIGH,
    LOW
}

void main(String[] args)
{
    //Start, explain rules and commands.
    System.out.println("Guessing Game");
    System.out.println("=============");
    System.out.println("Guess what number between 1 and 100 is the correct number.");
    System.out.println("Press Esc at any time to quit program.");

    //Generate number through method call
    int randomNumber = getRandomNumber( 1, 100);

    //Initialize Scanner
    Scanner inputReader = new Scanner(System.in);

    //Main gameplay loop, exits on gameWon
    boolean gameWon = false;
    while (!gameWon)
    {
        //Ask for a guess
        int guess = askForInput(inputReader);
        System.out.println("You guessed: " + guess);

        //Compare guess to randomNumber and evaluated based on state
        CompareGuess outcome = result(guess, randomNumber);

        guessFeedback(outcome);
    }
}
//Math.random returns double between 0 and 1,
// multiply by range and add 1 to match scale.
public int getRandomNumber(int min, int max)
{
    return (int) ((Math.random() * (max - min + 1)) + min);
}
//Scanner takes input
public int askForInput(Scanner inputReader)
{
    boolean validInput = false;
    int guessedNumber = 0;

    while (!validInput)
    {
        System.out.println("Enter your guess: ");

        if (inputReader.hasNextInt())
        {
            guessedNumber = inputReader.nextInt();
            validInput = true;
        } else
        {
            System.out.println("Invalid guess, input an integer.");
            inputReader.next();
        }
    }

    return guessedNumber;
}
//Compare guess and randomNumber using custom enum to assign state
public CompareGuess result(int guess, int randomNumber)
{
    if (guess == randomNumber)
    {
        return CompareGuess.CORRECT;
    }
    else if (guess < randomNumber)
    {
        return CompareGuess.LOW;
    }
    else
    {
        return CompareGuess.HIGH;
    }
}

public void guessFeedback(CompareGuess outcome)
{
    if (outcome == CompareGuess.CORRECT)
    {
        System.out.println("You guessed correct!");
    }
    else if (outcome == CompareGuess.HIGH)
    {
        System.out.println("You guessed wrong, the correct number is lower than your guess.");
    }
    else
    {
        System.out.println("You guessed wrong, the correct number is higher than your guess.");
    }
}

//spelet slut -> be om input, spela igen? Starta om loop från generera tal
//quit kommando som fungerar i alla faser av programmet