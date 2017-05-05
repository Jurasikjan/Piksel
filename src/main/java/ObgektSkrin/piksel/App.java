package ObgektSkrin.piksel;

/**
 * Created by Юра on 16.03.2017.
 */
//qvikSkrinPicsel
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class App extends Thread{
    public static void main(String[] args)throws IOException {



            double [][] mas= Piksel("spektr.png");






    }
    public static double aDouble(double d,int posleTochki)
    {
        /*
        Вычисляем оттенок
        черный от 0.0>=3.0
        смешеный цвет от 3.0>=6.0
        белый от 6.0>=9.0

        видит только 3 спектра цвета


        */
        String ret="";

        String s=Double.toString(d);
        String[] mas=s.split("[.]+");
        for (int i = 0; i < mas.length; i++) {
            if(i==0) {
                for (int j = 0; j < mas[i].length(); j++) {
                    char[] ch = mas[i].toCharArray();
                    for (int k = 0; k < ch.length; k++) {
                        ret += Character.toString(ch[i])+".";
                    }
                }
            }else {
                    char[] ch = mas[i].toCharArray();
                    for (int k = 0; k < posleTochki; k++) {
                        if (Integer.valueOf(Character.toString(ch[k]))>5) {
                            ret += Character.toString(ch[k]);
                        }else k++;
                    }
            }
        }

        return Double.valueOf(ret);
    }
    public static double [][] Piksel(String png)
    {


        File file;
        BufferedImage image=null;

        try {
            file=new File(png);
            image = ImageIO.read(file);
            System.out.println("Reading");


        }catch (IOException ex){
            System.out.println(ex);
        }

        int w = image.getWidth();
        int h = image.getHeight();
        int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);

        System.out.println(w+" "+h+" lengt"+dataBuffInt.length);
        Color c;// хранит цвет пикселя
        double [][] masSr=new double[w][h];
        int o=0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                c = new Color(dataBuffInt[o]);
                o++;
                masSr[j][i]=aDouble(((c.getRed()+c.getGreen()+c.getBlue())/3)/28.3,1); //пиксель состоит из r=255 g=255 b=255 ((765)/3)среднее число пикселя
                                                                                        // /28.3 определяем оттенок цвета
                                                                                        // черный +
                                                                                        //белый -
                                                                                        // смешеный цвет *

                if (masSr[j][i]>=3.0)
                    if (masSr[j][i]>=6.0)
                        System.out.print("-");
                    else   System.out.print("*");
                else {
                    System.out.print("+");
                }
            }

            System.out.println();
        }

        System.out.println(o);

        return masSr;
    }
}
