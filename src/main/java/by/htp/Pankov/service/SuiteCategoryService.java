package by.htp.Pankov.service;

import by.htp.Pankov.dao.SuiteCategoryDao;
import by.htp.Pankov.dto.suiteCategory.SuiteCategoryDto;
import by.htp.Pankov.entity.SuiteCategory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SuiteCategoryService {

    private static final SuiteCategoryService INSTANCE = new SuiteCategoryService();

    public List<SuiteCategory> findAll() {
        return SuiteCategoryDao.getInstance().findAllCategory();
    }

    public void save(SuiteCategoryDto suiteCategoryDto) {
        SuiteCategory suiteCategory = SuiteCategory.builder()
                .name(suiteCategoryDto.getName())
                .comment(suiteCategoryDto.getComment())
                .build();
        SuiteCategoryDao.getInstance().save(suiteCategory);
    }

    public static SuiteCategoryService getInstance() {
        return INSTANCE;
    }
}
