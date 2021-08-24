package com.techelevator.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Purchase {


    private List<Item> purchases = new ArrayList<Item>();
    private BigDecimal balance= new BigDecimal("0.00");

    public BigDecimal getBalance() {

        return balance;
    }

    //feed money method that updates balance here
    public void feedMoneyPurchase(BigDecimal deposit) {

        balance = balance.add(deposit);

    }

    //method for adding purchase which will update the balance
    public void addPurchase(Item choice) {// made need to update the total price elsewhere?

        //add purchase to the list
        purchases.add(choice);

        //update the remaining money
        BigDecimal purchasePrice = choice.getPrice();
        balance = balance.subtract(purchasePrice);

        //add to log
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM/dd/yyy hh:mm a");
        LogWriter logger = new LogWriter ("log.txt");
        logger.writeToFile(currentDateTime.format(targetFormat)+ " " + choice.getName() + " "
                + choice.getSlot()+ "\\ $ " + balance.add(purchasePrice) + "\\ $ " + balance);

    }

    public String toString(Item choice) {
        String str= "------------------------------\n";
        for (Item purchases: this.purchases){
            str += "Your order:"+ purchases.getName()+"\n";
            str += "------------------------------\n";
            str += "Total Price: $"+ purchases.getPrice()+ "\n";
            str += "------------------------------\n";
        }

        str += "Money Remaining: $" + balance;

        return str;
    }
}
