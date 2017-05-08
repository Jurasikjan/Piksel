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
import java.util.ArrayList;
import java.util.List;

public class App extends Thread{
    public static void main(String[] args)throws IOException {



           Piksel("prov.png");

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
                    for (int k = 0; k < posleTochki+1; k++) {
                        int pr=Integer.valueOf(Character.toString(ch[k+1]));
                        if ( k==posleTochki && pr>5 && pr!=0) {
                            int o=Integer.valueOf(Character.toString(ch[k]))+1;
                            if(o==10)
                            {
                               char []m=ret.toCharArray();
                                int o1=Integer.valueOf(Character.toString(m[m.length-1]))+1;
                                m[m.length-1]=Integer.toString(o1).charAt(0);

                                ret= String.valueOf(m);

                            }else {
                                ret += String.valueOf(o);
                            }

                        }else {
                            ret += Character.toString(ch[k]);
                        }
                    }
            }
        }

        return Double.valueOf(ret);
    }
    public  static  File toRGBFail(double[][] Piksel)
    {
        File file=null;

        BufferedImage image=new BufferedImage(Piksel.length,Piksel[0].length,1);
        for (int i = 0; i < Piksel.length; i++) {
            for (int j = 0; j < Piksel[i].length; j++) {

               int rgb= (int) Math.round (Piksel[i][j]*28.3);

                Color color=new Color(0,0,0);
            }

        }

        return file;
    }
    public static List<ImageRGB> Piksel(String png)
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
        ImageRGB.h=h;
        ImageRGB.w=w;
        List<ImageRGB> pic=new ArrayList<ImageRGB>();

        int o=0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                    c = new Color(dataBuffInt[o]);
                o++;

                    pic.add(new ImageRGB(j,i,c.getRed(),c.getGreen(),c.getBlue()));

            }
        }
        for (ImageRGB imageRGB : pic) {
            System.out.print(imageRGB.toString());
        }

        System.out.println(o);

        return pic;
    }
}
