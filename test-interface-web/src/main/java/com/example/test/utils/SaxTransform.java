package com.example.test.utils;

import net.sf.saxon.s9api.*;
import org.springframework.core.io.Resource;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

public class SaxTransform {

    private static Processor proc;

    private static HashMap<String, XsltExecutable> xslCache = new HashMap();

    static {
        proc = new Processor(false);
    }

    public static String xslTransform(String strXml, Resource xslFile) {
        Serializer out = null;
        StringWriter writer = new StringWriter(1024);
        XsltTransformer trans = null;
        StringReader xmlReader = null;
        try {
            XsltExecutable exp = xslCache.get(xslFile.getURL().getPath());
            if (exp == null) {
                StreamSource xslStream = new StreamSource(xslFile.getInputStream());
                XsltCompiler comp = proc.newXsltCompiler();
                exp = comp.compile(xslStream);
                xslCache.put(xslFile.getURL().getPath(), exp);
            }
            xmlReader = new StringReader(strXml);
            StreamSource xmlStream = new StreamSource(xmlReader);
            out = proc.newSerializer();
            out.setOutputWriter(writer);
            trans = exp.load();
            trans.setSource(xmlStream);
            trans.setDestination(out);
            trans.transform();
            String result = writer.toString();
            return result;
        } catch (FileNotFoundException sae) {
            xmlFilestreamClose(out, writer, trans, xmlReader);
        } catch (Exception sae) {
            xmlFilestreamClose(out, writer, trans, xmlReader);
        } finally {
            try {
                out.close();
                writer.close();
                trans.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    private static void xmlFilestreamClose(Serializer out, StringWriter writer, XsltTransformer trans, StringReader xmlReader) {
        try {
            xmlReader.close();
            out.close();
            writer.close();
            trans.close();
        } catch (Exception e) {
        }
    }
}