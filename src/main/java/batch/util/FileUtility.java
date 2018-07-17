package batch.util;

import java.io.*;
import java.util.List;

public class FileUtility {

    public static void writeBuffered(File file, List<String> records, int bufSize, boolean append) throws IOException {
        try {
            FileWriter writer = new FileWriter(file, append);
            write(records, new BufferedWriter(writer, bufSize));
        } finally {
//            file.delete();
        }
    }

    private static void write(List<String> records, Writer writer) throws IOException {
        for (String record: records) {
            writer.write(record);
        }
        writer.flush();
        writer.close();
    }
}
