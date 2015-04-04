package at.fhooe.mc.it.data;

import at.fhooe.mc.it.helpers.MapUtils;

import java.util.HashMap;

/**
 * Created by Thomas Jetzinger on 04/04/2015.
 */
public class WordEntry extends Entry {
    private String mWord;
    private HashMap<String, Float> mOtherWords;

    public WordEntry(String _word) {
        mWord = _word;
        mOtherWords = new HashMap<>();
    }

    public void addWord(String _word) {
        Float value = mOtherWords.get(_word);

        if (value == null)
            mOtherWords.put(_word, 1f);
        else
            mOtherWords.put(_word, value.floatValue() + 1);
    }

    public void order() {
        mOtherWords = MapUtils.sortByValue(mOtherWords);
    }
}
