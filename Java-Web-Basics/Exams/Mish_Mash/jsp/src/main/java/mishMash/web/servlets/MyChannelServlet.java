package mishMash.web.servlets;

import mishMash.domain.models.views.MyChannelViewModel;
import mishMash.service.ChannelService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.Transient;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/channel/my")
public class MyChannelServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private ChannelService channelService;

    @Inject
    public MyChannelServlet(ModelMapper modelMapper, ChannelService channelService) {
        this.modelMapper = modelMapper;
        this.channelService = channelService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = (String) req.getSession().getAttribute("id");
        List<MyChannelViewModel> channels = this.channelService.getByUserId(userId)
                .stream()
                .map(c -> this.modelMapper.map(c, MyChannelViewModel.class))
                .collect(Collectors.toList());

        req.setAttribute("channels", null);
        req.setAttribute("channels", channels);
        req.getRequestDispatcher("/view/myChannels.jsp")
                .forward(req, resp);
    }
}
