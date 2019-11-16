package chushki.web.servlets;

import chushki.domain.models.service.ProductServiceModel;
import chushki.domain.models.view.ProductInfoModelView;
import chushki.service.ProductService;
import chushki.util.HtmlReader;
import chushki.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet("/products/all")
public class IndexServlet extends HttpServlet {
    private static final String INDEX_HTML_PATH = "C:\\Users\\MASTER\\Desktop\\temp\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFile = this.htmlReader.readHtmlFile(INDEX_HTML_PATH);
        ProductInfoModelView[] products = Arrays.stream(this.productService.getAllProducts())
                .map(p -> this.modelMapper.map(p, ProductInfoModelView.class))
                .toArray(ProductInfoModelView[]::new);

        String replacement = Arrays.stream(products)
                .map(p -> String.format("<li><a href=\"/products/details?name=%1$s\">%1$s</a></li>", p.getName()))
                .collect(Collectors.joining("\n"));

        htmlFile = htmlFile.replace("{{products}}", replacement);
        resp.getWriter().println(htmlFile);
    }
}
