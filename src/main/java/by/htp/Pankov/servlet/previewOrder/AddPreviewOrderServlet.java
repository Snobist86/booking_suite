package by.htp.Pankov.servlet.previewOrder;

import by.htp.Pankov.dto.previewOrder.AddPreviewOrderDto;
import by.htp.Pankov.entity.User;
import by.htp.Pankov.service.PreviewOrderService;
import by.htp.Pankov.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addPreviewOrder", name = "addPreviewOrder")
public class AddPreviewOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        AddPreviewOrderDto addPreviewOrderDto = AddPreviewOrderDto.builder()
                .userId(String.valueOf(currentUser.getId()))
                .suiteSizeId(req.getParameter("suiteSizeId"))
                .suiteCategoryId(req.getParameter("suiteCategoryId"))
                .checkIn(req.getParameter("inDate"))
                .checkOut(req.getParameter("outDate"))
                .price(req.getParameter("price"))
                .comment(req.getParameter("comment"))
                .build();

        PreviewOrderService.getInstance().save(addPreviewOrderDto);

        getServletContext().getRequestDispatcher(JspPath.get("successfully-order")).forward(req, resp);
    }
}
