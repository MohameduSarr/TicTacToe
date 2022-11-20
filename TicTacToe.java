import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int row, column;
        char player = 'X';


        char[][] board = new char[3][3];
        char ch = '1';
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                board[i][j] = ch++;
            }
        }
        displayBoard(board);
        while(!winner(board) == true){


            System.out.println("Enter a row and column (0, 1, or 2); for player " + player + ":");
            row = in.nextInt();
            column = in.nextInt();


            if (board[row][column] == 'X' || board[row][column] == 'O') {
                System.out.println("This spot is occupied. Please try again");
            }

            board[row][column] = player;
            displayBoard(board);

            if (winner(board)){
                System.out.println("Player " + player + " is the winner!");
            }


            if (player == 'O') {
                player = 'X';

            }
            else {
                player = 'O';
            }
            if (winner(board) == false && !hasFreeSpace(board)) {
                System.out.println("The game is a draw. Please try again.");
            }
        }


        in.close();

    }

    public static void displayBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j == board[i].length - 1) System.out.print(board[i][j]);
                else System.out.print( board[i][j] + " | ");
            }
            System.out.println();
        }
    }


    public static boolean hasFreeSpace(char[][] board){
        for (int i = 0; i< board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 'O' && board[i][j] != 'X') {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean winner(char[][] board){
        return isHorizontalWin(board) || isVerticalWin(board) || isDiagonalWin(board);
    }

    private static boolean isHorizontalWin(char[][] board) {
        for(int row = 0; row < board.length; row++){
            if(isWin(board[row]))
                return true;
        }
        return false;
    }


    private static boolean isWin(char[] lineToProcess) {
        boolean foundWin = true;
        char prevChar = '-';
        for(char character: lineToProcess) {
            if(prevChar == '-')
                prevChar = character;
            if ('O' != character && 'X' != character) {
                foundWin = false;
                break;
            } else if (prevChar != character) {
                foundWin = false;
                break;
            }
        }
        return foundWin;
    }

    private static boolean isVerticalWin(char[][] board) {
        char[] column = null;
        for(int col = 0; col < board[0].length; col++){
            column = new char[board[0].length];
            for(int row = 0; row < column.length; row++){
                column[row] = board[row][col];
            }
            if(isWin(column))
                return true;
        }
        return false;
    }

    private static boolean isDiagonalWin(char[][] board) {

        int row = 0, col = 0;
        int cols = board.length;
        int rows = board[0].length;

        int size = rows < cols ? rows : cols;
        char[] diagonal = new char[size];


        while (row < rows && col < cols) {
            diagonal[col] = board[row][col];

            row++;
            col++;
        }
        if (isWin(diagonal)) {
            return true;
        }


        row = rows - 1;
        col = 0;
        diagonal = new char[size];
        while (row >=0 && col < cols) {
            diagonal[col] = board[row][col];
            row--;
            col++;
        }
        return isWin(diagonal);

    }
}

