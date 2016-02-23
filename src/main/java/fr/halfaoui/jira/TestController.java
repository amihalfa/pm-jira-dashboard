package fr.halfaoui.jira;

import fr.halfaoui.jira.model.GraphData;
import fr.halfaoui.jira.model.Sprint;
import fr.halfaoui.jira.service.SprintService;
import fr.halfaoui.jira.service.StatsService;
import fr.halfaoui.jira.service.session.SprintSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author amirouche
 */
@Controller
public class TestController {

    @Autowired
    private SprintService sprintService;

    @Autowired
    private SprintSession sprintSession;

    @Autowired
    private StatsService statsService;

    @Value("${jira.board.id}")
    private Long boardId;


    @RequestMapping(value = "/ajax/points", method = RequestMethod.GET)
    public @ResponseBody GraphData ajaxGraphData() {
        return statsService.getGraphData(sprintSession.getSprint());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dashboard(ModelMap modelMap) {
        Sprint sprint = sprintService.getActiveSprint(boardId);
        if(sprint == null) {
            return "empty";
        }
        modelMap.addAttribute("sprint", sprint);
        modelMap.addAttribute("stats", statsService.computeStats(sprint));
        sprintSession.setSprint(sprint);
        return "index";

    }


}
