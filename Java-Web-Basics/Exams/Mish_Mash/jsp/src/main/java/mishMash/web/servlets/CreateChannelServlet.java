package mishMash.web.servlets;

import mishMash.domain.models.bindings.ChannelCreateBindingModel;
import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/channel/create")
public class CreateChannelServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private ChannelService channelService;

    @Inject
    public CreateChannelServlet(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/createChannel.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChannelCreateBindingModel model = (ChannelCreateBindingModel) req.getAttribute("model");
        ChannelServiceModel channelServiceModel = this.modelMapper
                .map(model, ChannelServiceModel.class);
        List<String> tags = Arrays.stream(model.getTags()
                .split(",\\s*"))
                .collect(Collectors.toList());

        channelServiceModel.setTags(tags);

        if (!this.channelService.createChannel(channelServiceModel)) {
            resp.sendRedirect("/channel/create");
            return;
        }
        resp.sendRedirect("/");
    }
}
