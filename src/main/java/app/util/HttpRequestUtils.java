package app.util;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HttpRequestUtils {

    public static Map<String, String> parseQueryString(String queryString){
        return parseValues(queryString, "&");
    }

    public static Map<String, String> parseCookies(String cookies){
        return parseValues(cookies, ";");
    }

    public static Pair parseHeader(String header){
        return getKeyvalue(header, ": ");
    }

    public static Map<String, String> parseValues(String values, String separator){
        if(Strings.isNullOrEmpty(values)){
            return Maps.newHashMap();
        }

        String[] tokens = values.split(separator);
        return Arrays.stream(tokens)
                .map(s -> getKeyvalue(s, "="))
                .filter(pair -> pair != null)
                .collect(Collectors.toMap(pair -> pair.getKey(), pair -> pair.getValue()));

    }

    static Pair getKeyvalue(String keyValue, String regex){
        if(Strings.isNullOrEmpty(keyValue)){
            return null;
        }

        String[] tokens = keyValue.split(regex);
        if(tokens.length != 2){
            return null;
        }

        return new Pair(tokens[0], tokens[1]);
    }

    public static class Pair {
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        String key;
        String value;

        public Pair(String key, String value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Pair [key=" + key + ", value=" + value + "]";
        }
    }



}
