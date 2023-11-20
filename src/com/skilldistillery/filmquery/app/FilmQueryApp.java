package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		app.startUserInterface();
		app.launch();
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);
		System.out.println("Please select:  1) If you would like to look up a film by its identification number.\n"
				+ "\t\t2) If you would like to do a keyword search.\n"
				+ "\t\t3) If you would like to exit the application.");
		int userChoice = input.nextInt();

		switch (userChoice) {
		case 1:
			System.out.println("You have selected to search for a film via its Identification Number.");
			System.out.println("Please enter the films Id Number.");
			int id = input.nextInt();
			input.nextLine();
			db.findFilmById(id);
			break;
		case 2:
			System.out.println("You have choosen to enter a keyword for your search query.");
			System.out.println("Please enter the keyword you wish to search by.");
			String keyword = input.next();
			input.nextLine();
			db.findFilmByKeyword(keyword);
			break;
		case 3:
			System.out.println(
					"You have choosen to leave. \nWe are sorry to see you go. \nPlease come back soon before they shut us down....");
			System.exit(userChoice);
		default:
			System.out.println("You have choosen.....incorrectly. Try again by entering 1....2....or.........3");
			launch();
			break;
		}
		continueOrExit(input);

		input.close();
	}

	public void continueOrExit(Scanner scanner) throws SQLException {
		System.out.println(
				"\nPlease read the following prompts carefully, as our menu is likely to have changed.....again\n");
		System.out.println("Please select one of the following options:\n"
				+ "1) If you would like to look up another film by id or keyword.\n"
				+ "2) If you would like to exit.\n");

		int userChoice2 = scanner.nextInt();
		switch (userChoice2) {
		case 1:
			System.out.println("You have choosen to make another selection! HUZZAH WE'RE STILL IN BUSINESS!!");
			launch();
			break;
		case 2:
			System.out.println(
					"\nYou have choosen to leave. \n" 
					+ "We are sorry to see you go." 
					+ "\nPlease come back soon before they shut us down....\n");
			System.exit(userChoice2);
		}
	}

	private void startUserInterface() {
		System.out.println("\tWelcome to the last BlockedBuster Search Engine still running!");
		System.out.println("Please read the following prompts carefully, as our menu is likely to have changed.\n");
	}

}
