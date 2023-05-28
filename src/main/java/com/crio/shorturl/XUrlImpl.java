package com.crio.shorturl;

import java.util.HashMap;

public class XUrlImpl implements XUrl{
    HashMap<String, String> sortURL = new HashMap<>();
    HashMap<String, String> longURL = new HashMap<>();
    HashMap<String, Integer> count = new HashMap<>();
    static String getAlphaNumericString(int n) {
    
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
    
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        
        for (int i = 0; i < n; i++) { 
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
            = (int)(AlphaNumericString.length()
                * Math.random());
            
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                .charAt(index));
        }
        
        return sb.toString();
    }

    public String registerNewUrl(String longUrl){
        String url = sortURL.get(longUrl);
        if (url == null) {
            String newShortUrl = "http://short.url/" + getAlphaNumericString(9);
            sortURL.put(longUrl, newShortUrl);
            longURL.put(newShortUrl, longUrl);
            url = sortURL.get(longUrl);
        }
        return url;
    }
    public String getUrl(String shortUrl) {
        String result = longURL.get(shortUrl);
        if (result == null) return null;
        if (count.containsKey(result)) {
            int getCount = count.get(result);
            count.put(result, getCount+1);
        } else {
            count.put(result, 1);
        }
        return result;
    }
    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        String sort_Url = sortURL.get(longUrl);
        String long_Url = longURL.get(shortUrl);
        if (sort_Url != null || long_Url != null) return null;
        sortURL.put(longUrl, shortUrl);
        longURL.put(shortUrl, longUrl);
        return shortUrl;
    }
    @Override
    public Integer getHitCount(String longUrl) {
        if (count.containsKey(longUrl)) return count.get(longUrl);
        return 0;
    }
    @Override
    public String delete(String longUrl) {
        String sortUrl = sortURL.get(longUrl);
        sortURL.remove(longUrl);
        return longURL.remove(sortUrl);
    }

}