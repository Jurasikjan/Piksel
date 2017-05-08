package ObgektSkrin.piksel;

/**
 * Created by Юра on 16.03.2017.
 */
//qvikSkrinPicsel
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Thread{
    public static void main(String[] args)throws IOException {



while (true) {


    toRGBFail(Piksel("spektr1.png"), "spektr1.png");
    try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    }

    public  static  File toRGBFail(List<ImageRGB> masRGB,String fail)
    {
        File file=new File(fail);


        BufferedImage image=new BufferedImage(ImageRGB.w,ImageRGB.h,1);
        int o=0;
        for (int i = 0; i < ImageRGB.h; i++) {
            for (int j = 0; j < ImageRGB.w; j++) {


                int r=masRGB.get(o).getR()+(int)(Math.random()*10);
                if(r>255)r=0;
                int g=masRGB.get(o).getG()+(int)(Math.random()*10);
                if(g>255)g=0;
                int b=masRGB.get(o).getB()+(int)(Math.random()*10);
                if(b>255)b=0;
                Color color=new Color(r,g,b);
                o++;
                int rgb=color.getRGB();
                image.setRGB(j,i,rgb);
            }

        }

        try {
            ImageIO.write(image,"png",file);
        } catch (IOException e) {
            e.printStackTrace();
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
