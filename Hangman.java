import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {


        String[] wordArray = {"moose", "deer", "cat", "dog", "love", "chair", "coffee", "spain", "denmark", "hello"};

        char[] availableLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'j', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        boolean gameStatus = true;
        char userGuess, userInput = 'y';
        int wrongGuesses = 7, wordsUsed = 0;
        int myRandomNumber = getRandomNumber(10); //see if there's away to define myRandomNumber modularly

        String myWord = wordArray[myRandomNumber];
        int wordLength = myWord.length();

        //MakingVisibleLettersArray
        char[] visibleLetters = new char[wordLength];
        for (int i = 0; i < wordLength; i++) {
            visibleLetters[i] = '-';
        }

        printStartup(wordLength);



        while (gameStatus && wordsUsed < 10) {
            System.out.println("Your word: " + myWord); //get rid of this line for final submission
            displayStatus(visibleLetters, availableLetters, wrongGuesses);
            userGuess = getUserGuess();

            if (!hasLetterBeenUsed(userGuess, availableLetters)) { //get rid
                System.out.println("Your guess was: " + userGuess);
                System.out.println(isLetterInWord(userGuess, myWord));

                //update visibleLetters and wrongGuesses
                if (isLetterInWord(userGuess, myWord)) {
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
                printLines();
            }


            if (wrongGuesses == 0 || hasWordBeenGuessed(visibleLetters))
            {
                if (wordsUsed < 10) {
                    userInput = askUserToPlayAgain(userInput);

                    if (userInput == 'y') {
                        System.out.println("You are in the if statement");
                        printLines();


                        myRandomNumber++;
                        myWord = wordArray[(myRandomNumber) % 10];
                        wordLength = myWord.length();
                        wrongGuesses = 7;
                        wordsUsed++;
                        resetAvailableLetters(availableLetters);
                        visibleLetters = resetVisibleLetters(visibleLetters, wordLength);
                        gameStatus = true;

                        printLines();
                        System.out.println("words used: " + wordsUsed);
                    }
                    else{
                        System.out.println("You are in the else statement");
                        printLines();
                        gameStatus = false;
                    }
                }
                else
                    finishGame(wordsUsed);
            }
        }

        finishGame(wordsUsed);
    }



        public static void printStartup(int wordLength)
        {
            System.out.println("The word to guess has " + wordLength + " letters.");
            System.out.println();
        }


        public static void displayStatus(char[] visibleLetters, char[] availableLetters, int wrongGuesses)
        {
            printList(visibleLetters);
            System.out.print("Available letters: ");
            printList(availableLetters);
            System.out.println(wrongGuesses + " incorrect guesses remaining.");
        }

        public static void printList(char[] array)
        {
            for (int i = 0; i < array.length; i++)
            {
                System.out.print(array[i]);
            }
            System.out.println();
        }

        public static boolean isLetterInWord(char letter, String myWord)
        {
            for (int i = 0; i < myWord.length(); i++) {
                if (myWord.charAt(i) == letter)
                    return true;
            }
            return false;
        }

        public static boolean hasLetterBeenUsed(char letter, char[] myArray)
        {
            for (int i = 0; i < myArray.length; i++)
            {
                if (myArray[i] == letter)
                    return false;
            }
            return true;
        }

        public static int getRandomNumber(int bound)
        {
            Random randomNumber = new Random();
            int myRandomNumber = randomNumber.nextInt(bound);
            return myRandomNumber;
        }

        public static char getUserGuess()
        {
            Scanner kb = new Scanner(System.in);
            System.out.println("Please enter your guess: ");
            char userGuess = kb.next().charAt(0);
            kb.nextLine();
            return userGuess;
        }

        public static void printLines()
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

        public static void finishGame(int wordsUsed)
        {
            if (wordsUsed > 9)
            {
                System.out.println("That's all the words I have stored. Thanks for playing!");
                printLines();
            }
        }
}