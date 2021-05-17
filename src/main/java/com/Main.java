package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.awt.event.*;

class Main extends Component {
    String path = "src/main/resources/imgs_marked/" +
//            "6h4h9sQc.png";
//            "3d4h5s.png";
//    "3c7c2d.png";
//    "6h4h9sQc.png";
//    "2s9sQs.png";
    "6sJsKh.png";
//    "5sQs8h.png";
//    "Ac4cJc.png";
//    "6s3d10c.png";
//    "8h3sKd.png";
    File dir = new File(path);
    BufferedImage img;


    public Main(){
        try {
            img = ImageIO.read(dir);
        } catch (IOException e) { }
    }

    public void paint(Graphics g) {
        long before = System.nanoTime();
        int x = 151;                            // 143 x – the X coordinate of the upper-left corner of the specified rectangular region
        int y = 591;                            // 585 y – the Y coordinate of the upper-left corner of the specified rectangular region
        int w = 340;                            // w – the width of the specified rectangular region
        int h = 80;                             // h – the height of the specified rectangular region
        int y1 = 1;
        int y2 = 7;   // 3 (10-13)
        int y3 = 16;  // 4 (14-17)
        int y4 = 21;  //
        int y5 = 23;  // 2 (23)         23@ taki gitsn e
        int[] results;
        String result = "";
        img = img.getSubimage(x,y,w,h);         // vzyat oblasti v kartinke
        results = countElement1(img, y1, y2, y3, y4, y5);
        result += getCardsNumber(results);
        g.drawImage(img, 0, 0, null);

        x = 69;y = 0;w = 270;h = 80;
        img = img.getSubimage(x,y,w,h);
        results = countElement1(img, y1, y2, y3, y4, y5);
        result += getCardsNumber(results);

        x = 70;y = 0;w = 200;h = 80;
        img = img.getSubimage(x,y,w,h);
        results = countElement1(img, y1, y2, y3, y4, y5);
        result += getCardsNumber(results);

        x = 70;y = 0;w = 130;h = 80;
        img = img.getSubimage(x,y,w,h);
        results = countElement1(img, y1, y2, y3, y4, y5);
        result += getCardsNumber(results);

        x = 72;y = 0;w = 58;h = 80;
        img = img.getSubimage(x,y,w,h);
        results = countElement1(img, y1, y2, y3, y4, y5);
        result += getCardsNumber(results);

        long after = System.nanoTime();
        System.out.println("Time: - " + (double)(after - before)/ 1_000_000_000);
        System.out.println("result: - " + result);
    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }

    public static void main(String[] args) throws IOException {

        JFrame f = new JFrame("Load Image Sample");

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new Main());
        f.pack();
        f.setVisible(true);
    }


    private int[] countElement1(BufferedImage img, int y1, int y2, int y3, int y4, int y5){
        int red;
        int green;
        int blue;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int[] result = new int[5];
        Color c;
        for (int x1 = 0; x1 < 30; x1++) {
            c = new Color(img.getRGB(x1, y1));
            red = c.getRed();
            green = c.getGreen();
            blue = c.getBlue();
            if( !( (red == 255 && green == 255 && blue == 255) ||
                    (red == 120 && green == 120 && blue == 120) )
            ){ count1++; }
//          ------------------------------------------------------------------
            c = new Color(img.getRGB(x1, y2));
            red = c.getRed();
            green = c.getGreen();
            blue = c.getBlue();

            if( !( (red == 255 && green == 255 && blue == 255) ||
                    (red == 120 && green == 120 && blue == 120) )
            ){ count2++; }

            c = new Color(img.getRGB(x1, y3));
            red = c.getRed();
            green = c.getGreen();
            blue = c.getBlue();

            if( !( (red == 255 && green == 255 && blue == 255) ||
                    (red == 120 && green == 120 && blue == 120) )
            ){ count3++; }

            c = new Color(img.getRGB(x1, y4));
            red = c.getRed();
            green = c.getGreen();
            blue = c.getBlue();

            if( !( (red == 255 && green == 255 && blue == 255) ||
                    (red == 120 && green == 120 && blue == 120) )
            ){ count4++; }

            c = new Color(img.getRGB(x1, y5));
            red = c.getRed();
            green = c.getGreen();
            blue = c.getBlue();

            if( !( (red == 255 && green == 255 && blue == 255) ||
                    (red == 120 && green == 120 && blue == 120) )
            ){ count5++; }
        }
        result[0] = count1;
        result[1] = count2;
        result[2] = count3;
        result[3] = count4;
        result[4] = count5;
        System.out.println(count1 + " - " + count2 + " - " + count3 + " - " + count4 + " - " + count5 );
        return result;
    }
    private String getCardsNumber(int[] results){
        String result = "";
        if((results[0] == 15 || results[0] == 14) && results[1] < 8 && results[2] < 8 && results[3] > 8 && results[3] < 14 && results[4] <= 8){
            result += "5";
        } else if(results[0] == 17 || results[0] == 16){
            if(results[1] < 8 && results[3] < 8 && results[4] < 8 ){
                result += "7";
            } else if(results[3] > 8 && results[3] < 15){
                result += "3";
            }
        }
        if(results[0] >=9 && results[1] < 9 && results[2] < 9 && results[4] > 16 ){
            result += "2";
        }
        if(results[0] < 8 && results[1] < 8 && results[2] < 8 && results[4] < 8){
            result += "J";
        }
        if(results[0] < 7 && results[2] > 17  && results[3] < 7 && results[4] < 7){
            result += "4";
        }
        if(results[0] < 7 && results[2] > 17  && results[3] > 7){
            result += "A";
        }
        if(results[0] < 11 && results[1] < 7 && results[3] > 10  && results[4] < 8){
            result += "6";
        }
        if(results[1] >= 9 && results[2] < 7  && results[4] < 8){
            result += "9";
        }
        if(results[0] >= 9 && results[0] < 12 && results[1] >= 9 && results[1] < 12 &&
                results[2] >= 9 && results[2] < 12 && results[3] >= 9 && results[3] < 17){
            result += "8";
        }
        if(results[0] >= 9 && results[0] < 12 && results[1] >= 9 && results[1] < 12 &&
                results[2] >= 9 && results[2] < 12 && results[3] >= 9 && results[3] < 16){
            result += "8";
        }
        if(results[0] >= 9 && results[0] < 12 && results[1] >= 9 && results[1] <= 12 &&
                results[2] > 12 && results[3] > 16 ){
            result += "Q";
        }
        if(results[0] >= 9 && results[0] < 12 && results[1] >= 9 && results[1] < 12 &&
                results[2] >= 12 && results[3] >= 9 && results[3] < 12 && results[4] >= 9 && results[4] < 12){
            result += "K";
        }
        return result;
    }
}

/*
♠️  - s
♥️  - h
♦️  - d
♣️  - c
T  - A
2  -
3  -
4  -
5  - 5
6  -
7  -
8  - 8
9  -
10 -
Q  - Q
J  - J
K  - K

636
1166
 */
//      System.out.println(img.getRGB(x1, y1) + " - " + red + ", " + green + ", " + blue);


//      File dir = new File("src/main/resources/imgs_marked/2c3dAh.png");
//      BufferedImage img;
//      img = ImageIO.read(dir);
//      System.out.println(img.getWidth());                 // razmer kartinki
//      System.out.println(img.getHeight());                // razmer kartinki
//      boolean png = ImageIO.write(img, "png", dir);       // zapis kartinku v file
//      int x = 150;                                        // 143 x – the X coordinate of the upper-left corner of the specified rectangular region
//      int y = 590;                                        // 585 y – the Y coordinate of the upper-left corner of the specified rectangular region
//      int w = 360;                                        // w – the width of the specified rectangular region
//      int h = 80;                                         // h – the height of the specified rectangular region
//      img = img.getSubimage(x,y,w,h);                     // vzyat oblasti v kartinke
//      g.drawImage(img, 0, 0, null);
//      int x1 = 55;
//      int y1 = 5;
//      img.getRGB(x1,y1);                                  // vzyats cvet tochki po koordinate
//      Color c = new Color(img.getRGB(x1,y1));             // rabota s cvetami tochki
//      c.getRed();                                         // rabota s cvetami tochki
//      c.getGreen();                                       // rabota s cvetami tochki
//      c.getBlue();                                        // rabota s cvetami tochki
//      // c.equals(c1);


class Test {
    // File representing the folder that you select using a FileChooser
    static final File dir = new File("PATH_TO_YOUR_DIRECTORY");

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp"  };// and other formats you need

    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };

    public static void main(String[] args) {

        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;

                try {
                    img = ImageIO.read(f);

                    // you probably want something more involved here
                    // to display in your UI
                    System.out.println("image: " + f.getName());
                    System.out.println(" width : " + img.getWidth());
                    System.out.println(" height: " + img.getHeight());
                    System.out.println(" size  : " + f.length());
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
    }
}


class LoadImageApp extends Component {
    private BufferedImage img;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public LoadImageApp() {
        try {
            img = ImageIO.read(new File("src/main/resources/imgs_marked/2c3dAh.png"));
//            img = ImageIO.read(new File("imgs_marked/2c3dAh.png"));
        } catch (IOException e) { }
    }

    public Dimension getPreferredSize() {
        if (img == null) {
            return new Dimension(100,100);
        } else {
            return new Dimension(img.getWidth(null), img.getHeight(null));
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Load Image Sample");

        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new LoadImageApp());
        f.pack();
        f.setVisible(true);
    }
}
class LoadImageApplet extends Applet {
    private BufferedImage img;

    public void init() {
        try {
            URL url = new URL(getCodeBase(), "examples/strawberry.jpg");
            img = ImageIO.read(url);
        } catch (IOException e) {
        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, 50, 50, null);
    }
}