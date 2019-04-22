import util.Svg2Jpeg;
import util.XsltHelper;

import java.io.File;
import java.io.FilenameFilter;

public class TransformTest {
    public static void main(String args[]) throws Exception {
        for (File mathMl : new File("src/main/resources/examples/").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        })) {
            File svg = new File("target", mathMl.getName().replace(".xml", ".svg"));
            File jpeg = new File("target", mathMl.getName().replace(".xml", ".jpeg"));
            XsltHelper.apply(new File("src/main/resources/XSLT2/pmml2svg.xsl"),
                    mathMl,
                    svg);
            Svg2Jpeg.convert(svg, jpeg);
        }
    }
}
