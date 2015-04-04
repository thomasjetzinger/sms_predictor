package at.fhooe.mc.it;

import at.fhooe.mc.it.data.SmsData;
import at.fhooe.mc.it.xml.StreamParser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Thomas Jetzinger on 04/04/2015.
 */
public class MainModel {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private SmsData mSmsData = new SmsData();

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void readFile(String _fileName) {
        try {
            StreamParser.parse(_fileName, mSmsData);
        } catch (Exception _ex) {
            _ex.printStackTrace();
        }
    }
}
