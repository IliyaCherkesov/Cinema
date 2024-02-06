package cinema.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ConsoleInputMock extends InputStream {
    private ArrayList<byte[]> input = new ArrayList<byte[]>();

    public void writeStringln(String str) {
        input.add(str.getBytes());
    }

    @Override
    public int read() throws IOException {
        if (input.isEmpty()) {
            return -1;
        }
        byte[] bytes = input.get(0);
        if (bytes.length == 0) {
            input.remove(0);
            return "\n".getBytes()[0];
        }

        byte res = bytes[0];
        byte[] newBytes = new byte[bytes.length - 1];
        for (int i = 0; i < newBytes.length; i++) {
            newBytes[i] = bytes[i + 1];
        }
        input.set(0, newBytes);
        return res;
    }
}
