package by.htp.Pankov;

import by.htp.Pankov.dao.SuiteDao;
import by.htp.Pankov.dto.suite.FindSuiteDto;
import by.htp.Pankov.dto.suite.VacantSuiteSearchDto;

import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        test1();
    }

//    private static Set<FindSuiteDto> test1() {
//        VacantSuiteSearchDto dto = VacantSuiteSearchDto.builder()
//                .suiteSizeId("2")
//                .suiteCategoryId("15")
//                .checkInDate("2018-10-16")
//                .checkOutDate("2018-10-20")
//                .build();
//
//        return SuiteDao.getInstance().searchVacantSuites(dto).stream()
//                .map(it -> new FindSuiteDto(Long.valueOf(it.getSuiteSize().getId()),
//                        Long.valueOf(it.getSuiteCategory().getId()),
//                        Integer.valueOf(it.getPrice())))
//                .collect(Collectors.toSet());
//    }
}