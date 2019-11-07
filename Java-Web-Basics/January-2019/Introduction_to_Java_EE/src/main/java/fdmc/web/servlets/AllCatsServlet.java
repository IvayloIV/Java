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

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
    private final static String ALL_CATS_FILE = "C:\\Users\\MASTER\\Desktop\\FDMC\\src\\main\\resources\\views\\all-cats.html";
    private final static String ALL_CATS_NOT_FOUND_FILE = "C:\\Users\\MASTER\\Desktop\\FDMC\\src\\main\\resources\\views\\all-cats-notfound.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public AllCatsServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        LinkedHashMap<String, Cat> cats = ((LinkedHashMap<String, Cat>)req.getSession().getAttribute("cats"));
        String fileContent;

        if (cats == null || cats.size() == 0) {
            fileContent = this.htmlFileReader.readHtmlFile(ALL_CATS_NOT_FOUND_FILE);
        } else {
            fileContent = this.htmlFileReader.readHtmlFile(ALL_CATS_FILE);
            StringBuilder sb = new StringBuilder();

            cats.forEach((catName, c) ->
                    sb.append(String.format("<h4><a href=\"/cat/profile?catName=%s\">%1$s</a></h4>", catName)));
            fileContent = fileContent.replace("{cats}", sb.toString().trim());
        }

        res.getWriter().println(fileContent);
    }
}
