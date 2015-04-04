package at.fhooe.mc.it.helpers;

import at.fhooe.mc.it.data.Entry;

import javax.print.DocFlavor;
import java.util.*;

/**
 * Created by Thomas Jetzinger on 04/04/2015.
 */
public class MapUtils {
    public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue( HashMap<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        HashMap<K, V> result = new LinkedHashMap<>();

        ListIterator li = list.listIterator(list.size());

        // reverse order
        while(li.hasPrevious())
        {
            Map.Entry<K, V> entry = (Map.Entry<K, V>) li.previous();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <String, Float extends Comparable<? super Float>> HashMap<String, Float>
        sortByValueAndCalcP( HashMap<String, Float> map, int _numberEntries )
    {
        List<Map.Entry<String, Float>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        HashMap<String, Float> result = new LinkedHashMap<>();

        ListIterator li = list.listIterator(list.size());

        // reverse order (1 to 0)
        while(li.hasPrevious())
        {
            Map.Entry<String, Float> entry = (Map.Entry<String, Float>) li.previous();
            java.lang.Float value = (java.lang.Float)entry.getValue();
            result.put(entry.getKey(), (Float) new java.lang.Float(value.floatValue() / _numberEntries));
        }
        return result;
    }
}
