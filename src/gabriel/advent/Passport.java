package gabriel.advent;

import javax.validation.constraints.NotNull;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Passport {

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
	private String eyeColour;

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
				if (f.get(this) == null) {
					if (!f.getName().equals("countryId")) {
						return false;
					}
				}
			} catch (IllegalAccessException e) {
				return false;
			}
		}
		return true;
	}


}
