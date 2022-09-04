import java.util.Random;

public class Operations {

    int counter = 0;

    public Operations() {

    }

    //oyun tahtasını sıfırlama
    public void gameBoardReset(int[][] board) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = 0;
            }
        }
    }

    //oyun tahtasını yazdırma
    public void gameBoardWriting(int[][] board) {
        System.out.println();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 0) {
                    System.out.print("N ");
                } else if (board[i][j] == 1) {
                    System.out.print("R ");
                } else if (board[i][j] == 2) {
                    System.out.print("Y ");
                } else {
                    System.out.print("W ");
                }
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    //kullanıcı için oyun tahtasında hareket
    public void gameBoardMovement(int[][] board, int column, String choiceOfColour) {
        for (int i = 5; i >= 0; i--) {
            if (board[i][column - 1] == 0) {
                if (choiceOfColour.equals("R") || choiceOfColour.equals("r")) {
                    board[i][column - 1] = 1;
                } else {
                    board[i][column - 1] = 2;
                }
                return;
            }
        }
    }

    //sütunun doluluğu kontrol etme
    public boolean columnIsFull(int[][] board, int column) {

        int counterFind = 0;
        for (int i = 5; i >= 0; i--) {
            if (board[i][column - 1] == 1 || board[i][column - 1] == 2) {
                counterFind++;
            }
        }
        counter = counterFind;
        return counterFind < 6;
    }

    //bilgisayar için oyun tahtasında hareket
    public int computer(int[][] board, String choiceOfColour) {
        Random random = new Random();

        int randomNumber;
        boolean randomNumberControl;

        do {
            randomNumber = random.nextInt(7) + 1;
            randomNumberControl = columnIsFull(board, randomNumber);

            if (randomNumberControl) {
                for (int i = 5; i >= 0; i--) {
                    if (board[i][randomNumber - 1] == 0) {
                        if (!choiceOfColour.equals("R") && !choiceOfColour.equals("r")) {
                            board[i][randomNumber - 1] = 1;
                        } else {
                            board[i][randomNumber - 1] = 2;
                        }
                        break;
                    }
                }
            }

        } while (!randomNumberControl);
        return randomNumber;
    }

    public boolean winningControl(int[][] board, int column, String choiceOfColour) {

        //renkleri sayıya dönüştürme
        int numericalValueOfTheColor;
        if (choiceOfColour.equals("R") || choiceOfColour.equals("r")) {
            numericalValueOfTheColor = 1;
        } else {
            numericalValueOfTheColor = 2;
        }

        //sütun kontrol
        int winningNumber = 0;
        for (int i = 5 - counter; i <= 5; i++) {
            if (board[i][(column - 1)] == numericalValueOfTheColor) {
                winningNumber++;
            } else {
                break;
            }
        }
        winningNumber++;

        //satır kontrol
        int winningNumberRow = 0;
        for (int i = column - 1; i <= 6; i++) {
            if (board[5 - counter][i] == numericalValueOfTheColor) {
                winningNumberRow++;
            } else {
                break;
            }
        }
        for (int i = column - 1; i >= 0; i--) {
            if (board[5 - counter][i] == numericalValueOfTheColor) {
                winningNumberRow++;
            } else {
                break;
            }
        }

        //çapraz a kontrol
        int winningNumberA = 0;
        int columnNegativeA = column - 1;
        int columnPositiveA = column - 1;
        for (int i = 5 - counter; i <= 5; i++) {
            if (board[i][(columnNegativeA)] == numericalValueOfTheColor) {
                winningNumberA++;
                columnNegativeA--;
            } else {
                break;
            }
            if (columnNegativeA < 0) {
                break;
            }
        }
        for (int i = 5 - counter; i >= 0; i--) {
            if (board[i][(columnPositiveA)] == numericalValueOfTheColor) {
                winningNumberA++;
                columnPositiveA++;
            } else {
                break;
            }
            if (columnPositiveA >= 7) {
                break;
            }
        }

        //çapraz b kontrol
        int winningNumberB = 0;
        int columnNegativeB = column - 1;
        int columnPositiveB = column - 1;
        for (int i = 5 - counter; i <= 5; i++) {
            if (board[i][(columnNegativeB)] == numericalValueOfTheColor) {
                winningNumberB++;
                columnNegativeB++;
            } else {
                break;
            }
            if (columnNegativeB >= 7) {
                break;
            }
        }
        for (int i = 5 - counter; i >= 0; i--) {
            if (board[i][(columnPositiveB)] == numericalValueOfTheColor) {
                winningNumberB++;
                columnPositiveB--;
            } else {
                break;
            }
            if (columnPositiveB < 0) {
                break;
            }
        }

        return winningNumber == 5 || winningNumberA == 5 || winningNumberB == 5 || winningNumberRow == 5;
    }

    public void design() {
        System.out.println("-------------------------------------------------------------------------------------------");
    }
}