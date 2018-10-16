package by.htp.Pankov.validator;

import by.htp.Pankov.dto.suite.VacantSuiteSearchDto;
import by.htp.Pankov.util.ErrorMessageUtil;
import by.htp.Pankov.util.StringUtil;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindSuitesValidator {

    private static FindSuitesValidator INSTANCE = new FindSuitesValidator();

    public List<String> validate(VacantSuiteSearchDto dto) {
        List<String> errors = new ArrayList<>();

        String checkInDate = dto.getCheckInDate();
        LocalDate localCheckInDate = LocalDateFormat.format(checkInDate);
        String formattedLocalCheckInDate = LocalDateFormat.slavicFormat(localCheckInDate);
        LocalDate localCurrentDate = LocalDate.now();
        String formattedLocalCurrentDate = LocalDateFormat.slavicFormat(localCurrentDate);
        if (StringUtil.isEmpty(checkInDate)) {
            errors.add(ErrorMessageUtil.CHECK_IN_FIELD_IS_EMPTY);
        } else if (localCheckInDate == null) {
            errors.add(ErrorMessageUtil.CHECK_IN_DATE_HAS_INCORRECT_FORMAT);
        } else if (localCheckInDate.isBefore(localCurrentDate)) {
            errors.add(ErrorMessageUtil.THE_NEAREST_DATE_FOR_RESERVATION_IS + formattedLocalCurrentDate);
        } else if (localCheckInDate.isAfter(localCurrentDate.plusYears(1))) {
            errors.add(ErrorMessageUtil.RESERVATION_IS_AVAILABLE_FOR_THE_NEAREST_YEAR);
        }

        String checkOutDate = dto.getCheckOutDate();
        LocalDate localCheckOutDate = LocalDateFormat.format(checkOutDate);
        String minCheckOutDate = LocalDateFormat.slavicFormat(localCurrentDate.plusDays(1));
        if (StringUtil.isEmpty(checkOutDate)) {
            errors.add(ErrorMessageUtil.CHECK_OUT_FIELD_IS_EMPTY);
        } else if (localCheckOutDate == null) {
            errors.add(ErrorMessageUtil.CHECK_OUT_DATE_HAS_INCORRECT_FORMAT);
        } else if (localCheckInDate != null && localCheckOutDate.isBefore(localCheckInDate.plusDays(1))) {
            errors.add(ErrorMessageUtil.CHECK_OUT_DATE_IS_SUPPOSED_TO_BE_AFTER_THE_CHECK_IN_DATE );
        } else if (localCheckOutDate.isBefore(localCurrentDate.plusDays(1))) {
            errors.add(ErrorMessageUtil.THE_NEAREST_CHECK_OUT_DATE_IS + minCheckOutDate);
        }

        return errors;
    }

    public static FindSuitesValidator getInstance() {
        return INSTANCE;
    }
}
