package mishMash.web.servlets;

import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.domain.models.views.ChannelDetailsViewModel;
import mishMash.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/channel/details")
public class ChannelDetailsServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private ChannelService channelService;

    @Inject
    public ChannelDetailsServlet(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String channelId = req.getParameter("channelId");
        ChannelServiceModel channel = this.channelService.getById(channelId);
        ChannelDetailsViewModel channelDetailsVM = this.modelMapper
                .map(channel, ChannelDetailsViewModel.class);
        channelDetailsVM.setTags(String.join(", ", channel.getTags()));

        req.setAttribute("channel", channelDetailsVM);
        req.getRequestDispatcher("/view/channelDetails.jsp")
                .forward(req, resp);
    }
}
