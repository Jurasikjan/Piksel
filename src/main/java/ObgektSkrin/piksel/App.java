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



        File file=null;
        BufferedImage image=null;

int widht=963;
        int height=640;
int[] mas=new int[10];
        try {
            file=new File("prov.png");
            image = ImageIO.read(file);
            System.out.println("Reading");


        }catch (IOException ex){
            System.out.println(ex);
        }
/*
try {
    file=new File("E:/pixl.jpg");
    ImageIO.write(image,"jpg",file);
    System.out.println("write");
}catch (IOException ex)
{
    System.out.println(ex);
}
*/
        ///////////
        int w = image.getWidth();
        int h = image.getHeight();
        int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);

        System.out.println(w+" "+h+" lengt"+dataBuffInt.length);
        Color c;

        double [][] masSr=new double[w][h];
        int o=0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                c = new Color(dataBuffInt[o]);
               // System.out.print(c.getAlpha());
                o++;
                masSr[j][i]=aDouble(((c.getRed()+c.getGreen()+c.getBlue())/3)/28.3,1);

               /* try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

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

    }
    public static double aDouble(double d,int posleTochki)
    {
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
}
