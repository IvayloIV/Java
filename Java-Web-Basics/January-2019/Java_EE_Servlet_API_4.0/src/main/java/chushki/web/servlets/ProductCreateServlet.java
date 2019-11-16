package chushki.web.servlets;

import chushki.domain.entities.Type;
import chushki.domain.models.service.ProductServiceModel;
import chushki.service.ProductService;
import chushki.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private static final String CREATE_PRODUCT_HTML_PATH = "C:\\Users\\MASTER\\Desktop\\temp\\src\\main\\resources\\views\\create-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String createProductHtml = this.htmlReader.readHtmlFile(CREATE_PRODUCT_HTML_PATH);
        createProductHtml = createProductHtml.replace("{{types}}", getTypeFormat());
        resp.getWriter().println(createProductHtml);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productModel = new ProductServiceModel();
        productModel.setName(req.getParameter("name"));
        productModel.setDescription(req.getParameter("description"));
        productModel.setType(Type.valueOf(req.getParameter("type")));

        this.productService.saveProduct(productModel);
        resp.sendRedirect("/products/all");
    }

    private String getTypeFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("<option disabled selected>Choose type...</option>")
                .append(System.lineSeparator());
        Arrays.stream(Type.values())
                .forEach(t -> sb
                        .append(String.format("<option value=\"%1$s\">%1$s</option>", t.name()))
                        .append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
