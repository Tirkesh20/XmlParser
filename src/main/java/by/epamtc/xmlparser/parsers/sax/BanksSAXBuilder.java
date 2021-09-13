package by.epamtc.xmlparser.parsers.sax;

import by.epamtc.xmlparser.exceptions.ParseException;
import by.epamtc.xmlparser.parsers.AbstractBanksBuilder;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class BanksSAXBuilder extends AbstractBanksBuilder {

    private BankHandler bankHandler;
    private SAXParserFactory parserFactory;
    private XMLReader reader;

    public BanksSAXBuilder() throws ParseException {
        bankHandler=new BankHandler();
        try {
            parserFactory=SAXParserFactory.newInstance();
            parserFactory.setNamespaceAware(true);
            reader=parserFactory.newSAXParser().getXMLReader();
            reader.setContentHandler((ContentHandler) bankHandler);
        } catch (ParserConfigurationException | SAXException e) {
            throw new ParseException(e.getMessage(),e);
        }
    }

    @Override
    public void buildBankSet(InputStream content) throws ParseException {
        if (content==null)
            throw new ParseException(NULL_EXCEPTION);
        try {
            reader.parse(new InputSource(content));
        } catch (IOException | SAXException e) {
            throw new ParseException(e.getMessage());
        }
        banks=bankHandler.getBanks();
    }
}
