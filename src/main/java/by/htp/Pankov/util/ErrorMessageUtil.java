package by.htp.Pankov.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessageUtil {

    public static final String CHECK_IN_FIELD_IS_EMPTY = "Дата прибытия пуста";
    public static final String CHECK_OUT_FIELD_IS_EMPTY = "Дата отъезда пуста";
    public static final String CHECK_IN_DATE_HAS_INCORRECT_FORMAT = "Некорректный формат даты прибытия";
    public static final String CHECK_OUT_DATE_HAS_INCORRECT_FORMAT = "Некорректный формат даты отъезда";
    public static final String THE_NEAREST_DATE_FOR_RESERVATION_IS = "Ближайшая дата прибытия ";
    public static final String THE_NEAREST_CHECK_OUT_DATE_IS = "Ближайшая дата отъезда ";
    public static final String CHECK_OUT_DATE_IS_SUPPOSED_TO_BE_AFTER_THE_CHECK_IN_DATE = "Предпологаемая дата отъезда " +
            "должна быть позже текущей даты и даты заезда.";
    public static final String RESERVATION_IS_AVAILABLE_FOR_THE_NEAREST_YEAR = "Резервирование доступно только на ближайший год";
}
