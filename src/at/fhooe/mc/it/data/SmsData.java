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

    public void addWord(String _currentWord, String _nextWord) {
        WordEntry wordEntry = mWords.get(_currentWord);
        if(wordEntry == null) {
            wordEntry = new WordEntry(_currentWord);
            mWords.put(_currentWord, wordEntry);
        }

        wordEntry.addWord(_nextWord);
    }

    public LinkedHashMap<String, WordEntry> getWords() {
        return mWords;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
