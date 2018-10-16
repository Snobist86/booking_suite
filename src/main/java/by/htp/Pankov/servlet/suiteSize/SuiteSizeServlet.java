package by.htp.Pankov.servlet.suiteSize;

import by.htp.Pankov.dto.suiteSize.SuiteSizeDto;
import by.htp.Pankov.service.SuiteSizeService;
import by.htp.Pankov.util.JspPath;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createSuiteSize", name = "createSuiteSize")
public class SuiteSizeServlet extends HttpServlet{

    private static final Logger log = Logger.getLogger(SuiteSizeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-size"))
                .forward(req,resp);
        log.info("Go to page of create of the suite's size ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SuiteSizeDto suiteSizeDto = SuiteSizeDto.builder()
                .name(req.getParameter("name"))
                .maxCapacity(Integer.valueOf(req.getParameter("maxCapacity")))
                .comment(req.getParameter("comment"))
                .build();

        SuiteSizeService.getInstance().save(suiteSizeDto);
        log.info("Create of the suite's size ");

        resp.sendRedirect("/createSuiteSize");
    }
}
