package gabriel.advent;

import com.sun.tools.javac.util.Pair;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PasswordValidation {
	public Pair<Integer, Integer> intervalCount;
	public String character;
}
