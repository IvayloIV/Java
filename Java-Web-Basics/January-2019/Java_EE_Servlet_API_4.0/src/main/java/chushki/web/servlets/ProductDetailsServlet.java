package chushki.web.servlets;

import chushki.domain.models.view.ProductDetailsModelView;
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

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
    private static final String PRODUCT_DETAILS_HTML_PATH = "C:\\Users\\MASTER\\Desktop\\temp\\src\\main\\resources\\views\\details-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public ProductDetailsServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getQueryString().split("=")[1];
        ProductDetailsModelView product = this.modelMapper.map(this.productService.getByName(name), ProductDetailsModelView.class);

        String detailsProductHtml = this.htmlReader.readHtmlFile(PRODUCT_DETAILS_HTML_PATH);
        detailsProductHtml = detailsProductHtml
                .replace("{{name}}", product.getName())
                .replace("{{description}}", product.getDescription())
                .replace("{{type}}", product.getType());

        resp.getWriter().println(detailsProductHtml);
    }
}
