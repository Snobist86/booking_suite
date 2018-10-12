package by.htp.Pankov.servlet.previewOrder;

import by.htp.Pankov.dto.previewOrder.FindPreviewOrderDto;
import by.htp.Pankov.dto.previewOrder.SearchPreviewOrderDto;
import by.htp.Pankov.service.OrderStatusService;
import by.htp.Pankov.service.PreviewOrderService;
import by.htp.Pankov.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/checkPreviewOrder", name = "checkPreviewOrder")
public class CheckPreviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("statusOrderList", OrderStatusService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("administration-of-orders"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchPreviewOrderDto dto = SearchPreviewOrderDto.builder()
                .id(req.getParameter("id"))
                .build();

        List<FindPreviewOrderDto> previewOrders = PreviewOrderService.getInstance().search(dto);

        req.setAttribute("previewOrders", previewOrders);
        req.setAttribute("status", dto);

        getServletContext().getRequestDispatcher(JspPath.get("administration-of-orders")).forward(req, resp);
    }

}