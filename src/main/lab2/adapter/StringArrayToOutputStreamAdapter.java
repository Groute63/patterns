package main.lab2.adapter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StringArrayToOutputStreamAdapter {
    private final OutputStream outputStream;

    public StringArrayToOutputStreamAdapter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String[] strings) throws IOException {
        for (String string : strings) {
            outputStream.write(string.getBytes(StandardCharsets.UTF_8));
            this.outputStream.write(32);
        }
    }
}
