package hu.econsult.is4.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import hu.econsult.is4.model.MessageHeader;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Util {
	
	private static final String PATIENT = "PATIENT";
	
	public static boolean notEmptyString(String s) {
		return s != null && !s.isEmpty();
	}
	
	public static boolean isEmptyString(String s) {
		return s == null || "".equals(s);
	}
	
	public static boolean isValidNEUMessageHeader(MessageHeader header) {
		if(notEmptyString(header.getSessionId())) {
			return true;
		}
		
		if(isEmptyString(header.getCurrentTapass()) 
				|| isEmptyString(header.getCurrentRole())
				|| isEmptyString(header.getLocale())
				|| isEmptyString(header.getDeviceId()) ) {
			return false;
		}
		
		if ((header.getCurrentRole().toUpperCase().contains(PATIENT))
				&& header.getCurrentServicePointId() == null) {
			return true;
		} else if ((header.getCurrentRole().toUpperCase().contains(PATIENT))
				&& header.getCurrentServicePointId() != null) {
			return false;
		}
		
		if( header.getCurrentServicePointId() == null || header.getCurrentServicePointId() <= 0L) {
			return false;
		}
		
		return true;
	}
	
	public static <T> String createXmlAsString(T t) {
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller m = context.createMarshaller();
           
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter writer = new StringWriter();
            m.marshal(t, writer);
            String result = writer.toString();
           
            return result;           
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	@SuppressWarnings("unchecked")
    public static <T> T unmarshallXml(String xmlString, Class<T> clazz) throws JAXBException, Exception {
       
        T t = clazz.getConstructor().newInstance();
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);             
       
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
       
        t = (T) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
       
        return t;
    }
}
