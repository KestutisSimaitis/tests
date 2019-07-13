package cucumberTests;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile {

    List<String> getUserCredentials() throws FileNotFoundException{
            Scanner s = new Scanner(new File("sources\\LoginData.txt"));
            ArrayList<String> list = new ArrayList<String>();
            while (s.hasNext()) {
                list.add(s.next());
            }
            s.close();
            return  list;
    }
}
