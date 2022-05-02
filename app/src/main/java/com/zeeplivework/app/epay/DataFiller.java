package com.zeeplivework.app.epay;

import java.util.ArrayList;

public class DataFiller {
    private static ArrayList<String> dataList;

    public static ArrayList<String> FillCurrencies() {

        (DataFiller.dataList = new ArrayList<String>()).add("IN");
        DataFiller.dataList.add("EUR");
        DataFiller.dataList.add("GBP");
        return DataFiller.dataList;
    }

    public static ArrayList<String> FillPaymentCollection() {
        (DataFiller.dataList = new ArrayList<String>()).add("Customer choice");
        DataFiller.dataList.add("Payment cards");
        DataFiller.dataList.add("Home Banking");
        DataFiller.dataList.add("Invoice");
        DataFiller.dataList.add("Mobile");
        DataFiller.dataList.add("Other");
        return DataFiller.dataList;
    }

    public static ArrayList<String> FillLockPaymentCollection() {
        (DataFiller.dataList = new ArrayList<String>()).add("Disabled");
        DataFiller.dataList.add("Enabled");
        return DataFiller.dataList;
    }

    public static ArrayList<String> FillLanguage() {
        (DataFiller.dataList = new ArrayList<String>()).add("Auto detech");
        DataFiller.dataList.add("Danish");
        DataFiller.dataList.add("English");
        DataFiller.dataList.add("Swedish");
        DataFiller.dataList.add("Norwegian");
        DataFiller.dataList.add("Greenlandic");
        DataFiller.dataList.add("Icelandic");
        DataFiller.dataList.add("German");
        DataFiller.dataList.add("Finnish");
        DataFiller.dataList.add("Spanish");
        DataFiller.dataList.add("French");
        DataFiller.dataList.add("Polish");
        DataFiller.dataList.add("Italian");
        DataFiller.dataList.add("Dutch");
        return DataFiller.dataList;
    }

    public static ArrayList<String> FillSplitPayment() {
        (DataFiller.dataList = new ArrayList<String>()).add("Disabled");
        DataFiller.dataList.add("Enabled");
        return DataFiller.dataList;
    }

    public static ArrayList<String> FillInstantcallback() {
        (DataFiller.dataList = new ArrayList<String>()).add("Asynchrony");
        DataFiller.dataList.add("Instantly");
        return DataFiller.dataList;
    }

    public static ArrayList<String> FillSubscription() {
        (DataFiller.dataList = new ArrayList<String>()).add("Select Subscription");
        DataFiller.dataList.add("Disabled");
        DataFiller.dataList.add("Enabled");
        return DataFiller.dataList;
    }

    public static ArrayList<String> FillPaymentTypes() {
        (DataFiller.dataList = new ArrayList<String>()).add("Select PaymentTypes");
        DataFiller.dataList.add("1");
        DataFiller.dataList.add("2");
        DataFiller.dataList.add("3");
        DataFiller.dataList.add("4");
        DataFiller.dataList.add("5");
        DataFiller.dataList.add("6");
        DataFiller.dataList.add("7");
        DataFiller.dataList.add("8");
        DataFiller.dataList.add("9");
        DataFiller.dataList.add("10");
        DataFiller.dataList.add("11");
        DataFiller.dataList.add("12");
        DataFiller.dataList.add("13");
        DataFiller.dataList.add("14");
        DataFiller.dataList.add("15");
        DataFiller.dataList.add("16");
        DataFiller.dataList.add("17");
        DataFiller.dataList.add("18");
        DataFiller.dataList.add("19");
        DataFiller.dataList.add("20");
        DataFiller.dataList.add("21");
        DataFiller.dataList.add("22");
        DataFiller.dataList.add("23");
        DataFiller.dataList.add("24");
        return DataFiller.dataList;
    }
}