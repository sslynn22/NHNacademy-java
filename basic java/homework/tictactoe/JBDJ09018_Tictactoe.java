package basic_java.homework.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class JBDJ09018_Tictactoe {
	static String[][] array = new String[3][3];
	static boolean clear = false;
	static String win = "";

	public static void initArray() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				array[i][j] = " ";
			}
		}
	}

	public static void draw() {
		for (int i = 0 ; i < 3 ; i ++) {
			for (int j = 0 ; j < 3 ; j++) {
				System.out.printf(" %s ", array[i][j]);
				if (j!=2) System.out.print("|");
			}
			if(i!=2) System.out.println("\n-----------");
		}
		System.out.println();
	}

	public static void inputUser(String input) {
		try {
			String[] tokens = input.split(" ");

			if (tokens.length != 2) {
				throw new IllegalArgumentException("Invalid input");
			}

			int x = Integer.parseInt(tokens[1]);
			int y = Integer.parseInt(tokens[0]);

			if (x < 0 || x >= 3 || y < 0 || y >= 3) {
				throw new ArrayIndexOutOfBoundsException("Out of Range");
			}

			if (!array[x][y].equals(" ")) {
				throw new IllegalStateException("Position already occupied");
			}

			array[x][y] = "O";

		}
		catch (IllegalArgumentException e) {
			System.out.println("ERROR: Invalid input");
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ERROR: Out of Range");
		}
		catch (IllegalStateException e) {
			System.out.println("ERROR: Position already occupied");
		}
	}

	public static void inputComputer() {
		Random random = new Random();
		while (true) {
			int x = random.nextInt(3);
			int y = random.nextInt(3);
			if (array[x][y].equals(" ")) {
				array[x][y] = "X";
				break;
			}
		}
	}

	public static void end() {
		if (checkWin()) {
			winner();
			return;
		}

		if (isFull()) {
			System.out.println("Draw!");
			clear = true;
		}
	}

	public static boolean checkWin() {
		for (int i = 0; i < 3 ; i++) {
			if (!array[i][0].equals(" ") && array[i][0].equals(array[i][1])&& array[i][1].equals(array[i][2])) {
				win = array[i][0].equals("X") ? "computer" : "user";
				return true;
			}
		}
		for (int i = 0; i < 3 ; i++) {
			if (!array[0][i].equals(" ") && array[0][i].equals(array[1][i]) && array[1][i].equals(array[2][i])) {
				win = array[0][i].equals("X") ? "computer" : "user";
				return true;
			}
		}
		if (array[0][0].equals(array[1][1]) && array[1][1].equals(array[2][2]) && !array[1][1].equals(" ")) {
			win = array[1][1].equals("X") ? "computer" : "user";
			return true;
		}
		if (array[0][2].equals(array[1][1]) && array[1][1].equals(array[2][0]) && !array[1][1].equals(" ")) {
			win = array[1][1].equals("X") ? "computer" : "user";
			return true;
		}
		return false;
	}

	public static boolean isFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (array[i][j].equals(" ")) {
					return false;
				}
			}
		}
		return true;
	}

	public static void winner() {
		if(win.equals("computer")) {
			System.out.println("Computer Win");
			clear = true;
		}
		if(win.equals("user")) {
			System.out.println("You win");
			clear = true;
		}
	}

	public static void main(String[] args) {
		initArray();
		Scanner sc = new Scanner(System.in);

		while(!clear) {
			System.out.println("\n컴퓨터 턴");
			inputComputer();
			draw();
			end();
			if (clear) break;

			System.out.print("\n사용자 턴(x y): ");
			String input = sc.nextLine();
			inputUser(input);
			draw();
			end();
			if (clear) break;
		}
		sc.close();
	}
}