package xee;

import java.io.StringReader;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XEE2 {
  public String getXml(HttpServletRequest request) {
    String xmlStr = request.getParameter("xml");
    String result = unsafe(xmlStr);

    if (result != null) {
      return result;
    }

    return "XML error";
  }

  private String unsafe(String xmlString) {

    try {
      TransformerFactory transformerFactory = SAXTransformerFactory.newInstance();
      try {
        transformerFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      } catch (TransformerConfigurationException ex) {
        throw new RuntimeException(
            "XML Parser Exception occurred, check for possible attack or misconfiguration");
      }
      Transformer transformer = transformerFactory.newTransformer();
      StreamSource source = new StreamSource(new StringReader(xmlString));
      StringWriter writer = new StringWriter();
      StreamResult target = new StreamResult(writer);

      transformer.transform(source, target);

      return writer.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
