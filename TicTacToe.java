import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TicTacToe {
    static int size = 3;
    static String[][] matrix = new String[size][size];
    static int line;
    static int column;

    static Scanner sc = new Scanner(System.in);

    public static void initializingMatrix(){
        int options = 1;
        for (int i = 0; i < size; i++) {
            for (int y = 0; y < size; y++) {
                matrix[i][y] = String.valueOf(options);
                options++;
            }
        }
    }

    public static void writeMatrix(){
        for (int i = 0; i < size; i++) {
            for (int y = 0; y < size; y++) {
                System.out.print(matrix[i][y] + "\t");
            }
            System.out.println();
        }
    }

    public static void fillMatrix(String value){

        for (int i = 0; i < size; i++) {
            for (int y = 0; y < size; y++) {
                if (i == line && y == column) {
                    matrix[i][y] = value;
                }
            }
        }
        System.out.println();
        writeMatrix();
    }

    public static void indexMatrix(int position){

        switch (position){
            case 1: line = 0; column = 0;
                break;
            case 2: line = 0; column = 1;
                break;
            case 3: line = 0; column = 2;
                break;
            case 4: line = 1; column = 0;
                break;
            case 5: line = 1; column = 1;
                break;
            case 6: line = 1; column = 2;
                break;
            case 7: line = 2; column = 0;
                break;
            case 8: line = 2; column = 1;
                break;
            case 9: line = 2; column = 2;
                break;
            default:
                break;
        }

    }

    public static boolean checkPosition(int position){
        indexMatrix(position);

        String regex = "[0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(matrix[line][column]);

        return matcher.matches();
    }

    public static void checkPlay(int position, int player){

        boolean isValidPosition = checkPosition(position);
        String write = player == 1 ? "X" : "O";

        if (isValidPosition) {
            fillMatrix(write);
        } else {
            boolean isValidInput;
            do {
                System.out.print("Invalid position!\nType a new position: ");
                int newValue = sc.nextInt();
                isValidInput = checkPosition(newValue);
                if (isValidInput){
                    fillMatrix(write);
                }
                System.out.println();
            } while (!isValidInput);
        }
    }

    public static boolean checkWinner(){
        boolean result = false;
        String player1 = "X";
        String player2 = "O";
        int count1Main = 0;
        int count1Secundary = 0;
        int count2Main = 0;
        int count2Secundary = 0;
        for (int i = 0; i < size; i++) {
            // Verifying the lines
            if(matrix[i][0] == matrix[i][1] && matrix[i][0] == matrix[i][2]){
                if (matrix[i][0] == player1) {
                    System.out.println("Player 1 is the winner");
                } else {
                    System.out.println("Player 2 is the winner");
                }
                result = true;
            }
            // Verifying the columns
            if(matrix[0][i] == matrix[1][i] && matrix[0][i] == matrix[2][i]){
                if (matrix[0][i] == player1) {
                    System.out.println("Player 1 is the winner");
                } else {
                    System.out.println("Player 2 is the winner");
                }
                result = true;
            }

            for (int j = 0; j < size; j++) {
                // Main diagonal
                if (i == j) {
                    if(matrix[i][j] == player1) count1Main++;
                    if(matrix[i][j] == player2) count2Main++;
                    if(count1Main == 3 || count2Main == 3) {
                        if(count1Main == 3) System.out.println("Player 1 is the winner");
                        if(count2Main == 3) System.out.println("Player 2 is the winner");
                        result = true;
                    }
                }
                // Secondary diagonal
                if (i+j == size-1) {
                    if(matrix[i][j] == player1) count1Secundary++;
                    if(matrix[i][j] == player2) count2Secundary++;
                    if(count1Secundary == 3 || count2Secundary == 3) {
                        if(count1Secundary == 3) System.out.println("Player 1 is the winner");
                        if(count2Secundary == 3) System.out.println("Player 2 is the winner");
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int input; // X ou O

        System.out.println("Welcome to Tic Tac Toe game!");
        System.out.println("Type the position from 1 through 9\n");

        initializingMatrix();
        writeMatrix();

        // Fill the game
        for (int i = 0; i < 9; i++) {
            int player;
            System.out.print("\nPlayer ");
            if (i % 2 == 0) {
                System.out.print("1: ");
                player = 1;
            } else {
                player = 2;
                System.out.print("2: ");
            }
            input = sc.nextInt();
            checkPlay(input, player);
            if(checkWinner()){
                System.out.println("The game is over!");
                break;
            }
        }

    }

}
