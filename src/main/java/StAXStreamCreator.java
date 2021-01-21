
import javanet.staxutils.*;

import java.io.StringWriter;
import java.math.*;
import java.text.*;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StAXStreamCreator {

    @SuppressWarnings("unused")
    private static final String XMLDATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public void createDocument(List<Customer> data, String filename) throws XMLStreamException {

        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        StringWriter sw = new StringWriter();
        XMLStreamWriter w = factory.createXMLStreamWriter(sw);

        IndentingXMLStreamWriter writer = new IndentingXMLStreamWriter(w);
        writer.writeStartDocument();
        writer.writeStartElement("customers");

        for (Customer customer : data) {
            createCustomerElement(writer, customer);

        }

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();

        System.out.println(sw.toString());
    }

    private void createCustomerElement(XMLStreamWriter writer, Customer customer) throws XMLStreamException {
        writer.writeStartElement("customer");
        writer.writeAttribute(Customer.ID, Integer.toString(customer.getId()));

        writeDataElement(writer, customer.getName(), Customer.NAME);
        writeDataElement(writer, Integer.toString(customer.getAge()), Customer.AGE);
        writeDataElement(writer, customer.getName(), Customer.PHONE);
        writeDataElement(writer, customer.getBalance().toString(), Customer.BALANCE);
        writeDataElement(writer, customer.getName(), Customer.ABOUT);
        writeDataElement(writer, Boolean.toString(customer.isActive()), Customer.ACTIVE);

        DateFormat dateFormat = new SimpleDateFormat(XMLDATEFORMAT);
        writeDataElement(writer, dateFormat.format(customer.getJoined()), Customer.JOINED);


        writer.writeEndElement();

    }

    private void writeDataElement(XMLStreamWriter writer, String value, String elementName) throws XMLStreamException {

        writer.writeStartElement(elementName);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }
}
