package web;

import dao.AdsDaoImpl;
import dao.BaseDao;
import model.Ads;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdsServlet", urlPatterns = {"/ads", "/ads-new", "/ads-add", "/ads-edit",
        "/ads-update", "/ads-delete"})
public class AdsServlet extends HttpServlet {

    private BaseDao<Ads> adsDao;

    public AdsServlet() {
        this.adsDao = new AdsDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/ads-add":
                addAds(req, resp);
                break;
            case "/ads-new":
                showAdsForm(req, resp);
                break;
            case "/ads-edit":
                showEditFormAds(req, resp);
                break;
            case "/ads-update":
                updateAds(req, resp);
                break;
            case "/ads-delete":
                deleteAds(req, resp);
                break;
            default:
                showAllAds(req, resp);
                break;

        }

    }

    private void deleteAds(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        adsDao.delete(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/ads");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void showAdsForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/ads-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void showAllAds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ads> ads = adsDao.read();
        req.setAttribute("ads", ads);
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/ads.jsp");
        dispatcher.forward(req, resp);
    }

    private void addAds(HttpServletRequest request, HttpServletResponse response) throws IOException {

        this.adsDao.insert(Ads.builder()
                .title(request.getParameter("title"))
                .subtitle(request.getParameter("subtitle"))
                .description(request.getParameter("description"))
                .price(Double.parseDouble(request.getParameter("price")))
                .user_id(1)
                .build());
        response.sendRedirect(request.getContextPath() + "/ads");

    }

    private void showEditFormAds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Ads ads = adsDao.read(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/ads-form.jsp");
        request.setAttribute("ads", ads);
        dispatcher.forward(request, response);
    }

    private void updateAds(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.adsDao.update(Ads.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .title(req.getParameter("title"))
                .subtitle(req.getParameter("subtitle"))
                .description(req.getParameter("description"))
                .price(Double.parseDouble(req.getParameter("price")))
                .build()

        );
        resp.sendRedirect(req.getContextPath() + "/ads");
    }
}
