package by.htp.Pankov.servlet.suite;

import by.htp.Pankov.dto.suite.FindSuiteDto;
import by.htp.Pankov.dto.suite.VacantSuiteSearchDto;
import by.htp.Pankov.service.SuiteCategoryService;
import by.htp.Pankov.service.SuiteService;
import by.htp.Pankov.service.SuiteSizeService;
import by.htp.Pankov.util.JspPath;
import by.htp.Pankov.util.StringUtil;
import by.htp.Pankov.validator.FindSuitesValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(value = "/searchSuite", name = "searchSuite")
public class SearchSuiteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sizes", SuiteSizeService.getInstance().findAll());
        req.setAttribute("categories", SuiteCategoryService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("search-suite"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VacantSuiteSearchDto vacantSuiteSearchDto = VacantSuiteSearchDto.builder()
                .suiteSizeId(StringUtil.defaultParameter(req.getParameter("size")))
                .suiteCategoryId(StringUtil.defaultParameter(req.getParameter("category")))
                .checkInDate(req.getParameter("startReservationDate"))
                .checkOutDate(req.getParameter("endReservationDate"))
                .build();

        List<String> validationResult = FindSuitesValidator.getInstance()
                .validate(vacantSuiteSearchDto);
        if (validationResult.isEmpty()) {
            Set<FindSuiteDto> vacantSuites = SuiteService.getInstance().search(vacantSuiteSearchDto);
            req.setAttribute("vacantSuites", vacantSuites);
        } else {
            req.setAttribute("errors", validationResult);
        }
        getServletContext().getRequestDispatcher(JspPath.get("search-suite")).forward(req, resp);
    }
}