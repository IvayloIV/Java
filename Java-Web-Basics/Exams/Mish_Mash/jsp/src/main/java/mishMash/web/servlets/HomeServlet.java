package mishMash.web.servlets;

import mishMash.domain.models.services.ChannelServiceModel;
import mishMash.domain.models.services.UserServiceModel;
import mishMash.domain.models.views.ChannelHomeViewModel;
import mishMash.service.ChannelService;
import mishMash.service.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private List<ChannelHomeViewModel> followedChannels;
    private List<ChannelHomeViewModel> suggestChannels;
    private List<ChannelHomeViewModel> otherChannels;

    private ModelMapper modelMapper;
    private ChannelService channelService;
    private UserService userService;

    @Inject
    public HomeServlet(ModelMapper modelMapper, ChannelService channelService, UserService userService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.followedChannels = new ArrayList<>();
        this.suggestChannels = new ArrayList<>();
        this.otherChannels = new ArrayList<>();
        String userId = (String) req.getSession().getAttribute("id");
        UserServiceModel userServiceModel = this.userService.getById(userId);
        List<ChannelServiceModel> channels = this.channelService.getAll();

        for (ChannelServiceModel channel : channels) {
            List<ChannelHomeViewModel> channelType = getChannelType(userServiceModel, channel);
            channelType.add(this.modelMapper.map(channel, ChannelHomeViewModel.class));
        }

        req.setAttribute("followedChannels", null);
        req.setAttribute("suggestChannels", null);
        req.setAttribute("otherChannels", null);
        req.setAttribute("followedChannels", followedChannels);
        req.setAttribute("suggestChannels", suggestChannels);
        req.setAttribute("otherChannels", otherChannels);
        req.getRequestDispatcher("/view/home.jsp")
                .forward(req, resp);
    }

    private List<ChannelHomeViewModel> getChannelType(UserServiceModel userServiceModel, ChannelServiceModel channel) {
        if (this.isUserFollowChannel(userServiceModel, channel)) {
            return this.followedChannels;
        }

        if (this.checkTagConnection(userServiceModel, channel)) {
            return this.suggestChannels;
        }
        return this.otherChannels;
    }

    private boolean checkTagConnection(UserServiceModel userServiceModel, ChannelServiceModel channel) {
        for (String tag : channel.getTags()) {
            if (checkUserTags(userServiceModel, tag)) {
                return true;
            }
        }
        return false;
    }

    private boolean isUserFollowChannel(UserServiceModel userServiceModel, ChannelServiceModel channel) {
        return userServiceModel.getFollowedChannels()
                .stream()
                .anyMatch(c -> c.getId().equals(channel.getId()));
    }

    private boolean checkUserTags(UserServiceModel userServiceModel, String tag) {
        return userServiceModel.getFollowedChannels()
                .stream()
                .anyMatch(u -> u.getTags()
                        .stream()
                        .anyMatch(t -> t.equals(tag)));
    }
}
