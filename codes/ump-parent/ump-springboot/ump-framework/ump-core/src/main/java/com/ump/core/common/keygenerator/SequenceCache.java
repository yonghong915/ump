package com.ump.core.common.keygenerator;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class SequenceCache {
    private Map<String, SeqObject> cacheMap = new HashMap<>();
    private static SequenceCache cacheInstance = new SequenceCache();

    private SequenceCache() {
        super();
    }

    public static SequenceCache getInstance() {
        return cacheInstance;
    }

    public SeqObject getValue(String tableName) {
        return cacheMap.get(tableName);
    }

    public void putValue(String tableName, SeqObject seqObject) {
        cacheMap.put(tableName, seqObject);
    }
}
