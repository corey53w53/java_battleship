import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner Reader = new Scanner(System.in);
        clear();
        System.out.println("Welcome to Battleship!");
        //initialize player one board
        int[][] player1Coords = new int[5][2];
        for (int i = 1; i<=5; i++){
            System.out.println("Player 1\nCoordinates of ship (numbers 1-5 separated by space)\n#" + i + ": ");
            int[] input = getCoords();
            boolean valid=true;
            if (input[0]>5||input[0]<1||input[1]>5||input[1]<1){
                valid=false;
                System.out.println("\nInvalid number, try again\n");
                i--;
            }
            for (int j = 0; j<5; j++){
                valid=true;
                if (player1Coords[j][0]==input[0] && player1Coords[j][1]==input[1]){
                    valid=false;
                    System.out.println("You already have a ship there.");
                    i--;
                    break;
                }
            }
            if (valid){
                player1Coords[i-1][0] = input[0];
                player1Coords[i-1][1] = input[1];
            }
        }
        Character[][] board1 = new Character[5][5];
        fillBoard(board1,'-');
        for (int[] arr : player1Coords){
            board1[arr[0]-1][arr[1]-1] = '@';
        }
        System.out.println("\nHere is your board: ");
        printArr(board1);
        System.out.println("Press enter to continue");
        Reader.nextLine();
        clear();
        //end player one board

        //initialize player two board
        int[][] player2Coords = new int[5][2];
        for (int i = 1; i<=5; i++){
            System.out.println("Player 2\nCoordinates of ship (numbers 1-5 separated by space)\n#" + i + ": ");
            int[] input = getCoords();
            boolean valid=true;
            if (input[0]>5||input[0]<1||input[1]>5||input[1]<1){
                valid=false;
                System.out.println("\nInvalid number, try again\n");
                i--;
            }
            for (int j = 0; j<5; j++){
                valid=true;
                if (player2Coords[j][0]==input[0] && player2Coords[j][1]==input[1]){
                    valid=false;
                    System.out.println("You already have a ship there.");
                    i--;
                    break;
                }
            }
            if (valid){
                player2Coords[i-1][0] = input[0];
                player2Coords[i-1][1] = input[1];
            }
        }
        Character[][] board2 = new Character[5][5];
        fillBoard(board2,'-');
        for (int[] arr : player2Coords){
            board2[arr[0]-1][arr[1]-1] = '@';
        }
        System.out.println("\nHere is your board: ");
        printArr(board2);
        System.out.println("Press enter to continue");
        Reader.nextLine();
        clear();

        //end player two board

        Character[][] history1 = new Character[5][5];
        fillBoard(history1,'-');
        Character[][] history2 = new Character[5][5];
        fillBoard(history2,'-');

        int won = 0;
        int player1hits=0;
        int player2hits=0;
        boolean valid = true;
        int[] input = new int[2];
        while (true){
            //Player one turn start
            do{
                valid = true;
                System.out.println("Player 1\nEnter coordinates to attack");
                input = getCoords();
                if (input[0]>5||input[0]<1||input[1]>5||input[1]<1){
                    System.out.println("\nInvalid number, try again\n");
                    valid=false;
                }
                else if (history1[input[0]-1][input[1]-1]=='X'){
                    System.out.println("\nYou already hit there\n");
                    valid=false;
                }
                else if (history1[input[0]-1][input[1]-1]=='O'){
                    System.out.println("\nYou already missed there\n");
                    valid=false;
                }
            }
            while(valid==false);
            if (board2[input[0]-1][input[1]-1]=='@'){
                System.out.println("you hit!");
                history1[input[0]-1][input[1]-1]='X';
                player1hits+=1;
                if (player1hits==5){
                    break;
                }
            }
            else{
                System.out.println("you missed");
                history1[input[0]-1][input[1]-1]='O';
            }
            printArr(history1);
            System.out.println("Press enter to continue");
            Reader.nextLine();
            //Player two turn start
            clear();
            do{
                valid = true;
                System.out.println("Player 2\nEnter coordinates to attack");
                input = getCoords();
                if (input[0]>5||input[0]<1||input[1]>5||input[1]<1){
                    System.out.println("\nInvalid number, try again\n");
                    valid=false;
                }
                else if (history2[input[0]-1][input[1]-1]=='X'){
                    System.out.println("\nYou already hit there\n");
                    valid=false;
                }
                else if (history2[input[0]-1][input[1]-1]=='O'){
                    System.out.println("\nYou already missed there\n");
                    valid=false;
                }
            }
            while(valid==false);
            if (board1[input[0]-1][input[1]-1]=='@'){
                System.out.println("you hit!");
                history2[input[0]-1][input[1]-1]='X';
                player2hits+=1;
                if (player2hits==5){
                    break;
                }
            }
            else{
                System.out.println("you missed");
                history2[input[0]-1][input[1]-1]='O';
            }
            printArr(history2);
            System.out.println("Press enter to continue");
            Reader.nextLine();
            clear();
        }

        if (player1hits==5){
            System.out.println("Player One Wins!");
            printArr(history1);
        } else {
            System.out.println("Player Two Wins!");
            printArr(history2);
        }
    }

    public static int[] getCoords(){
        Scanner Reader = new Scanner(System.in);
        String input = Reader.nextLine();
        int x=Character.getNumericValue(input.charAt(0));
        int y=Character.getNumericValue(input.charAt(2));
        int[] result= new int[2];
        result[0]=x;
        result[1]=y;
        return result;
    }
    public static void clear(){
        for (int i=0; i<100; i++){
            System.out.println("\n");
        }
    }
    public static void printArr(Character[][] arr){
        System.out.println("  1 2 3 4 5");
        for (int i=0; i<arr.length;i++){
            System.out.print(i+1 + " ");
            for (int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public static void fillBoard(Character[][] arr,char c){
        for (int i=0; i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                arr[i][j]=c;
            }
        }
    }
}