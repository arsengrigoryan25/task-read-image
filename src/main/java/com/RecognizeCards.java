package com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

class RecognizeCards {
    public static void main(String[] args) throws IOException {
        File folder = new File(args[0]);
        RecognizeCards main = new RecognizeCards();
        BufferedImage img;
        int[] rowPixels = new int[]{1,7,16,21,23};

        for (final File fileEntry : folder.listFiles()) {
            System.out.print(fileEntry.getName());
            img = ImageIO.read(fileEntry);
            String result = main.cutCards(img, rowPixels);
            System.out.println(" - " + result);
        }
    }

    public String cutCards(BufferedImage img, int[] yArr) {
        int[] results;
        String result = "";

        int x = 151;
        int y = 591;
        int w = 340;
        int h = 80;
        img = img.getSubimage(x, y, w, h);
        results = countPixels(img, yArr[0], yArr[1], yArr[2], yArr[3], yArr[4]);
        result += getCardsNumber(results, img);

        x = 69;
        y = 0;
        w = 270;
        h = 80;
        img = img.getSubimage(x, y, w, h);
        results = countPixels(img, yArr[0], yArr[1], yArr[2], yArr[3], yArr[4]);
        result += getCardsNumber(results, img);

        x = 70;
        y = 0;
        w = 200;
        h = 80;
        img = img.getSubimage(x, y, w, h);
        results = countPixels(img, yArr[0], yArr[1], yArr[2], yArr[3], yArr[4]);
        result += getCardsNumber(results, img);

        x = 70;
        y = 0;
        w = 130;
        h = 80;
        img = img.getSubimage(x, y, w, h);
        results = countPixels(img, yArr[0], yArr[1], yArr[2], yArr[3], yArr[4]);
        result += getCardsNumber(results, img);

        x = 72;
        y = 0;
        w = 58;
        h = 80;
        img = img.getSubimage(x, y, w, h);
        results = countPixels(img, yArr[0], yArr[1], yArr[2], yArr[3], yArr[4]);
        result += getCardsNumber(results, img);
        return result;
    }

    private int[] countPixels(BufferedImage img, int y1, int y2, int y3, int y4, int y5) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int[] results = new int[5];

        Color c;
        int red;
        int green;
        int blue;
        for (int x = 0; x < 10; x++) {
            c = new Color(img.getRGB(x, 25));
            red = c.getRed();
            green = c.getGreen();
            blue = c.getBlue();
            if ((red == 255 && green == 255 && blue == 255) || (red == 120 && green == 120 && blue == 120)) {
                count6++;
            }
        }

        if (count6 < 5) {
            return results;
        }

        for (int x1 = 0; x1 < 30; x1++) {
            if (isRGBPixels(img, x1, y1)) {
                count1++;
            }
            if (isRGBPixels(img, x1, y2)) {
                count2++;
            }
            if (isRGBPixels(img, x1, y3)) {
                count3++;
            }
            if (isRGBPixels(img, x1, y4)) {
                count4++;
            }
            if (isRGBPixels(img, x1, y5)) {
                count5++;
            }
        }
        results[0] = count1;
        results[1] = count2;
        results[2] = count3;
        results[3] = count4;
        results[4] = count5;
        return results;
    }

    private String getCardsNumber(int[] results, BufferedImage img1) {
        String result = "";
        Color c = new Color(img1.getRGB(27, 12));
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();

        if (Arrays.stream(results).sum() != 0) {
            if ((results[0] > 14 && results[0] <= 21 && results[1] > 13 && results[1] <= 17) &&
                    ((red != 255 && green != 255 && blue != 255) || (red != 120 && green != 120 && blue != 120))) {
                result += "10";
                result += getCardsSuit(img1);
                return result;
            }

            if ((results[0] == 15 || results[0] == 14) && results[1] < 8 && results[2] < 8 && results[3] > 8 && results[3] <= 14 && results[4] <= 8) {
                result += "5";
                result += getCardsSuit(img1);
            } else if (results[0] > 15 && results[0] < 20) {
                if (results[1] < 8 && results[3] < 8 && results[4] < 8) {
                    result += "7";
                    result += getCardsSuit(img1);
                } else if (results[3] > 8 && results[3] < 15) {
                    result += "3";
                    result += getCardsSuit(img1);
                }
            }
            if (results[0] >= 9 && results[1] < 9 && results[2] < 10 && results[4] >= 16) {
                result += "2";
                result += getCardsSuit(img1);
            }
            if (results[0] < 8 && results[1] < 8 && results[2] < 8 && results[4] < 8) {
                result += "J";
                result += getCardsSuit(img1);
            }
            if (results[0] < 7 && results[2] > 17 && results[3] < 7 && results[4] < 7) {
                result += "4";
                result += getCardsSuit(img1);
            }
            if (results[0] <= 8 && results[2] >= 10 && results[3] > 6) {
                result += "A";
                result += getCardsSuit(img1);
            }
            if (results[0] > 6 && results[0] < 11 && results[1] < 7 && results[3] > 10 && results[4] < 8) {
                result += "6";
                result += getCardsSuit(img1);
            }
            if (results[1] >= 9 && results[2] < 7 && results[4] < 8) {
                result += "9";
                result += getCardsSuit(img1);
            }
            if (results[0] >= 9 && results[0] < 12 && results[1] >= 9 && results[1] < 12 &&
                    results[2] >= 9 && results[2] <= 13 && results[3] >= 13 && results[3] < 17) {
                result += "8";
                result += getCardsSuit(img1);
            }
            if (results[0] >= 9 && results[0] <= 13 && results[1] >= 9 && results[1] <= 14 && results[2] > 12 && results[3] > 16) {
                result += "Q";
                result += getCardsSuit(img1);
            }
            if (results[0] >= 9 && results[0] <= 12 && results[1] >= 9 && results[1] <= 13 &&
                    results[2] >= 10 && results[3] >= 9 && results[3] <= 12 && results[4] >= 9 && results[4] < 12) {
                result += "K";
                result += getCardsSuit(img1);
            }
        }

        return result;
    }

    private String getCardsSuit(BufferedImage img) {
        String result = "";
        boolean isBlack = false;
        int red;
        int green;
        int blue;
        int count1 = 0;
        int count2 = 0;
        int x = 35;
        int y = 60;

        Color c = new Color(img.getRGB(x, y));
        red = c.getRed();
        green = c.getGreen();
        blue = c.getBlue();
        if ((red == 35 && green == 35 && blue == 38) || (red == 16 && green == 16 && blue == 18)) {
            isBlack = true;
        }

        int y1 = 48;
        int y2 = 54;
        for (int x1 = 17; x1 < 50; x1++) {
            if (isRGBPixels(img, x1, y1)) {
                count1++;
            }
            if (isRGBPixels(img, x1, y2)) {
                count2++;
            }
        }

        if (isBlack) {
            if (count1 >= 14 && count2 <= 22) {
                return "c";
            } else if (count1 <= 11 && count2 >= 22) {
                return "s";
            }
        } else {
            if (count1 <= 14 && count2 >= 26) {
                return "h";
            } else if (count1 >= 3 && count2 <= 27) {
                return "d";
            }
        }

        return result;
    }

    private boolean isRGBPixels(BufferedImage img, int x, int y) {
        boolean result = false;
        Color c = new Color(img.getRGB(x, y));
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        if (!((red == 255 && green == 255 && blue == 255) || (red == 120 && green == 120 && blue == 120))) {
            result = true;
        }
        return result;
    }
}