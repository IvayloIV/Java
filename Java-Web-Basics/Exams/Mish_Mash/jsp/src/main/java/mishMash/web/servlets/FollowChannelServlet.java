package mishMash.web.servlets;

import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.service.ChannelService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/channel/follow")
public class FollowChannelServlet extends HttpServlet {

    private ChannelService channelService;

    @Inject
    public FollowChannelServlet(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = (String) req.getSession().getAttribute("id");
        String channelId = req.getParameter("channelId");
        ChannelServiceModel channelServiceModel = this.channelService.getById(channelId);
        this.channelService.addNewFollower(channelServiceModel, userId);
        resp.sendRedirect("/home");
    }
}
