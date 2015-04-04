package at.fhooe.mc.it.xml;


import at.fhooe.mc.it.data.Entry;
import at.fhooe.mc.it.data.SmsData;
import at.fhooe.mc.it.data.WordEntry;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Thomas Jetzinger on 04/04/2015.
 */
public class StreamParser {
    /**
     * Reads the XML data and constructs an ordered entry hash map
     * @param _path
     * @param _smsData
     * @return
     */
    public static boolean parse(final String _path, SmsData _smsData) {

        final HashMap<String, Entry> words = new HashMap<>();

        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = null;

            in = new FileInputStream(_path);

            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            XMLEvent event;
            String line;

            while (eventReader.hasNext()) {
                event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement valueElement = event.asStartElement();

                    if(valueElement.getName().getLocalPart().equals("text")) {
                        event = eventReader.nextEvent();

                        line = event.asCharacters().getData();

                        parseLine(line, _smsData);
                    }
                }

            }


        } catch (Exception _ex) {
            _ex.printStackTrace();
            return false;
        }

        orderWords(_smsData);

        return true;
    }

    /**
     * Parse a text element from the sms data
     * @param _line Line without tags
     * @param _smsData
     */
    private static void parseLine(String _line, SmsData _smsData) {
        if(_line != null && _line.length() > 0) {
            // remove numbers and point, convert to lower case
            _line = _line.replaceAll("[\\d.]", " ").toLowerCase();

            // omit empty space
            String[] words = _line.split("\\s+");

            for(int i = 0; i < words.length - 1; i++) {
                _smsData.addWord(words[i], words[i+1]);
            }
        }
    }

    /**
     * Orders the word
     * @param _smsData
     */
    private static void orderWords(SmsData _smsData) {
        _smsData.getWords().values().parallelStream().forEach(
                entry -> entry.order()
        );
    }
}