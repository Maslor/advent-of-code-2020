package gabriel.advent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdventDay1 {
	private static List<Integer> numbers = new ArrayList<>();

	private AdventDay1() {
		throw new IllegalStateException("Utility Class");
	}

	public static void solveProblem(String filePath, int setLength) {
		try {
			Scanner scanner = new Scanner(new FileReader(filePath));
			while (scanner.hasNextLine()) {
				numbers.add(Integer.valueOf(scanner.nextLine()));
			}
			scanner.close();

			if (setLength == 3) {
				System.out.println(getThree2020product());
			} else if (setLength == 2) {
				System.out.println(get2020product());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Integer get2020product() {
		for (int i = 0; i < numbers.size() - 1; i++) {
			for (int j = 1; j < numbers.size(); j++) {
				if (numbers.get(i) + numbers.get(j) == 2020) {
					return numbers.get(i) * numbers.get(j);
				}
			}
		}
		throw new IllegalStateException("2020 sum not found");
	}

	private static Integer getThree2020product() {
		for (int i = 0; i < numbers.size() - 2; i++) {
			for (int j = 1; j < numbers.size() - 1; j++) {
				for (int w = 2; w < numbers.size(); w++) {
					if (numbers.get(i) + numbers.get(j) + numbers.get(w) == 2020) {
						return numbers.get(i) * numbers.get(j) * numbers.get(w);
					}
				}
			}
		}
		throw new IllegalStateException("2020 sum not found");
	}
}
