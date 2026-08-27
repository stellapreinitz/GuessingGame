//Import Scanner to handle player input
import java.util.Scanner;

//Initialize Scanner
Scanner inputReader = new Scanner(System.in);

//declares valid states for any guess
public enum CompareGuess
{
    CORRECT,
    HIGH,
    LOW
}
void main(String[] args)
{
    boolean playing = true;
    while (playing)
    {
        gameLoop();

        System.out.println("Play again?(Y/N)");
        String replay = inputReader.nextLine();
        if (!replay.equals("Y") && !replay.equals("y"))
        {
            playing = false;
        }
    }
}

void gameLoop()
{
    //Start, explain rules and commands.
    System.out.println("Guessing Game");
    System.out.println("=============");
    System.out.println("Guess what number between 1 and 100 is the correct number.");

    //Generate number through method call
    int randomNumber = getRandomNumber( 1, 100);

    //Main gameplay loop, exits on gameWon
    boolean gameWon = false;
    while (!gameWon)
    {
        //Ask for a guess
        int guess = askForInput();
        System.out.println("You guessed: " + guess);

        //Compare guess to randomNumber and evaluated based on state
        CompareGuess outcome = result(guess, randomNumber);

        //Use evaluated guess to provide feedback
        guessFeedback(outcome);

        //Break loop in guess is correct
        if (outcome == CompareGuess.CORRECT)
        {
            gameWon = true;
        }
    }
}
//Math.random returns double between 0 and 1,
// multiply by range and add 1 to match scale.
public int getRandomNumber(int min, int max)
{
    return (int) ((Math.random() * (max - min + 1)) + min);
}
//Scanner takes input, invalid input exception
public int askForInput()
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
    inputReader.nextLine();
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
//Gives feedback derived from outcome enum state, void method returns nothing
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