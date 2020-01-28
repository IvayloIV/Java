package torshia.web.filters;

import torshia.domain.models.bindings.TaskCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebFilter("/task/create")
public class TaskCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toUpperCase().equals("POST")) {
            TaskCreateBindingModel model = new TaskCreateBindingModel();
            model.setTitle(req.getParameter("title"));
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            try {
                model.setDueDate(formater.parse(req.getParameter("dueDate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            model.setDescription(req.getParameter("description"));
            model.setParticipants(req.getParameter("participants"));

            if (req.getParameterValues("sector") != null) {
                model.setAffectedSectors(Arrays.stream(req.getParameterValues("sector"))
                        .collect(Collectors.toList()));
            } else {
                model.setAffectedSectors(new ArrayList<>());
            }

            req.setAttribute("model", model);
        }

        filterChain.doFilter(req, resp);
    }
}
