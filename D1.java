import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class D1 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("D1_input.txt");){
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            int sum = 0;
            while((line = br.readLine()) != null) {


                // This solves the issue with overlapping numbers
                line = line.replace("one", "o1e");
                line = line.replace("two", "t2o");
                line = line.replace("three", "th3ee");
                line = line.replace("four", "f4ur");
                line = line.replace("five", "f5ve");
                line = line.replace("six", "s6x");
                line = line.replace("seven", "se7en");
                line = line.replace("eight", "ei8ht");
                line = line.replace("nine", "n9ne");

                Scanner s = new Scanner(line);


                // Find first occurrence of digit
                String f = s.findInLine("\\d");

                // Find last occurrence of digit
                String n = "";
                String l = "";
                while (n != null){
                    l = n;
                    n = s.findInLine("\\d");
                }

                if(!l.equals("")){
                    System.out.println(line+": "+f +""+ l);
                    sum += Integer.parseInt(parse(f) +""+ parse(l));
                }else{
                    System.out.println(line+": "+f +""+ f);
                    sum += Integer.parseInt(parse(f) +""+ parse(f));
                }

            }

            System.out.println(sum);
        }catch (IOException io){
            System.out.println(io.toString());
        }

    }

    private static Integer parse(String l) {
        return Integer.parseInt(l);
    }
}
