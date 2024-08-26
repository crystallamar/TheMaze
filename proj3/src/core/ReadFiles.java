package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadFiles {

    public Long readFileSeed() {
        long savedSeed = 12345L;
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("Seed.data"));
            savedSeed = input.readLong();
        } catch(IOException ioe) {
            System.err.println("Issue retrieving Seed.data");
        }
        return savedSeed;
    }
}
