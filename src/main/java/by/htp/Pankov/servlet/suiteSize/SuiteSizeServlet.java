package by.htp.Pankov.servlet.suiteSize;

import by.htp.Pankov.dto.suiteSize.SuiteSizeDto;
import by.htp.Pankov.service.SuiteSizeService;
import by.htp.Pankov.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/createSuiteSize", name = "createSuiteSize")
public class SuiteSizeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("create-size"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SuiteSizeDto suiteSizeDto = SuiteSizeDto.builder()
                .name(req.getParameter("name"))
                .maxCapacity(Integer.valueOf(req.getParameter("maxCapacity")))
                .comment(req.getParameter("comment"))
                .build();

        SuiteSizeService.getInstance().save(suiteSizeDto);

        resp.sendRedirect("/createSuiteSize");
    }
}
