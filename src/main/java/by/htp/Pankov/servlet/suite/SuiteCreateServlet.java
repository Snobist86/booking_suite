package by.htp.Pankov.servlet.suite;

import by.htp.Pankov.dto.suite.SuiteCreateDto;
import by.htp.Pankov.service.SuiteCategoryService;
import by.htp.Pankov.service.SuiteService;
import by.htp.Pankov.service.SuiteSizeService;
import by.htp.Pankov.util.JspPath;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createSuite", name = "createSuite")
public class SuiteCreateServlet extends HttpServlet{

    private static final Logger log = Logger.getLogger(SuiteCreateServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sizes", SuiteSizeService.getInstance().findAll());
        req.setAttribute("categories", SuiteCategoryService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-suite"))
                .forward(req, resp);
        log.info("Go to page of suite's create ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SuiteCreateDto suiteCreateDto = SuiteCreateDto.builder()
                .number(req.getParameter("number"))
                .suiteSizeId(req.getParameter("size"))
                .suiteCategoryId(req.getParameter("category"))
                .price(req.getParameter("price"))
                .floor(req.getParameter("floor"))
                .build();

        SuiteService.getInstance().save(suiteCreateDto);
        log.info("Create the suite ");

        resp.sendRedirect("/createSuite");
    }
}
