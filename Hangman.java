import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {


        String[] wordArray = {"moose", "deer", "cat", "dog", "love", "chair", "coffee", "spain", "denmark", "hello"};

        char[] availableLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'j', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        int wrongGuesses = 7, wordsUsed = 0;
        char userGuess, userInput = 'y';
        int myRandomNumber = GetRandomNumber(10); //see if there's away to define myRandomNumber modularly

        boolean gameStatus = true;

        String myWord = wordArray[myRandomNumber];
        int wordLength = myWord.length();

        //MakingVisibleLettersArray
        char[] visibleLetters = new char[wordLength];
        for (int i = 0; i < wordLength; i++) {
            visibleLetters[i] = '-';
        }


        PrintStartup(wordLength);



        while (gameStatus) { //wrongGuesses != 0 && userInput == 'y'
            //while (wrongGuesses != 0) {
            System.out.println("Your word: " + myWord); //get rid of this line for final submission
            DisplayStatus(visibleLetters, availableLetters, wrongGuesses);


            userGuess = GetUserGuess(); // see if this is good... otherwise, old code above

            if (!HasLetterBeenUsed(userGuess, availableLetters)) { //get rid
                System.out.println("Your guess was: " + userGuess);
                System.out.println(IsLetterInWord(userGuess, myWord));

                //update visibleLetters and wrongGuesses
                if (IsLetterInWord(userGuess, myWord)) {
                    for (int i = 0; i < myWord.length(); i++) {
                        if (myWord.charAt(i) == userGuess) {
                            visibleLetters[i] = userGuess;
                        }
                    }
                } else
                    wrongGuesses--;

                //update availableLetters
                for (int i = 0; i < availableLetters.length; i++) {
                    if (availableLetters[i] == userGuess)
                        availableLetters[i] = '-';
                }
                PrintLines();
            }

            if (wrongGuesses == 0 || hasWordBeenGuessed(visibleLetters))
            {
                userInput = askUserToPlayAgain(userInput);

                if (userInput == 'y') {
                    System.out.println("You are in the if statement");
                    PrintLines();


                    myRandomNumber++;
                    myWord = wordArray[(myRandomNumber) % 10];
                    wordLength = myWord.length();
                    wrongGuesses = 7;
                    wordsUsed++;
                    resetAvailableLetters(availableLetters);
                    visibleLetters = resetVisibleLetters(visibleLetters, wordLength);
                    gameStatus = true;

                    //userInput = 'y';
                    //have int variable that keeps track after 10 rounds have been played
                }
                else{
                    System.out.println("You are in the else statement");
                    PrintLines();
                    gameStatus = false;
                }
            }
        }
    }



        public static void PrintStartup(int wordLength)
        {
            System.out.println("The word to guess has " + wordLength + " letters.");
            System.out.println();
        }


        public static void DisplayStatus(char[] visibleLetters, char[] availableLetters, int wrongGuesses)
        {
            PrintList(visibleLetters);
            System.out.print("Available letters: ");
            PrintList(availableLetters);
            System.out.println(wrongGuesses + " incorrect guesses remaining.");
        }

        public static void PrintList(char[] array)
        {
            for (int i = 0; i < array.length; i++)
            {
                System.out.print(array[i]);
            }
            System.out.println();
        }

        public static boolean IsLetterInWord(char letter, String myWord)
        {
            for (int i = 0; i < myWord.length(); i++) {
                if (myWord.charAt(i) == letter)
                    return true;
            }
            return false;
        }

        public static boolean HasLetterBeenUsed(char letter, char[] myArray)
        {
            for (int i = 0; i < myArray.length; i++)
            {
                if (myArray[i] == letter)
                    return false;
            }
            return true;
        }

        public static int GetRandomNumber(int bound)
        {
            Random randomNumber = new Random();
            int myRandomNumber = randomNumber.nextInt(bound);
            return myRandomNumber;
        }

        public static char GetUserGuess()
        {
            Scanner kb = new Scanner(System.in);
            System.out.println("Please enter your guess: ");
            char userGuess = kb.next().charAt(0);
            kb.nextLine();
            return userGuess;
        }

        public static void PrintLines()
        {
            System.out.println();
            System.out.println();
        }

        public static char askUserToPlayAgain(char userInput)
        {
            System.out.println("Do you want to play again?");
            Scanner kb = new Scanner(System.in);
            System.out.println("y or n ");
            userInput = kb.next().charAt(0);
            kb.nextLine();
            System.out.println("You said: " + userInput);
            return userInput;
        }

        public static boolean hasWordBeenGuessed(char[] visibleLetters)
        {
            for (int i = 0; i < visibleLetters.length; i++)
            {
                if (visibleLetters[i] == '-')
                    return false;
            }
            return true;
        }

        public static void resetAvailableLetters(char[] availableLetters)
        {
            char j = 'a';
            for (int i = 0; i < 26; i++)
            {
                availableLetters[i] = j;
                j++;
            }
        }

        public static char[] resetVisibleLetters(char[] visibleLetters, int wordLength)
        {
            visibleLetters = null;
            visibleLetters = new char[wordLength];
            for (int i = 0; i < wordLength; i++) {
                visibleLetters[i] = '-';
            }
            return visibleLetters;
        }
}