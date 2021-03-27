package com.dzavorin.solutions.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DesignAuthenticationManager {

    int ttl;

    Map<String, Integer> map = new HashMap<>();
    TreeMap<Integer, String> expires = new TreeMap<>();

    public DesignAuthenticationManager(int ttl) {
        this.ttl = ttl;
    }

    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime + ttl);
        expires.put(currentTime + ttl, tokenId);
    }

    public void renew(String tokenId, int currentTime) {
        Integer expire = map.get(tokenId);
        if (expire != null && expire > currentTime) {
            expires.remove(expire);
            expires.put(currentTime + ttl, tokenId);
            map.put(tokenId, currentTime + ttl);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        return expires.tailMap(currentTime, false).size();
    }

}
