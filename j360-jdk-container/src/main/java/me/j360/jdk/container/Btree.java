package me.j360.jdk.container;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.Map;

/**
 * Package: me.j360.jdk.container
 * User: min_xu
 * Date: 2017/4/17 下午6:17
 * 说明：
 */
public class Btree {

    public static void main(String[] args) {
        //import org.mapdb.*
        DB db = DBMaker.memoryDB().make();


        BTreeMap<byte[], Integer> map = db
                .treeMap("towns", Serializer.BYTE_ARRAY, Serializer.INTEGER)
                .createOrOpen();

        map.put("New York".getBytes(), 1);
        map.put("New Jersey".getBytes(), 2);
        map.put("Boston".getBytes(), 3);

        //get all New* cities
        Map<byte[], Integer> newCities = map.prefixSubMap("New".getBytes());


    }
}
