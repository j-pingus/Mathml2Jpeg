package util;


import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class XsltHelper {


    public static void apply(final File xsltSheet, final File xmlFile, final File outFile) throws IOException, TransformerException {
        apply(xsltSheet, xmlFile, outFile, new HashMap<String, Object>());
    }

    public static void apply(final File xsltSheet, final File xmlFile, final File outFile, final Map<String, Object> parameters) throws IOException, TransformerException {
        final TransformerFactory factory = TransformerFactory.newInstance();
        final File xsltPath = xsltSheet.getParentFile();
        factory.setURIResolver(new URIResolver() {
            public Source resolve(String href, String base) throws TransformerException {
                System.out.println(href);
                File xsltCandidate = new File(xsltPath, href);
                if (xsltCandidate.exists())
                    try {
                        return new StreamSource(new FileInputStream(xsltCandidate));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                return null;
            }
        });
        final Transformer xslt = factory.newTransformer(new StreamSource(new FileInputStream(xsltSheet)));
        final Set<Entry<String, Object>> entries = parameters.entrySet();
        for (final Entry<String, Object> entry : entries) {
            xslt.setParameter(entry.getKey(), entry.getValue());
        }


        final StreamResult result = new StreamResult(new FileOutputStream(outFile));
        final StreamSource source = new StreamSource(new FileInputStream(xmlFile));
        xslt.transform(source, result);
    }
}
