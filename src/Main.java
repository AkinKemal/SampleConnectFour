import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Operations operations = new Operations();

        int[][] board = new int[6][7];
        //oyun tahtasını sıfırlama
        operations.gameBoardReset(board);

        int column;
        int counter = 0;
        String choiceOfColour, choiceOfColourComputer;
        boolean choiceOfColourControl;
        boolean columnIsFull = true;
        boolean winUser, winComputer = true;

        System.out.println("\nWelcome to Connect Four Game\n");
        operations.design();

        //kullanıcının renk seçimini alma ve kontrol etme
        do {
            System.out.println("(Press the 'R' button for RED)\n(Press the 'Y' button for YELLOW)");
            System.out.println("Please select the color you want to use in the game:");
            choiceOfColour = scanner.next();

            String choiceOfColourRR = "R", choiceOfColourYY = "Y", choiceOfColourRr = "r", choiceOfColourYy = "y";
            choiceOfColourControl = choiceOfColourRR.equals(choiceOfColour) || choiceOfColourYY.equals(choiceOfColour)
                    || choiceOfColourRr.equals(choiceOfColour) || choiceOfColourYy.equals(choiceOfColour);

            if (!choiceOfColourControl) {
                System.out.println("WARNING! Wrong color choice, please try again.");
            }
        } while (!choiceOfColourControl);

        do {
            //oyun tahtası yazdırma
            operations.gameBoardWriting(board);

            //kullanıcının sütun seçimini alma ve kontrol etme
            do {
                System.out.println("(1 to 7)");
                System.out.println("Please choice one of the columns for the movement:");
                column = scanner.nextInt();

                if (column < 1 || column > 7) {
                    System.out.println("WARNING! Wrong column choice, please try again.");
                    operations.gameBoardWriting(board);
                }
                //sütunun doluluğu kontrol etme
                else {
                    columnIsFull = operations.columnIsFull(board, column);
                    if (!columnIsFull) {
                        System.out.println("WARNING! Column is full");
                    }
                }


            } while (column < 1 || column > 7 || !columnIsFull);


            operations.gameBoardMovement(board, column, choiceOfColour);
            winUser = operations.winningControl(board, column, choiceOfColour);

            if (choiceOfColour.equals("R") || choiceOfColour.equals("r")) {
                choiceOfColourComputer = "Y";
            } else {
                choiceOfColourComputer = "R";
            }

            counter++;
            if (winUser) {
                System.out.println("\nCongratulations you WIN the game!");
                operations.gameBoardWriting(board);
                System.out.println("You win the game " + counter + " moves.");
                System.out.println("Thank you for choosing us...");
            }
            //kullanıcı kazandığında bilgisayarın oynamasını engelleme
            else {
                int columnComputer = operations.computer(board, choiceOfColour);
                winComputer = operations.winningControl(board, columnComputer, choiceOfColourComputer);
            }

            if (winComputer) {
                System.out.println("\nCongratulations you LOSE the game!");
                System.out.println("The computer WIN");
                operations.gameBoardWriting(board);
                System.out.println("You lose the game " + counter + " moves.");
                System.out.println("Thank you for choosing us...");
            }

            if (counter == 21) {
                System.out.println("\nGame ended in draw!");
                System.out.println("Thank you for choosing us...");
            }

            operations.design();

        } while (!winUser && !winComputer && counter != 21);
    }
}