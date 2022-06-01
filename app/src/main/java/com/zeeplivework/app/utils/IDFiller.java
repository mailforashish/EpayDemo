package com.zeeplivework.app.utils;

import java.util.ArrayList;

public class IDFiller {
    private static ArrayList<String> dataList;

    public static ArrayList<String> FillIdTypeBangladesh() {
        (IDFiller.dataList = new ArrayList<String>()).add("Foreign Passport");
        IDFiller.dataList.add("National ID");
        IDFiller.dataList.add("Identity number");
        return IDFiller.dataList;
    }

    public static ArrayList<String> FillIdTypeVietnam() {
        (IDFiller.dataList = new ArrayList<String>()).add("Foreign Passport");
        IDFiller.dataList.add("National ID");
        IDFiller.dataList.add("Identity number");
        return IDFiller.dataList;
    }

    public static ArrayList<String> FillIdTypeIndonesia() {
        (IDFiller.dataList = new ArrayList<String>()).add("Foreign Passport");
        IDFiller.dataList.add("National ID");
        IDFiller.dataList.add("Identity number");
        return IDFiller.dataList;
    }

    public static ArrayList<String> FillIdTypeBrazil() {
        (IDFiller.dataList = new ArrayList<String>()).add("Tax Payer Identification Card/Number");
        return IDFiller.dataList;
    }

    public static ArrayList<String> FillIdTypeMalaysia() {
        (IDFiller.dataList = new ArrayList<String>()).add("Foreign Passport");
        IDFiller.dataList.add("National ID");
        IDFiller.dataList.add("Identity number");
        return IDFiller.dataList;
    }

    public static ArrayList<String> FillIdTypeColombia() {
        (IDFiller.dataList = new ArrayList<String>()).add("Passport");
        IDFiller.dataList.add("National ID");
        IDFiller.dataList.add("Driving License");
        IDFiller.dataList.add("Social Security Card");
        IDFiller.dataList.add("Tax Payer Identification Card/Number");
        IDFiller.dataList.add("Birth Certificate");
        return IDFiller.dataList;
    }

    public static ArrayList<String> FillIdTypePhilippines() {
        (IDFiller.dataList = new ArrayList<String>()).add("Foreign Passport");
        IDFiller.dataList.add("National ID");
        IDFiller.dataList.add("Identity number");
        return IDFiller.dataList;
    }

    public static ArrayList<String> FillAccountType() {
        (IDFiller.dataList = new ArrayList<String>()).add("SAVING");
        IDFiller.dataList.add("CHECKING");
        return IDFiller.dataList;
    }

}