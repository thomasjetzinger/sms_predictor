package at.fhooe.mc.it;

import at.fhooe.mc.it.data.SmsData;
import at.fhooe.mc.it.data.WordEntry;
import at.fhooe.mc.it.xml.StreamParser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

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
      System.out.println("Started reading");
      StreamParser.parse(_fileName, mSmsData);
      System.out.println("Finished reading");

    } catch (Exception _ex) {
      _ex.printStackTrace();
    }
  }

  public List<String> getSuggestedWords(String previousWord, int n) {
    WordEntry wordEntry = mSmsData.getWords().get(previousWord);
    if (wordEntry == null) {
      return new ArrayList<>();
    }
    List<String> suggestedWords = wordEntry.getSuggestedWords();
    boolean longList = suggestedWords.size() > n;
    return longList ? suggestedWords.subList(0, n) : suggestedWords;
  }
}
