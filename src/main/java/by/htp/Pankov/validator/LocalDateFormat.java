package by.htp.Pankov.validator;

import by.htp.Pankov.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateFormat {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final String RUSSIAN_PATTERN = "dd-MM-yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final DateTimeFormatter SLAVIC_FORMATTER = DateTimeFormatter.ofPattern(RUSSIAN_PATTERN);

    public static LocalDate format(String value) {
        LocalDate localDate = null;

        if (!StringUtil.isEmpty(value)) {
            try {
                localDate = LocalDate.parse(value, FORMATTER);
            } catch (DateTimeParseException e) {
                e.printStackTrace();
            }
        }

        return localDate;
    }

    public static String format(LocalDate localDate) {
        String formattedValue = null;

        if (localDate != null) {
            formattedValue = FORMATTER.format(localDate);
        }

        return formattedValue;
    }

    public static String slavicFormat(LocalDate localDate) {
        String formattedValue = null;

        if (localDate != null) {
            formattedValue = SLAVIC_FORMATTER.format(localDate);
        }

        return formattedValue;
    }
}
