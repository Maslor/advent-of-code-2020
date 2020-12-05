package gabriel.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

	private Util() {
		throw new IllegalStateException("Utility Class");
	}

	public static List<String> getFileLines(String filePath) {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			return stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Failed to get file lines");
	}

	public static String getFileContent(String filePath) {
		StringBuilder fileContentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(s -> fileContentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContentBuilder.toString();

	}
}
