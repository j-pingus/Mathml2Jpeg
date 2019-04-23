import net.sourceforge.jeuclid.converter.Converter;
import util.Svg2Jpeg;
import util.XsltHelper;

import java.io.File;
import java.io.FilenameFilter;

public class TransformTest2 {
    public static void main(String args[]) throws Exception {
        Converter converter = Converter.getInstance();
        for (File mathMl : new File("src/main/resources/examples/").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        })) {
            File svg = new File("target", mathMl.getName().replace(".xml", ".jeuclid.svg"));
            File jpeg = new File("target", mathMl.getName().replace(".xml", ".jeuclid.jpeg"));
            converter.convert(mathMl,svg,Converter.TYPE_SVG);
            Svg2Jpeg.convert(svg, jpeg);
        }
    }
}
