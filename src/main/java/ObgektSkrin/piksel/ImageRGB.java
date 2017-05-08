package ObgektSkrin.piksel;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;

/**
 * Created by Юра on 08.05.2017.
 */
@Data
public class ImageRGB {
    private int x;
    private int y;
    private int r;
    private int g;
    private int b;
    public static int w;
    public static int h;
    private static int col;

    public ImageRGB(int x,int y, int r, int g, int b) {
        this.x =x;
        this.y =y;
        this.r = r;
        this.g = g;
        this.b = b;
        col++;
    }

    public int SredCh(int r,int g,int b)
    {
        if((r+g+b)!=0)
        return (r+g+b)/3;
        return 0;
    }
    @Override
    public String toString() {

        StringBuilder ret=new StringBuilder();
        int sr=SredCh(r,g,b);
        if(sr>=0 && sr<100)  ret.append("+");
        else {
            if(sr>=100 &&sr<200)  ret.append("*");
            else ret.append("-");

        }
        if (x==w-1)
            ret.append("\n");
        return ret.toString();
    }
}
