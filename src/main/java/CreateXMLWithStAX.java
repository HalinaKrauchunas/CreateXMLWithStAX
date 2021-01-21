
import java.text.*;
import java.util.List;

import javax.xml.stream.XMLStreamException;

public class CreateXMLWithStAX {

    public static void main(String[] args) throws XMLStreamException, ParseException {

        List<Customer> data = DataProvider.getData(DataProvider.SMALL);

        StAXStreamCreator creator = new StAXStreamCreator();
        creator.createDocument(data, "./output/customers.xml");
    }
}
