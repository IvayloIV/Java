package fdmc.web.servlets;

import fdmc.utils.HtmlFileReader;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private final static String HTML_INDEX_PATH_FILE = "C:\\Users\\MASTER\\Desktop\\FDMC\\src\\main\\resources\\views\\index.html";

    private final HtmlFileReader htmlFileReader;

    @Inject
    public IndexServlet(HtmlFileReader htmlFileReader) {
        this.htmlFileReader = htmlFileReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println(this.htmlFileReader.readHtmlFile(HTML_INDEX_PATH_FILE));
    }
}
