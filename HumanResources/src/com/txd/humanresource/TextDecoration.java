package com.txd.humanresource;

/**
 * Text Decoration for print Table
 *
 * @version 1.0 18 Oct 2021
 * @author Tran Xuan Dien
 */
public interface TextDecoration {
    String PRINT_FORMAT = "| %6s | %-25s | %4s | %11s | %12s | %-15s | %9s | %-18s |\n";
    int[] COLUMN_WIDTH = {6,25,4,11,12,15,9,18};
    String PRINT_FORMAT_SALARY = "| %6s | %-25s | %11s | %-18s | %-18s |\n";
    int[] COLUMN_WIDTH_SALARY = {6,25,11,18,18};

    // Make Text Center for good display
    static String centerText(String text, int maxLength) {
        String result = text;
        int lenText = maxLength - text.length();

        for (int i=1; i <= lenText/2; i++) {
            result = result + " ";
        }
        for (int i=1; i <= lenText/2+lenText%2; i++) {
            result = " " + result;
        }

        return result;
    }
}
