package ObgektSkrin;

import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Created by Юра on 07.03.2017.
 */
public class Test {

 /*   public static void main(String[] args) {



        int[][] pixels = new int[w][h];


        for(int i = 0; i < w; i++ )
            for( int j = 0; j < h; j++ )
                pixels[i][j] = img.getRGB( i, j );




    }*/

public static void ArrPixToString(int [][] result){

    for (int i = 0; i < result.length; i++) {

        System.out.println(result[i][0]);
    }
}


        public static void main(String[] args) throws IOException {
            BufferedImage hugeImage = null;
            File img=new File("ntst.png");

            try {

                hugeImage = ImageIO.read(img);

            } catch (IOException ex) {
                System.err.println("Неудалось загрузить картинку!");
                ex.printStackTrace();
            }

            System.out.println("Testing convertTo2DUsingGetRGB:");
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                int[][] result = convertTo2DUsingGetRGB(hugeImage);
                long endTime = System.nanoTime();
                ArrPixToString(result);
                System.out.println(String.format("%-2d: %s", (i + 1), toString(endTime - startTime)));
            }

            System.out.println("");

            System.out.println("Testing convertTo2DWithoutUsingGetRGB:");
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                int[][] result = convertTo2DWithoutUsingGetRGB(hugeImage);
                long endTime = System.nanoTime();
                System.out.println(String.format("%-2d: %s", (i + 1), toString(endTime - startTime)));
            }
        }

        private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
            int width = image.getWidth();
            int height = image.getHeight();
            int[][] result = new int[height][width];

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    result[row][col] = image.getRGB(col, row);
                }
            }

            return result;
        }

        private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {

            final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
            final int width = image.getWidth();
            final int height = image.getHeight();
            final boolean hasAlphaChannel = image.getAlphaRaster() != null;

            int[][] result = new int[height][width];
            if (hasAlphaChannel) {
                final int pixelLength = 4;
                for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                    int argb = 0;
                    argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                    argb += ((int) pixels[pixel + 1] & 0xff); // blue
                    argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                    argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                    result[row][col] = argb;
                    col++;
                    if (col == width) {
                        col = 0;
                        row++;
                    }
                }
            } else {
                final int pixelLength = 3;
                for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                    int argb = 0;
                    argb += -16777216; // 255 alpha
                    argb += ((int) pixels[pixel] & 0xff); // blue
                    argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                    argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                    result[row][col] = argb;
                    col++;
                    if (col == width) {
                        col = 0;
                        row++;
                    }
                }
            }

            return result;
        }

        private static String toString(long nanoSecs) {
            int minutes    = (int) (nanoSecs / 60000000000.0);
            int seconds    = (int) (nanoSecs / 1000000000.0)  - (minutes * 60);
            int millisecs  = (int) ( ((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);


            if (minutes == 0 && seconds == 0)
                return millisecs + "ms";
            else if (minutes == 0 && millisecs == 0)
                return seconds + "s";
            else if (seconds == 0 && millisecs == 0)
                return minutes + "min";
            else if (minutes == 0)
                return seconds + "s " + millisecs + "ms";
            else if (seconds == 0)
                return minutes + "min " + millisecs + "ms";
            else if (millisecs == 0)
                return minutes + "min " + seconds + "s";

            return minutes + "min " + seconds + "s " + millisecs + "ms";
        }

}
