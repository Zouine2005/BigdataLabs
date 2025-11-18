package edu.supmti.hadoop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class ReadHDFS {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: <hdfs_path>");
            System.exit(1);
        }

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path file = new Path(args[0]);
        if (!fs.exists(file)) {
            System.out.println("File not found: " + file.toString());
            fs.close();
            System.exit(1);
        }

        try (FSDataInputStream in = fs.open(file);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        fs.close();
    }
}