package com.verenitymc.xutil.util;

/**
 * Created by Mat
 *
 * Easy Roman number parser, just two quick methods, you never know when you want it.
 */
public class RomanUtil {


    public static String getRomanNumber(int level) {
        if(level==1)return "I";
        if(level==2)return "II";
        if(level==3)return "III";
        if(level==4)return "IV";
        if(level==5)return "V";
        if(level==6)return "VI";
        if(level==7)return "VII";
        if(level==8)return "VIII";
        if(level==9)return "IX";
        if(level==10)return "X";
        if(level==11)return "XI";
        if(level==12)return "XII";
        if(level==13)return "XIII";
        if(level==14)return "XIV";
        if(level==15)return "XV";
        if(level==16)return "XVI";
        if(level==17)return "XVII";
        if(level==18)return "XVIII";
        if(level==19)return "XIX";
        if(level==20)return "XX";
        if(level==21)return "XXI";
        if(level==22)return "XXII";
        if(level==23)return "XXIII";
        if(level==24)return "XXIV";
        if(level==25)return "XXV";
        if(level==26)return "XXVI";
        if(level==27)return "XXVII";
        if(level==28)return "XXVIII";
        if(level==29)return "XXIX";
        if(level==30)return "XXX";
        if(level==31)return "XXXI";
        if(level==32)return "XXXII";
        if(level==33)return "XXXIII";
        if(level==34)return "XXXIV";
        if(level==35)return "XXXV";
        if(level==36)return "XXXVI";
        if(level==37)return "XXXVII";
        if(level==38)return "XXXVIII";
        if(level==39)return "XXXIX";
        if(level==40)return "XL";
        if(level==41)return "XLI";
        if(level==42)return "XLII";
        if(level==43)return "XLIII";
        if(level==44)return "XLIV";
        if(level==45)return "XLV";
        if(level==46)return "XLVI";
        if(level==47)return "XLVII";
        if(level==48)return "XLVIII";
        if(level==49)return "XLIX";
        if(level==50)return "L";
        return null;
    }



    public static int parseRomanNumber(String string) {
        if(string.equalsIgnoreCase("I"))return 1;
        if(string.equalsIgnoreCase("II"))return 2;
        if(string.equalsIgnoreCase("III"))return 3;
        if(string.equalsIgnoreCase("IV"))return 4;
        if(string.equalsIgnoreCase("V"))return 5;
        if(string.equalsIgnoreCase("VI"))return 6;
        if(string.equalsIgnoreCase("VII"))return 7;
        if(string.equalsIgnoreCase("VIII"))return 8;
        if(string.equalsIgnoreCase("IX"))return 9;
        if(string.equalsIgnoreCase("X"))return 10;
        if(string.equalsIgnoreCase("XI"))return 11;
        if(string.equalsIgnoreCase("XII"))return 12;
        if(string.equalsIgnoreCase("XIII"))return 13;
        if(string.equalsIgnoreCase("XIV"))return 14;
        if(string.equalsIgnoreCase("XV"))return 15;
        if(string.equalsIgnoreCase("XVI"))return 16;
        if(string.equalsIgnoreCase("XVII"))return 17;
        if(string.equalsIgnoreCase("XVIII"))return 18;
        if(string.equalsIgnoreCase("XIX"))return 19;
        if(string.equalsIgnoreCase("XX"))return 20;
        if(string.equalsIgnoreCase("XXI"))return 21;
        if(string.equalsIgnoreCase("XXII"))return 22;
        if(string.equalsIgnoreCase("XXIII"))return 23;
        if(string.equalsIgnoreCase("XXIV"))return 24;
        if(string.equalsIgnoreCase("XXV"))return 25;
        if(string.equalsIgnoreCase("XXVI"))return 26;
        if(string.equalsIgnoreCase("XXVII"))return 27;
        if(string.equalsIgnoreCase("XXVIII"))return 28;
        if(string.equalsIgnoreCase("XXIX"))return 29;
        if(string.equalsIgnoreCase("XXX"))return 30;
        if(string.equalsIgnoreCase("XXXI"))return 31;
        if(string.equalsIgnoreCase("XXXII"))return 32;
        if(string.equalsIgnoreCase("XXXIII"))return 33;
        if(string.equalsIgnoreCase("XXXIV"))return 34;
        if(string.equalsIgnoreCase("XXXV"))return 35;
        if(string.equalsIgnoreCase("XXXVI"))return 36;
        if(string.equalsIgnoreCase("XXXVII"))return 37;
        if(string.equalsIgnoreCase("XXXVIII"))return 38;
        if(string.equalsIgnoreCase("XXXIX"))return 39;
        if(string.equalsIgnoreCase("XL"))return 40;
        if(string.equalsIgnoreCase("XLI"))return 41;
        if(string.equalsIgnoreCase("XLII"))return 42;
        if(string.equalsIgnoreCase("XLIII"))return 43;
        if(string.equalsIgnoreCase("XLIV"))return 44;
        if(string.equalsIgnoreCase("XLV"))return 45;
        if(string.equalsIgnoreCase("XLVI"))return 46;
        if(string.equalsIgnoreCase("XLVII"))return 47;
        if(string.equalsIgnoreCase("XLVIII"))return 48;
        if(string.equalsIgnoreCase("XLIX"))return 49;
        if(string.equalsIgnoreCase("L"))return 50;
        return 0;
    }
}
