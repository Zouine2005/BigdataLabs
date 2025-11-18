package edu.supmti.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HadoopFileStatus {
    public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            System.out.println("Usage: <chemin> <nom_fichier> <nouveau_nom>");
            System.exit(1);
        }

        String dir = args[0];
        String oldName = args[1];
        String newName = args[2];

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path oldPath = new Path(dir + "/" + oldName);
        Path newPath = new Path(dir + "/" + newName);

        if (!fs.exists(oldPath)) {
            System.out.println("❌ Le fichier n'existe pas !");
            System.exit(1);
        }

        FileStatus status = fs.getFileStatus(oldPath);

        System.out.println("Nom : " + oldPath.getName());
        System.out.println("Taille : " + status.getLen() + " bytes");
        System.out.println("Propriétaire : " + status.getOwner());
        System.out.println("Permissions : " + status.getPermission());
        System.out.println("Replication : " + status.getReplication());
        System.out.println("Taille block : " + status.getBlockSize());

        System.out.println("➡ Renommage...");
        fs.rename(oldPath, newPath);
        System.out.println("✔ Renommé en : " + newName);

        fs.close();
    }
}

