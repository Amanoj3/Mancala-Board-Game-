import java.util.Scanner;

public class Mancala {

    private String whoseTurn;
    private final int[] boardArray;
    private boolean turnAgain;
    private String whoWon;

    Scanner scanIn;

    boolean players_slot_empty(String whoseMove) { // check if it is a player's turn and their corresponding slots are empty
        if (whoseMove.equals("Player1") && boardArray[0] == 0 && boardArray[1] == 0 && boardArray[2] == 0 && boardArray[3] == 0 &&
        boardArray[4] == 0 && boardArray[5] == 0) {
            return true;
        }
        else return whoseMove.equals("Player2") && boardArray[7] == 0 && boardArray[8] == 0 && boardArray[9] == 0 && boardArray[10] == 0 &&
                boardArray[11] == 0 && boardArray[12] == 0;
    }

    boolean gameOver() { // detect when game is over and determine winner
        return whoseTurn.equals("Player1") && players_slot_empty(whoseTurn) || whoseTurn.equals("Player2") && players_slot_empty(whoseTurn);
    }

    void determineTurn() {
        if (whoseTurn.equals("Player1") && turnAgain) {
            whoseTurn = "Player1";
        }
        else if (whoseTurn.equals("Player2") && turnAgain) {
            whoseTurn = "Player2";
        }
        else if (whoseTurn.equals("Player1")) {
            whoseTurn = "Player2";
        }
        else if (whoseTurn.equals("Player2")) {
            whoseTurn = "Player1";
        }
    }

    int getCorrespondingPit(String move_letter, String whoseMove) { // A corresponds to index 0, B corresponds to 1, etc.
        if (whoseMove.equals("Player1")) {
            if (move_letter.equals("a")) {
                return 0;
            }
            if (move_letter.equals("b")) {
                return 1;
            }
            if (move_letter.equals("c")) {
                return 2;
            }
            if (move_letter.equals("d")) {
                return 3;
            }
            if (move_letter.equals("e")) {
                return 4;
            }
            if (move_letter.equals("f")) {
                return 5;
            }
        } // end of Player1 moves
        // begin player2 moves
        else {
            if (move_letter.equals("a")) {
                return 12;
            }
            if (move_letter.equals("b")) {
                return 11;
            }
            if (move_letter.equals("c")) {
                return 10;
            }
            if (move_letter.equals("d")) {
                return 9;
            }
            if (move_letter.equals("e")) {
                return 8;
            }
            if (move_letter.equals("f")) {
                return 7;
            }
        }
        return -999; // placeholder
    }

    int getOppositePit(int key) {

        // player one's keys
        if (key == 0) {
            return 12;
        }
        if (key == 1) {
            return 11;
        }
        if (key == 2) {
            return 10;
        }
        if (key == 3) {
            return 9;
        }
        if (key == 4) {
            return 8;
        }
        if (key == 5) {
            return 7;
        }
        // end player one's keys

        // begin player two's keys
        if (key == 7) {
            return 5;
        }
        if (key == 8) {
            return 4;
        }

        if (key == 9) {
            return 3;
        }

        if (key == 10) {
            return 2;
        }

        if (key == 11) {
            return 1;
        }

        if (key == 12) {
            return 0;
        }


        return -999; // placeholder
    }

    Mancala() {

        whoWon = "None";
        turnAgain = false; // does player X get to move again?
        scanIn = new Scanner(System.in); // to scan userInput
        int numSlots = 14; // total number of slots in the board, including the mancalas
        whoseTurn = "Player1";
        boardArray = new int[numSlots];

        for (int i = 0; i < numSlots; i++) {
            boardArray[i] = 0;
        }

        for (int i = 0; i < 6; i++) {
            boardArray[i] = 4;
        }

        for (int i = 7; i < 13; i++) {
            boardArray[i] = 4;
        }

    }

    void showBoard() { // prints out the mancala board

        System.out.println("    A B C   D E F    ");
        System.out.println("=====================");

        System.out.print("    " + boardArray[12] + " ");
        System.out.print(boardArray[11] + " ");
        System.out.print(boardArray[10] + " ");
        System.out.print("| ");
        System.out.print(boardArray[9] + " ");
        System.out.print(boardArray[8] + " ");
        System.out.println(boardArray[7] + " ");
        System.out.println(boardArray[13] + " ----------------- " + boardArray[6]);
        System.out.print("    " + boardArray[0] + " ");
        System.out.print(boardArray[1] + " ");
        System.out.print(boardArray[2] + " ");
        System.out.print("| ");
        System.out.print(boardArray[3] + " ");
        System.out.print(boardArray[4] + " ");
        System.out.println(boardArray[5] + " ");

        System.out.println("=====================");
        System.out.println("    A B C   D E F    ");

    }

    void showCommands() {
        System.out.println("Enter A - G to make a move, or q to exit the program.");
    }

    void processCommands() {
        while (true) {

            if (gameOver()) {

                int player2_total = boardArray[13] + boardArray[12] + boardArray[11] + boardArray[10] + boardArray[9] + boardArray[8] + boardArray[7];
                int player1_total = boardArray[0] + boardArray[1] + boardArray[2] + boardArray[3] + boardArray[4] + boardArray[5] + boardArray[6];

                if (player1_total == player2_total) {
                    whoWon = "Draw";
                }
                if (player1_total > player2_total) {
                    whoWon = "Player1";
                }
                if (player1_total < player2_total) {
                    whoWon = "Player2";
                }

                System.out.println(whoWon + " won!");
                System.out.println("Player1 score: " + player1_total);
                System.out.println("Player2 score: " + player2_total);
                break;

            }
            int nextIndex;
            if (!turnAgain) {
                System.out.println(whoseTurn + "'s " + "turn. Enter a move : ");
            }
            if (turnAgain) {
                System.out.println(whoseTurn + "'s " + "turn AGAIN. Enter a move : ");
            }

            turnAgain = false;

            String userInput;

            Scanner scanIn = new Scanner(System.in);
            userInput = scanIn.nextLine();

            if (userInput.equals("q") || userInput.equals("Q")) {
                System.out.println("Exiting now...");
                break;
            }

            else {

                if (!(userInput.equals("a") || userInput.equals("b") || userInput.equals("c")
                        || userInput.equals("d") || userInput.equals("e") || userInput.equals("f"))) {
                    System.out.println("Invalid command. Try again!");
                }

                else {
                    int slotChosen = getCorrespondingPit(userInput,whoseTurn);

                    if (boardArray[slotChosen] == 0) {
                            System.out.println("Error! Slot is empty!");
                            continue;
                    }

                    if (whoseTurn.equals("Player1")) { // if it is player1's turn
                        int currentBeads = boardArray[slotChosen];
                        boardArray[slotChosen] = 0;
                        nextIndex = slotChosen + 1;
                        while (currentBeads > 0) {
                            if (nextIndex == 13) {
                                nextIndex = 0;
                            }
                            if (currentBeads == 1 && nextIndex == 6) { // if it is your mancala..
                                turnAgain = true;
                                whoseTurn = "Player1";
                                boardArray[nextIndex] = boardArray[nextIndex] + 1;
                                break;
                            }
                            if (currentBeads == 1 && boardArray[nextIndex] == 0 && nextIndex <= 5) {
                                int oppositePit = getOppositePit(nextIndex);
                                if (boardArray[oppositePit] > 0) { // if there is at least one bead in the opposite pit..
                                    int totalSum = boardArray[oppositePit] + 1;
                                    boardArray[6] = boardArray[6] + totalSum;
                                    boardArray[oppositePit] = 0;
                                    boardArray[nextIndex] = 0;
                                    break;
                                }
                            }
                            boardArray[nextIndex] = boardArray[nextIndex] + 1;
                            currentBeads = currentBeads - 1;
                            nextIndex = nextIndex + 1;
                        } // end while
                        determineTurn();
                    } // end of if player 1 ..

                    else { // it's player2's turn
                        int currentBeads = boardArray[slotChosen];
                        boardArray[slotChosen] = 0;
                        nextIndex = slotChosen + 1;
                        while (currentBeads > 0) {
                            if (nextIndex == 6) {
                                nextIndex = 7;
                            }
                            if (currentBeads == 1 && nextIndex == 13) { // if it is player 2's mancala
                                turnAgain = true;
                                whoseTurn = "Player2";
                                boardArray[nextIndex] = boardArray[nextIndex] + 1;
                                break;
                            }
                            if (currentBeads == 1 && boardArray[nextIndex] == 0 && (nextIndex <= 12 && nextIndex >=7)) {
                                int oppositePit = getOppositePit(nextIndex);
                                if (boardArray[oppositePit] > 0) {
                                    int totalSum = boardArray[oppositePit] + 1;
                                    boardArray[13] = boardArray[13] + totalSum;
                                    boardArray[oppositePit] = 0;
                                    boardArray[nextIndex] = 0;
                                    break;
                                }
                            }
                            boardArray[nextIndex] = boardArray[nextIndex] + 1;
                            currentBeads = currentBeads - 1;
                            nextIndex = nextIndex + 1;
                            if (nextIndex == 14) {
                                nextIndex = 0;
                            }
                        } // end while
                        determineTurn();
                    } //end of else (if player 2)
                }
            }
            showBoard();
        }
    }
}