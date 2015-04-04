package at.fhooe.mc.it.data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Thomas Jetzinger on 04/04/2015.
 */
public class SmsData {
    private LinkedHashMap<String, WordEntry> mWords;

    public SmsData() {
        mWords = new LinkedHashMap<>();
    }

    /**
     * Adds a new word to the hash map
     * @param _currentWord Current Word in
     * @param _nextWord Word after current word
     */
    public void addWord(String _currentWord, String _nextWord) {
        WordEntry wordEntry = mWords.get(_currentWord);
        // First word?
        if(wordEntry == null) {
            wordEntry = new WordEntry(_currentWord);
            mWords.put(_currentWord, wordEntry);
        }

        wordEntry.addWord(_nextWord);
    }

    public LinkedHashMap<String, WordEntry> getWords() {
        return mWords;
    }
}
