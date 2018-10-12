package by.htp.Pankov.servlet;

import by.htp.Pankov.service.SuiteSizeService;
import by.htp.Pankov.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

//@WebServlet(value = "/main", name = "main")
public class HomepageServlet extends HttpServlet {

    private static final int ONE_WEEK = 1;

//    private static List<String> SUITE_SIZES = SuiteSizeService.getInstance().findAllSuiteSizes();

    //    private static List<suiteCategory> SUITE_CATEGORIES = SuiteCategoryService.getInstance().findAllSuiteCategories();
//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        setAttributes(req);
        getServletContext().getRequestDispatcher(JspPath.get("main-page"))
                .forward(req, resp);
    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestToFindSuitesDto requestToFindSuitesDto = RequestToFindSuitesDto.builder()
//                .suiteSize(StringUtil.defaultIfBlank(req.getParameter("suiteSize")))
//                .suiteCategory(StringUtil.defaultIfBlank(req.getParameter("suiteCategory")))
//                .startReservationDate(req.getParameter("startReservationDate"))
//                .endReservationDate(req.getParameter("endReservationDate"))
//                .build();
//
//        setDefaultDatesIfEmpty(req, requestToFindSuitesDto);
//
//        setAttributes(req);
//        List<String> validationResult = FindSuitesValidator.getInstance()
//                .validate(requestToFindSuitesDto);
//        if (validationResult.isEmpty()) {
//            List<SuiteCreateDto> vacantSuites = SuiteService.getInstance().findVacantSuitesByAllParameters(requestToFindSuitesDto);
//            req.setAttribute("vacantSuites", vacantSuites);
//        } else {
//            req.setAttribute("errors", validationResult);
//        }
//        getServletContext().getRequestDispatcher(JspPath.get("homepage/homepage")).forward(req, resp);
//    }
//
//    private void setDefaultDatesIfEmpty(HttpServletRequest req, RequestToFindSuitesDto requestToFindSuitesDto) {
//        if (StringUtil.isEmpty(requestToFindSuitesDto.getStartReservationDate())) {
//            String defaultStartDate = String.valueOf(LocalDate.now());
//            requestToFindSuitesDto.setStartReservationDate(defaultStartDate);
//            req.setAttribute("defaultStartDate", defaultStartDate);
//        }
//
//        if (StringUtil.isEmpty(requestToFindSuitesDto.getEndReservationDate())) {
//            String defaultEndDate = String.valueOf(LocalDateFormat.format(requestToFindSuitesDto.getStartReservationDate()).plusWeeks(ONE_WEEK));
//            requestToFindSuitesDto.setEndReservationDate(defaultEndDate);
//            req.setAttribute("defaultEndDate", defaultEndDate);
//        }
//    }

//    private void setAttributes(HttpServletRequest req) {
//        req.setAttribute("suiteSizes", SUITE_SIZES);
//        req.setAttribute("suiteCategories", SUITE_CATEGORIES);
//    }
}


