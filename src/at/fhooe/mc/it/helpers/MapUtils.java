package at.fhooe.mc.it.helpers;

import at.fhooe.mc.it.data.Entry;

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
}
