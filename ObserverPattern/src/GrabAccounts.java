/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  ObserverPattern.java
 * Date: April 17, 2016
 */
import java.util.Scanner;

public class GrabAccounts {

	public static void main(String[] args) {
		// creates a new scanner
		Scanner scanny = new Scanner(System.in);
		// creates variables
		int choice = 0;
		// new notifier calls the notifiers constructor
		Notifier notify = new Notifier();
		while (choice != 5) {
			// go until choice = 5
			System.out.println("Main Menu\n" + "1. Create Account \n" + "2. Toggle SleepMode on an account. \n" + // (Should
																													// ask
																													// user
																													// for
																													// which
																													// account
																													// to
																													// toggle)
					"3. Edit Youtube Account Info.\n" + // (Input information on
														// YouTube Account
														// statistics)
					"4. Delete Account\n" + // (Should ask for which account to
											// delete)
					"5. Exit");
			choice = scanny.nextInt();

			// based on choice do different things as stated above ^
			switch (choice) {
			case 1:
				// creates an account (which prints inside the account owner constructor)
				AccountOwner ao = new AccountOwner(notify);
				// registers the account and notifies
				notify.register(ao);
				notify.notifyObserver();
				break;

			case 2:
				System.out.println("Which account would you like to trigger sleep mode on?");
				// scans in desired account to sleep and triggers sleep mode
				int num = scanny.nextInt();
				notify.triggerSleepMode(num);
				break;

			case 3:
				// prints, updates count, and notifies observer
				System.out.println(
						"Enter how many Subscribers, then enter new View Count, then enter the new Video Count.");
				notify.setSubCount(scanny.nextInt());
				notify.setViewCount(scanny.nextInt());
				notify.setVidCount(scanny.nextInt());
				notify.notifyObserver();
				
				break;

			case 4:
				// prints, scans in account, and unregisters it
				System.out.println("Which account would you like to unregister?");
				int acc = scanny.nextInt();
				notify.unregister(acc);
				break;

			case 5:
				break;

			}

		}
	}

}
