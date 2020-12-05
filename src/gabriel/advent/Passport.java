package gabriel.advent;

import javax.validation.constraints.NotNull;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Passport {

	private static final String HEX_WEBCOLOR_PATTERN = "^#([a-fA-F0-9]{6})$";
	private static final String NINE_DIGIT_NUMBER_PATTERN = "([0-9]{9})";

	private static final Pattern colorPattern = Pattern.compile(HEX_WEBCOLOR_PATTERN);
	private static final Pattern numberPattern = Pattern.compile(NINE_DIGIT_NUMBER_PATTERN);


	@JsonProperty("byr")
	@NotNull
	private Integer birthYear;

	@JsonProperty("iyr")
	@NotNull
	private Integer issueYear;

	@JsonProperty("eyr")
	@NotNull
	private Integer expirationYear;

	@JsonProperty("ecl")
	@NotNull
	private EyeColour eyeColour;

	@JsonProperty("hgt")
	@NotNull
	private String height;

	@JsonProperty("hcl")
	@NotNull
	private String hairColour;

	@JsonProperty("pid")
	@NotNull
	private String passportId;

	@JsonProperty("cid")
	private Integer countryId;

	public boolean checkIfValid() {
		for (Field f : getClass().getDeclaredFields()) {
			try {
				if (f.get(this) == null && !f.getName().equals("countryId")) {
					return false;
				}
			} catch (IllegalAccessException e) {
				return false;
			}
		}

		return this.getBirthYear() >= 1920 && this.getBirthYear() <= 2002 &&
				this.getIssueYear() >= 2010 && this.getIssueYear() <= 2020 &&
				this.getExpirationYear() >= 2020 && this.getExpirationYear() <= 2030 &&
				colorPattern.matcher(this.getHairColour()).matches() &&
				numberPattern.matcher(this.getPassportId()).matches() &&
				isValidHeight(this.getHeight());
	}

	private boolean isValidHeight(String height) {
		String[] heightParameters = height.replaceAll("(\\d+)()(\\S)", "$1 $3").split(" ");
		int heightValue = Integer.valueOf(heightParameters[0]);
		String heightType = heightParameters[1];

		if (heightType.equals("in")) {
			return heightValue >= 59 && heightValue <= 76;
		} else if (heightType.equals("cm")) {
			return heightValue >= 150 && heightValue <= 193;
		}
		return false;
	}


}
