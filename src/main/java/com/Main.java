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

/*
♠️  - s
♥️  - h
♦️  - d
♣️  - c
1  -
2  -
3  -
4  -
5  -
6  -
7  -
8  -
9  -
10 -
Q  - Q
J  - J
K  - K
T  - A
 */
class Main extends Component {
    File dir = new File("src/main/resources/imgs_marked/2c3dAh.png");
    BufferedImage img;

    public Main(){
        try {
            img = ImageIO.read(dir);
            System.out.println(img.getWidth());                                 // razmer kartinki
            System.out.println(img.getHeight());                                // razmer kartinki
//            boolean png = ImageIO.write(img, "png", dir);             // zapis kartinku v file
        } catch (IOException e) { }
    }

    public void paint(Graphics g) {
        //636;
        //1166;
        int x = 300;    // x – the X coordinate of the upper-left corner of the specified rectangular region
        int y = 100;    // y – the Y coordinate of the upper-left corner of the specified rectangular region
        int w = 300;    // w – the width of the specified rectangular region
        int h = 300;    // h – the height of the specified rectangular region
        img = img.getSubimage(x,y,w,h);      // vzyat oblasti v kartinke
//        img.getRGB(x,y);                                    // vzyats cvet tochki po koordinate
        g.drawImage(img, 0, 0, null);

//        Color c = new Color(img.getRGB(x,y));               // rabota s cvetami tochki
//        c.getRed();                                         // rabota s cvetami tochki
//        c.getGreen();                                       // rabota s cvetami tochki
//        c.getBlue();                                        // rabota s cvetami tochki
//        // c.equals(c1);
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
}


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




class Test2{
    public static void main(String[] args) throws IOException {
        // read image from folder
        File folderInput = new File("/tmp/duke.png");
        BufferedImage folderImage = ImageIO.read(folderInput);

        // read image from url
        URL urlInput = new URL("https://memorynotfound.com/wp-content/uploads/java-duke.png");
        BufferedImage urlImage = ImageIO.read(urlInput);

        // read image from class-path
        File classPathInput = new File(Test2.class.getResource("duke.png").getFile());
        BufferedImage classpathImage = ImageIO.read(classPathInput);

        // read image from input stream
        InputStream isInput = new FileInputStream("/tmp/duke.png");
        BufferedImage inputStreamImage = ImageIO.read(isInput);
    }
}