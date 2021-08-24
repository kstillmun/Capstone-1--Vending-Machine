package com.techelevator;

import com.techelevator.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private List<Item> itemList = new ArrayList<>();

	Purchase purchase = new Purchase();
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() {

		loadData();

		boolean run= true;

		while (run) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println(choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayPurchaseOptions();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.exit(1);
			}
		}
	}

	public void displayPurchaseOptions() {
		System.out.println("=========================================");
		System.out.println("             Purchasing Menu             ");
		System.out.println("=========================================");

		boolean stay = true;

		while (stay) {
			String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (purchaseChoice.equals("Feed Money")) {
				feedMoney();
			} else if (purchaseChoice.equals("Select Product")) {
				selectProduct();
			} else if (purchaseChoice.equals("Finish Transaction")) {
				finishTransaction();
			}
		}

	}

	public BigDecimal feedMoney() {
		Scanner feeder = new Scanner(System.in);
		System.out.println("Please enter whole dollar amount you'd like to feed into the machine: ");
		String stringMoney = feeder.nextLine();
		BigDecimal deposit = new BigDecimal(stringMoney);
		BigDecimal depositCorrected= deposit.setScale(2, RoundingMode.HALF_UP);
		BigDecimal zero = BigDecimal.ZERO;
		BigDecimal ten= new BigDecimal("10.01");

		if (depositCorrected.compareTo(zero)>0 && depositCorrected.compareTo(ten)<0) {
			this.purchase.feedMoneyPurchase(depositCorrected);
			System.out.println("Current Money Provided: $ "+this.purchase.getBalance());

			//log
			LocalDateTime currentDateTime = LocalDateTime.now();
			DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyy hh:mm a");
			LogWriter logger = new LogWriter ("log.txt");
			logger.writeToFile(currentDateTime.format(targetFormat)+ " FEED MONEY: "+"\\"+depositCorrected+"\\"+purchase.getBalance());

		} else {
			System.out.println("Please enter a valid dollar amount.");
		}

		return this.purchase.getBalance();

	}

	public void selectProduct() {
		displayItems();
		boolean run = true;
		Scanner purchaseScanner = new Scanner(System.in);

		System.out.println("Please provide the slot# that corresponds to the item you'd like to purchase:");
		String selectedSlot = purchaseScanner.nextLine();
		boolean found = false;

		for (Item item : itemList) {
			if (item.getSlot().toLowerCase().equals(selectedSlot)) {
				if (item.getStock() > 0) {
					purchase.addPurchase(item);
					item.dispenseItem();
					item.getNoise();
					System.out.println(purchase.toString(item));
					displayPurchaseOptions();
					found = true;
				} else {
					System.out.println("SOLD OUT. Please select another item.");
					displayPurchaseOptions();
				}
			}
		}

		if (found) {
			System.out.println("Feel free to make another purchase!");
		} else {
			System.out.println("Slot number invalid.");
		}

	}

	public void loadData() {

		File file = new File("vendingmachine.csv");

		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				String[] lineArr = line.split("\\|");

				String slot = lineArr[0];
				String name = lineArr[1];
				String price= lineArr[2];
				String itemType = lineArr[3];

				if (itemType.equals("Candy")) {
					Item candies = new Candy(slot, name, new BigDecimal(price), itemType);
					itemList.add(candies);
				} else if (itemType.equals("Chip")) {
					Item chips = new Chip(slot, name, new BigDecimal(price), itemType);
					itemList.add(chips);
				} else if (itemType.equals("Drink")) {
					Item drinks = new Drink(slot, name, new BigDecimal(price), itemType);
					itemList.add(drinks);
				} else if (itemType.equals("Gum")) {
					Item gums = new Gum(slot, name, new BigDecimal(price), itemType);
					itemList.add(gums);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	}

	public void displayItems() {

		for (Item item : this.itemList) {
			System.out.println("Slot " + item.getSlot() + " " + item.getName() + " $" + item.getPrice() + "| " + item.getStock() + " in stock");//potentially add the item.
		}

	}

	public void finishTransaction(){
		BigDecimal change= purchase.getBalance().setScale(2,RoundingMode.HALF_UP);
		BigDecimal changeCorrected= change.multiply(new BigDecimal("100"));
		int work= changeCorrected.intValue();

		int quartersRemainder = work%25;
		int dimesRemainder= quartersRemainder%10;

		//log
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyy hh:mm a");
		LogWriter logger = new LogWriter ("log.txt");
		logger.writeToFile(currentDateTime.format(targetFormat)+ " GIVE CHANGE: "+"\\"+purchase.getBalance()+"\\ $ 0.00");

		//change calculator
		int qCount= (int)Math.floor(work/25);
		int dCount= (int)Math.floor(quartersRemainder / 10);
		int nCount= (int)Math.floor(dimesRemainder/5);

		if (quartersRemainder==0){
			System.out.println("Thanks for your purchase you will receive: $"+
					purchase.getBalance()+" in "+ qCount+ " quarters.");
			System.exit(1);
		} else if (work%25>0 && quartersRemainder%10==0) {
			System.out.println("Thanks for your purchase you will receive: $" +
					purchase.getBalance() + " in | " +qCount + " quarters |"
					+dCount + " dimes.");
			System.exit(1);
		} else if (quartersRemainder>0 && dimesRemainder>0 && dimesRemainder%5==0){
			System.out.println("Thanks for your purchase you will receive: $" +
					purchase.getBalance() + " in | " + qCount + " quarters |"
					+ dCount + " dimes |"+ nCount + " nickels.");
			System.exit(1);
		}

	}

	public static void main(String[] args) {
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("            VENDO-MATIC 800          ");
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
