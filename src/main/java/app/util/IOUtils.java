package app.util;

import java.io.BufferedReader;
import java.io.IOException;

public class IOUtils {

    private static String getCubf(int dataLength, BufferedReader br) throws IOException {
        char[] cubf = new char[dataLength];
        int requestBodyLength = br.read(cubf);
        return String.valueOf(cubf);
    }
}
