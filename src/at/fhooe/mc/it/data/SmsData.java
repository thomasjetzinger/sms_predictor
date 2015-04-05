package at.fhooe.mc.it.data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Thomas Jetzinger on 04/04/2015.
 */
public class SmsData {
  private LinkedHashMap<String, WordEntry> mWords;

  private Map<Character, WordEntry> mCharacterMap;

  public SmsData() {
    mWords = new LinkedHashMap<>();
    mCharacterMap = new LinkedHashMap<>();
  }

  /**
   * Adds a new word to the hash map
   *
   * @param _currentWord Current Word in
   * @param _nextWord    Word after current word
   */
  public void addWord(String _currentWord, String _nextWord) {
    WordEntry wordEntry = mWords.get(_currentWord);
    // First word?
    if (wordEntry == null) {
      wordEntry = new WordEntry();
      mWords.put(_currentWord, wordEntry);
    }
    wordEntry.addWord(_nextWord);
  }

  public void addCharacterEntry(String _word) {
    Character startChar = _word.charAt(0);
    WordEntry wordEntry = mCharacterMap.get(startChar);
    if (wordEntry == null) {
      wordEntry = new WordEntry();
      mCharacterMap.put(startChar, wordEntry);
    }
    wordEntry.addWord(_word);
  }

  public LinkedHashMap<String, WordEntry> getWords() {
    return mWords;
  }

  public Map<Character, WordEntry> getCharEntries() {
    return mCharacterMap;
  }
}
