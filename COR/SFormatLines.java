package chainOfResponsibility;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Count the type of program lines
public class SFormatLines {
    public static void main(String[] args) throws IOException {
        Path thisFile = FileSystems.getDefault().getPath("src/chainOfResponsibility/QFormatLines.java");
        Map<LineType, Long> lineCount = Files.lines(thisFile).peek(System.out::println).map(s -> s.trim())
                .map(SFormatLines::classify).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        System.out.printf("Saw %s lines", lineCount);
    }

    enum LineType {
        COMMENT(s -> s.startsWith("//")), BLANK(String::isEmpty), IMPORT(s -> s.startsWith("import")), CODE(s -> true);
        public final Predicate<String> isItMe;
        LineType(Predicate<String> isItMe) {
            this.isItMe = isItMe;
        }
    }
    private static LineType[] lineTypes = LineType.values();
    public static LineType classify(String line) {
        return Arrays.stream(lineTypes).filter(lt -> lt.isItMe.test(line)).findFirst()
                .orElseThrow(() -> new RuntimeException("no Line type"));
    }
}
//Problem located in DesignPatternsParticipants.chainOfResponsibility.QFormatLines.javafire
