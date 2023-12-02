import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class D2 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("D2_input.txt");){
            BufferedReader br = new BufferedReader(fr);

            int setPowers = 0;


            // red, green and blue cubes
            HashMap<String, Integer> cubes = new HashMap<>();


            String line = "";
            while((line = br.readLine()) != null) {
                Scanner s = new Scanner(line);

                String id = s.findInLine("\\d+");

                List<String> sets = Arrays.stream(line.split(":|;")).skip(1).collect(Collectors.toList());

                cubes.put("red",0);
                cubes.put("green", 0);
                cubes.put("blue",0);

                // Each set consists of "3 blue, 4 red" e.g.
                for( var set : sets){
                    String[] batches = set.split(",");

                    // Each batch is e.g. "3 blue"
                    for (var batch: batches) {
                        Scanner sl = new Scanner(batch);

                        int nr = Integer.parseInt(sl.findInLine("\\d+"));
                        String color = sl.findInLine("blue|red|green");

                        if(nr > cubes.get(color)){
                            cubes.put(color,nr);
                        }
                    }
                }

                setPowers += cubes.values().stream().reduce(1,(a, b) -> a * b);

            }
            System.out.println(setPowers);

        }catch (IOException io){
            System.out.println(io.toString());
        }

    }

    private static Integer parse(String l) {
        return Integer.parseInt(l);
    }
}
