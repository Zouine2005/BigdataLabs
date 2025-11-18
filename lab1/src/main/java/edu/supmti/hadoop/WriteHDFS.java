package edu.supmti.hadoop;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class WriteHDFS {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: <hdfs_path> <message>");
            System.exit(1);
        }

        String pathStr = args[0];
        String message = args[1];

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path path = new Path(pathStr);
        // create parent dirs if needed
        if (!fs.exists(path.getParent())) {
            fs.mkdirs(path.getParent());
        }

        try (FSDataOutputStream out = fs.create(path, true);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"))) {
            writer.write(message);
            writer.newLine();
        }

        System.out.println("Wrote to " + pathStr);
        fs.close();
    }
}
