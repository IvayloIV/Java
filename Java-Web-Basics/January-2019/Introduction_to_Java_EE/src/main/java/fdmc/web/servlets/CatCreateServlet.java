package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.utils.HtmlFileReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {
    private final static String HTML_CREATE_CAT_FILE = "C:\\Users\\MASTER\\Desktop\\FDMC\\src\\main\\resources\\views\\cat-create.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public CatCreateServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.getWriter().println(this.htmlFileReader.readHtmlFile(HTML_CREATE_CAT_FILE));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Cat cat = new Cat();
        cat.setName(req.getParameter("name"));
        cat.setColor(req.getParameter("color"));
        cat.setBreed(req.getParameter("breed"));
        cat.setNumberOfLegs(Integer.parseInt(req.getParameter("numberOfLegs")));

        if (req.getSession().getAttribute("cats") == null) {
            req.getSession().setAttribute("cats", new LinkedHashMap<String, Cat>());
        }

        ((LinkedHashMap<String, Cat>)req.getSession().getAttribute("cats")).put(cat.getName(), cat);
        res.sendRedirect(String.format("/cat/profile?catName=%s", cat.getName()));
    }
}
