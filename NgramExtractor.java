package Dersler.Assignment.Assignment6;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * @author Berkay Kaptan
 * @since 09.01.2021
 * @20170808050
 */
public class NgramExtractor {

    static String input;
    static String output;
    static String n;
    private static String temp;
    private static ArrayList<String> checkedWords;
    private HashMap<String, Integer> nGramAbs = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        input = args[0];
        output = args[1];
        n = args[2];
        NgramExtractor ngramsExt = new NgramExtractor();
        ngramsExt.config();
        List<String> l1 = Arrays.asList(temp.split(" "));
        checkedWords = new ArrayList<String>(l1);
        testNgrams(n);
    }

    private void config() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            StringBuilder inputContent = new StringBuilder();
            String eachLine = reader.readLine();
            while (eachLine != null) {
                eachLine = eachLine.replaceAll("\\p{Punct}", "").trim().toLowerCase();
                inputContent.append(eachLine + " ");
                eachLine = reader.readLine();
            }
            reader.close();
            temp = inputContent.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect file path or name.");
        } catch (IOException e) {
            System.out.println("Error happened while reading");
        }
    }

    private static void testNgrams(String n) throws IOException {
        File file = new File(output);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int tempChecked = Integer.parseInt(n);
        HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();
        int allWords = checkedWords.size();
        for (int i = 0; i < allWords - tempChecked + 1; i++) {
            String nGram = "";
            for (int j = i; j < i + tempChecked; j++) {
                nGram += checkedWords.get(j) + " ";
            }
                if (tempHashMap.containsKey(nGram)) {
                    tempHashMap.put(nGram, tempHashMap.get(nGram) + 1);
            }   else {
                    tempHashMap.put(nGram, 1);
            }
        }
        tempHashMap = tempHashMap.entrySet().stream().sorted(Map.Entry.<String,
                Integer>comparingByValue().reversed()).collect(toMap(Map.Entry::getKey,
                Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        double tempValue=0.0;
        for (Map.Entry<String, Integer> given : tempHashMap.entrySet()) {
            tempValue = tempValue + given.getValue();
        }

        bufferedWriter.write("Total number of tokens: " +  tempValue);
        bufferedWriter.newLine();

        bufferedWriter.write("ngram,count,frequency");
        bufferedWriter.newLine();
        for (Map.Entry<String, Integer> check : tempHashMap.entrySet()) {
            bufferedWriter.write(check.getKey() + " , " + check.getValue() + " , " + (100 * check.getValue()) / tempValue);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}



