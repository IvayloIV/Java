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

@WebServlet("/cat/profile")
public class CatProfileServlet extends HttpServlet {
    private final static String HTML_CAT_PROFILE_FILE = "C:\\Users\\MASTER\\Desktop\\FDMC\\src\\main\\resources\\views\\cat-profile.html";
    private final static String HTML_CAT_NONEXIST_FILE = "C:\\Users\\MASTER\\Desktop\\FDMC\\src\\main\\resources\\views\\cat-nonexist.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public CatProfileServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String fileContent;
        String catName = req.getQueryString().split("=")[1];
        LinkedHashMap<String, Cat> cats = ((LinkedHashMap<String, Cat>)req.getSession().getAttribute("cats"));

        if (!cats.containsKey(catName)) {
            fileContent = this.htmlFileReader.readHtmlFile(HTML_CAT_NONEXIST_FILE);
            fileContent = fileContent.replace("{name}", catName);
        } else {
            fileContent = this.htmlFileReader.readHtmlFile(HTML_CAT_PROFILE_FILE);
            Cat cat = cats.get(catName);
            fileContent = fileContent.replace("{name}", cat.getName())
                    .replace("{breed}", cat.getBreed())
                    .replace("{color}", cat.getColor())
                    .replace("{numberOfLegs}", String.valueOf(cat.getNumberOfLegs()));
        }

        res.getWriter().println(fileContent);
    }
}
