package by.htp.Pankov.servlet.suiteCategory;

import by.htp.Pankov.dto.suiteCategory.SuiteCategoryDto;
import by.htp.Pankov.service.SuiteCategoryService;
import by.htp.Pankov.util.JspPath;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createSuiteCategory", name = "createSuiteCategory")
public class SuiteCategoryServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(SuiteCategoryServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-category"))
                .forward(req, resp);
        log.info("Go to page of create of the suite's category ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SuiteCategoryDto suiteCategoryDto = SuiteCategoryDto.builder()
                .name(req.getParameter("name"))
                .comment(req.getParameter("comment"))
                .build();

        SuiteCategoryService.getInstance().save(suiteCategoryDto);
        log.info("Create of the suite's category ");

        resp.sendRedirect("/createSuiteCategory");
    }
}
