package utilities;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class XmlUtil {
	public static StringWriter tempString;
	
	public static String marshall(Object jaxbObject,String xmlfilepath) {
		
		tempString = new StringWriter();
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(jaxbObject.getClass());
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	   // jaxbMarshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<Document>xmlns=\"urn:iso:std:iso:20022:tech:xsd:pain.012. 001.03\"</Document>");
	   // jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
	     
	    //Marshal the employees list in console
	  //  jaxbMarshaller.marshal(jaxbObject, System.out);
	     
	    //Marshal the employees list in file
	   jaxbMarshaller.marshal(jaxbObject, new File(xmlfilepath));
	    jaxbMarshaller.marshal(jaxbObject, tempString);
	}catch (JAXBException e) {
		   e.printStackTrace();
	   }
		return tempString.toString();
 }
	
}
