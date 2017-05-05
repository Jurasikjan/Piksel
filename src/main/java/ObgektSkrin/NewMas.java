package ObgektSkrin;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * Created by Юра on 07.03.2017.
 */
public class NewMas {



        public static void main(String[] args) throws IOException {
            BufferedImage bi = null;
            File img=new File("ntst.png");

            try {

                bi = ImageIO.read(img);

            } catch (IOException ex) {
                System.err.println("Неудалось загрузить картинку!");
                ex.printStackTrace();
            }
            ArrayList<Integer[]> arg=new ArrayList<Integer[]>();
            int [] ArgArrrey = null;

//int[] pixel=bi.getRGB(0,1);

            System.out.println(bi.toString());
        }

    }

