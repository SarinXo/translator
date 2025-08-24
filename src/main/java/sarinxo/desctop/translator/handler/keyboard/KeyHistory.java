package sarinxo.desctop.translator.handler.keyboard;

import java.util.Arrays;

public class KeyHistory {

    private final int[] keys;
    private final byte capacity;
    private byte pos;

    public KeyHistory(byte capacity) {
        if( capacity <= 0 ) {
            throw new IllegalArgumentException("capacity < 0");
        } else if( capacity > 4 ) {
            throw new IllegalArgumentException("capacity > 4 (too much)");
        }
        this.capacity = capacity;
        keys = new int[capacity];
        Arrays.fill(keys, 0);
        pos = (byte) 0;
    }

    public void addKey(int key) {
        if (pos < capacity - 1) {
            pos++;
        } else {
            pos = 0;
        }
        keys[pos] = key;
    }

    public int[] getKeys() {
        int[] orderedKeys = new int[capacity];
        byte currentKey = pos;
        for(byte i = (byte)(capacity - 1); i > 0; i--) {
            orderedKeys[i] = keys[currentKey];
            if(currentKey == 0) {
                currentKey = (byte) (capacity - 1);
            } else
                currentKey--;
        }
        return orderedKeys;
    }
}
