package at.fhooe.mc.it.data;

import at.fhooe.mc.it.helpers.MapUtils;

import java.util.HashMap;

/**
 * Created by Thomas Jetzinger on 04/04/2015.
 */
public class WordEntry extends Entry {
    private String mWord;
    private HashMap<String, Float> mOtherWords;
    private int mCount = 0;

    public WordEntry(String _word) {
        mWord = _word;
        mOtherWords = new HashMap<>();
    }

    public int getCount() {
        return mCount;
    }

    public void addWord(String _word) {
        Float value = mOtherWords.get(_word);

        // First next word?
        if (value == null)
            mOtherWords.put(_word, 1f);
        else
            // increment word counter
            mOtherWords.put(_word, value.floatValue() + 1);

        // overall next word counter for the this word
        mCount++;
    }

    /**
     * Orders the word (from 1 to 0) and calc P from the word frequency
     */
    public void order() {
        mOtherWords = MapUtils.sortByValueAndCalcP(mOtherWords, mCount);
    }
}
