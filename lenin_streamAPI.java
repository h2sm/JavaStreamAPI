import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class streamAPI {
    public static void main(String[] args) throws IOException {
        new Lenin();//хихи хаха хохо мне не до шуток
        List<String> words = Lenin.getWords("D:/Java_laba/lenin_laba/lenin.txt");
        //new First().withoutCapital(words);
        //new Second().wordUsageMap(words);
        //new Four(words).charCount();
        //new Five(words).charUsage();
        new Five(words).decreasingUse();
    }
}

class First{
    void withoutCapital(List<String> words){
        List<String> strings = words.stream()//map(String::toLowerCase) - с учетом регистра
                .distinct()
                .collect(Collectors.toList());
        System.out.println(strings);
        lengthSortedLexicography(strings);
    }
    void lengthSortedLexicography(List<String> words){
        List<String> strings = words.stream().sorted().collect(Collectors.toList());
    }
    void lengthSortedLexicographyLimited(List<String> words){
        List<String> strings = words.stream().filter(a-> a.length()>=4 && a.length()<=7).collect(Collectors.toList());
    }
}
class Second{
    void wordUsage(List<String> words){
      //  List<String> list = words.stream().forEach(words));
    }

    void wordUsageMap(List<String> words){
//        Map<Integer, List<String>> wordsSet = words.stream().collect(groupingBy(String::length));
//        System.out.println(wordsSet);
        Map<String, Long> list = words.stream().collect(Collectors.groupingBy(Function.identity(), counting()));
        System.out.println(list);
    }
    void wordUsageMapLimited(List<String> words){
        Map<String, Long> map = words.stream().filter(a->a.length()>=4&&a.length()<=7).collect(Collectors.groupingBy(Function.identity(), counting()));
    }
    void wordUsageMapLimitedLexicography(List<String> words){
        Map<String, Long> map = words.stream().filter(a->a.length()>=4&&a.length()<=7).sorted().collect(Collectors.groupingBy(Function.identity(), counting()));
    }
}
class Third{
    void mapping(List<String> words){
        Map<Integer, Long> wordMap = words.stream().collect(groupingBy(String::length, counting()));
        System.out.println(wordMap);
    }
}
class Four{
    List<String> words;
    Four(List<String> words){
        this.words = words;
    }
    void charCount(){
        long count = words.stream().flatMap(x -> x.chars().mapToObj(i -> (char) i)).count();
        System.out.println(count);
    }
}
class Five{
    List<String> words;
    Map<String, Long> map = new TreeMap<>();
    Map<Long, String> map1 = new TreeMap<>(Collections.reverseOrder());
    Five(List<String> words){
        this.words = words;
    }
    void charUsage(){
        words.stream()
                .flatMap(x-> Arrays.stream(x.split("")))
                .collect(groupingBy(x->x, Collectors.counting()))
                .forEach((k,v) -> map.put(k,v));
        System.out.println(map);
    }
    void decreasingUse(){
        words.stream()
                .flatMap(x-> Arrays.stream(x.split("")))
                .collect(groupingBy(x->x, Collectors.counting()))
                .forEach((k,v) -> map1.put(v,k));
        System.out.println(map1);
    }

}
class Six{
    List<String> words;

    Six(List<String> wrds){
        this.words = wrds;
    }

}


class Lenin{
    static List<String> getWords(String filename) throws IOException {
        var splitter = Pattern.compile("[\\p{Punct}\\d\\s«…»–]+");
        return Files.lines(Path.of(filename))
                .flatMap(splitter::splitAsStream)
                .filter(w -> ! w.isEmpty())
                .collect(Collectors.toList());
    }
    private static void writeResultToFile(String filename, List<String> lines) throws IOException {
        Files.write(Path.of(filename), lines);
    }
}
