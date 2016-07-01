package fr.halfaoui.jira.service;

import fr.halfaoui.jira.model.GraphData;
import fr.halfaoui.jira.model.Issue;
import fr.halfaoui.jira.model.Sprint;
import fr.halfaoui.jira.model.Stats;
import fr.halfaoui.jira.technical.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author amirouche
 */
@Service
public class StatsService {

    @Autowired
    private SprintConfigurationService sprintConfigurationService;

    public Set<Issue> getAllTasks(Sprint sprint) {
        Set<Issue> tasks = new HashSet<>();
        for (Issue story : sprint.getIssues()) {
            if (story.isTask()) {
                tasks.add(story);
            } else {
                tasks.addAll(story.getChildren());
            }
        }
        return tasks;
    }

    public Stats computeStats(Sprint sprint) {
        Stats stats = new Stats();
        if (sprint != null) {
            long total = getAllTasks(sprint).stream()
                    .mapToLong(Issue::getComplexity)
                    .reduce(0, (a, b) -> a + b);
            long done = getAllTasks(sprint).stream()
                    .filter(Issue::isDone)
                    .mapToLong(Issue::getComplexity)
                    .reduce(0, (a, b) -> a + b);
            long totalTasks = getAllTasks(sprint).stream().count();
            long doneTasks = getAllTasks(sprint).stream().filter(Issue::isDone).count();
            stats.setDonePoints(done);
            stats.setTotalPoints(total);
            stats.setDoneTasks(doneTasks);
            stats.setTotalTasks(totalTasks);
        }
        return stats;
    }

    public GraphData getGraphData(Sprint sprint) {
        GraphData graphData = new GraphData();
        if (sprint != null) {
            Stats stats = computeStats(sprint);
            List<Date> availableDays = sprintConfigurationService.getAvailableDays(sprint);
            List<Date> availableDaysBeforeToday = sprintConfigurationService.getAvailableDaysBeforeToday(sprint);
            graphData.setAvailableDays(availableDays);
            SortedMap<Date, Long> remainingPointsPerDay = getRemainingPointsPerDay(sprint, availableDaysBeforeToday, stats);
            graphData.setRemainingPoints(remainingPointsPerDay);
            graphData.setMaximum(stats.getTotalPoints());
        }
        return graphData;
    }

    public SortedMap<Date, Long> getRemainingPointsPerDay(Sprint sprint, List<Date> availableDays, Stats stats) {
        SortedMap<Date, Long> pointPerDay = new TreeMap<>();
        availableDays.forEach(
                date -> pointPerDay.put(date, 0l)
        );
        getAllTasks(sprint).stream()
                .filter(Issue::isDone)
                .forEach(issue -> {
                    Date resolutionDay = DateUtil.getStartOfDay(issue.getResolutionDate());
                    if (pointPerDay.containsKey(resolutionDay)) {
                        pointPerDay.put(resolutionDay, pointPerDay.get(resolutionDay) + issue.getComplexity());
                    }
                });
        Date prevDay = null;
        for (Date day : availableDays) {
            if (prevDay != null) {
                pointPerDay.put(day, pointPerDay.get(day) + pointPerDay.get(prevDay));
            }
            prevDay = day;
        }
        availableDays.forEach(
                resolutionDay -> pointPerDay.put(resolutionDay, stats.getTotalPoints() - pointPerDay.get(resolutionDay))
        );
        return pointPerDay;
    }

}
