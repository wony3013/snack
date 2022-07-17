package app.util;

import java.io.BufferedReader;
import java.io.IOException;

public class IOUtils {

    public static String getCubf(BufferedReader br, int dataLength) throws IOException {
        char[] cubf = new char[dataLength];
        int requestBodyLength = br.read(cubf, 0, dataLength);
        return String.valueOf(cubf);
    }
}
