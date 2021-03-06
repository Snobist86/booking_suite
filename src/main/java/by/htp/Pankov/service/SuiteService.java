package by.htp.Pankov.service;

import by.htp.Pankov.dao.SuiteDao;
import by.htp.Pankov.dto.suite.FindSuiteDto;
import by.htp.Pankov.dto.suite.FindSuiteForOrderDto;
import by.htp.Pankov.dto.suite.SuiteCreateDto;
import by.htp.Pankov.dto.suite.VacantSuiteSearchDto;
import by.htp.Pankov.entity.Suite;
import by.htp.Pankov.entity.SuiteCategory;
import by.htp.Pankov.entity.SuiteSize;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SuiteService {

    private static final SuiteService INSTANCE = new SuiteService();

    public void save(SuiteCreateDto suiteCreateDto) {
        SuiteDao.getInstance().save(
                Suite.builder()
                        .number(Integer.valueOf(suiteCreateDto.getNumber()))
                        .suiteSize(SuiteSize.builder()
                                .id(Long.valueOf(suiteCreateDto.getSuiteSizeId()))
                                .build())
                        .suiteCategory(SuiteCategory.builder()
                                .id(Long.valueOf(suiteCreateDto.getSuiteCategoryId()))
                                .build())
                        .price(Integer.valueOf(suiteCreateDto.getPrice()))
                        .floor(Integer.valueOf(suiteCreateDto.getFloor()))
                        .build());
    }

    public Set<FindSuiteDto> search(VacantSuiteSearchDto vacantSuiteSearchDto) {
        return SuiteDao.getInstance().searchVacantSuites(vacantSuiteSearchDto).stream()
                .map(it -> getFindVacantSuiteDto(it))
                .collect(Collectors.toSet());
    }

    public List<FindSuiteForOrderDto> searchSuiteBySizeAndCategory(VacantSuiteSearchDto vacantSuiteSearchDto){
        return SuiteDao.getInstance().searchSuitesForOrder(vacantSuiteSearchDto).stream()
                .map(it -> getFindVacantSuiteForOrderDto(it))
                .collect(Collectors.toList());
    }

    private FindSuiteDto getFindVacantSuiteDto(Suite it) {
        return new FindSuiteDto
                (String.valueOf(it.getSuiteSize().getId()),
                String.valueOf(it.getSuiteSize().getName()),
                String.valueOf(it.getSuiteCategory().getId()),
                String.valueOf(it.getSuiteCategory().getName()),
                String.valueOf(it.getPrice()),
                String.valueOf(it.getSuiteSize().getComment()),
                String.valueOf(it.getSuiteCategory().getComment()));
    }

    private FindSuiteForOrderDto getFindVacantSuiteForOrderDto(Suite it) {
        return new FindSuiteForOrderDto
                (String.valueOf(it.getId()),
                String.valueOf(it.getNumber()));
    }

    public static SuiteService getInstance() {
        return INSTANCE;
    }
}
