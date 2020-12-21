import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamAPI {
    public static void main(String[] args) throws IOException {
        new Lenin();//новый ленин
        List<String> words = Lenin.getWords("D:/Java_laba/lenin_laba/lenin.txt");
        new First().withoutCapital(words);
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
        List<String> strings = words.stream().sorted();
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
