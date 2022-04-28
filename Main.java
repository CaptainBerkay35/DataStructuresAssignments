package Dersler.Assignment.Assignment7;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Berkay Kaptan
 * @since 16.01.2021
 * @20170808050
 */

public class Main {
    static String input;
    static String output;

    public static void main(String[] args)
    {
        input = "src/Dersler/Assignment/input.txt";
        output = "output.txt";
        SplayTree spt = new SplayTree();
        char ch;
        String currentLine;

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(output,true));
            while ((currentLine = br.readLine()) != null){
                String[] temp;
                temp = currentLine.split(" " );
                if(temp[0].equals("insert")){
                    spt.insert(Integer.parseInt(temp[1]));
                }
                else if(temp[0].equals("remove")){
                    spt.remove(spt.findNode(Integer.parseInt(temp[1])));
                }
                else if(temp[0].equals("find")){
                    spt.findNode(Integer.parseInt(temp[1]));
                }

                StringBuilder sb = spt.printLevelOrder(spt.getRoot());
                sb.append("\n");
                String s = sb.toString();
                bw.write(s);

            }
            br.close();
            bw.close();
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

