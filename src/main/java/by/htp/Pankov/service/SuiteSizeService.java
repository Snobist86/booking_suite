package by.htp.Pankov.service;

import by.htp.Pankov.dao.SuiteSizeDao;
import by.htp.Pankov.dto.suiteSize.SuiteSizeDto;
import by.htp.Pankov.entity.SuiteSize;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SuiteSizeService {

    private static final SuiteSizeService INSTANCE = new SuiteSizeService();

    public void save(SuiteSizeDto suiteCategoryDto) {
        SuiteSize suiteSize = SuiteSize.builder()
                .name(suiteCategoryDto.getName())
                .maxCapacity(suiteCategoryDto.getMaxCapacity())
                .comment(suiteCategoryDto.getComment())
                .build();
        SuiteSizeDao.getInstance().save(suiteSize);
    }

    public List<SuiteSize> findAll() {
        return SuiteSizeDao.getInstance().findAllSize();
    }

    public static SuiteSizeService getInstance() {
        return INSTANCE;
    }
}
