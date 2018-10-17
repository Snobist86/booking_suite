package by.htp.Pankov.servlet.previewOrder;

import by.htp.Pankov.dto.previewOrder.FindPreviewOrderDto;
import by.htp.Pankov.service.OrderStatusService;
import by.htp.Pankov.service.PreviewOrderService;
import by.htp.Pankov.util.JspPath;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/checkPreviewOrder", name = "checkPreviewOrder")
public class CheckPreviewOrderServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(CheckPreviewOrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("statusOrderList", OrderStatusService.getInstance().findAll());
        getServletContext()
                .getRequestDispatcher(JspPath.get("administration-of-orders"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String previewOrderId = req.getParameter("id");
        List<FindPreviewOrderDto> previewOrders = PreviewOrderService.getInstance().search(previewOrderId);

        req.setAttribute("statusOrderList", OrderStatusService.getInstance().findAll());
        req.setAttribute("previewOrders", previewOrders);
        log.info("search of the pre-order ");

        getServletContext().getRequestDispatcher(JspPath.get("administration-of-orders")).forward(req, resp);
    }

}