package mishMash.web.servlets;

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

@WebServlet("/channel/unfollow")
public class UnfollowChannelServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private ChannelService channelService;

    @Inject
    public UnfollowChannelServlet(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = (String) req.getSession().getAttribute("id");
        String channelId = req.getParameter("channelId");
        ChannelServiceModel channel = this.modelMapper
                .map(this.channelService.getById(channelId), ChannelServiceModel.class);
        this.channelService.removeFollower(channel, userId);
        resp.sendRedirect("/channel/my");
    }
}
