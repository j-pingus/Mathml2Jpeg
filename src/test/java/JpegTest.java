import org.apache.batik.transcoder.TranscoderException;
import util.Svg2Jpeg;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

public class JpegTest {
    public static void main(String args[]) throws IOException, TranscoderException {
        java.awt.GraphicsEnvironment env =
                java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] allFontFamilyNames = env.getAvailableFontFamilyNames();
        System.out.println(Arrays.toString(allFontFamilyNames));
        for (File fixedSvg : new File("src/main/resources/examples").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".svg");
            }
        })) {
            File jpeg = new File("target/" + fixedSvg.getName().replace(".svg", ".fixed.jpeg"));
            Svg2Jpeg.convert(fixedSvg, jpeg);
        }
    }
}
