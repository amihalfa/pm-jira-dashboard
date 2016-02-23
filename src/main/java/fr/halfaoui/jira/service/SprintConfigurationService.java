package fr.halfaoui.jira.service;

import fr.halfaoui.jira.model.Sprint;
import fr.halfaoui.jira.technical.util.DateUtil;
import org.springframework.stereotype.Service;
import static java.time.temporal.ChronoUnit.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

/**
 * @author amirouche
 */
@Service
public class SprintConfigurationService {

    public static EnumSet<DayOfWeek> EXCLUDED_DAYS = EnumSet.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
    );

    public List<Date> getAvailableDays(Sprint sprint) {
        ArrayList<Date> availableDays = new ArrayList<>();
        if (sprint != null) {
            LocalDate sprintStart = DateUtil.dateToLocalDate(sprint.getStartDate());
            LocalDate sprintEnd = DateUtil.dateToLocalDate(sprint.getEndDate());
            long days = DAYS.between(sprintStart, sprintEnd);
            for (int i = 0; i <= days; i++) {
                LocalDate day = sprintStart.plusDays(i);
                if (! isExcludedDay(day.getDayOfWeek())) {
                    availableDays.add(DateUtil.localDateToDate(day));
                }
            }
        }
        return availableDays;
    }

    public List<Date> getAvailableDaysBeforeToday(Sprint sprint) {
        ArrayList<Date> availableDays = new ArrayList<>();
        if (sprint != null) {
            LocalDate sprintStart = DateUtil.dateToLocalDate(sprint.getStartDate());
            LocalDate sprintEnd = DateUtil.dateToLocalDate(sprint.getEndDate());
            if (sprintEnd.isAfter(LocalDate.now())) {
                sprintEnd = LocalDate.now();
            }
            long days = DAYS.between(sprintStart, sprintEnd);
            for (int i = 0; i <= days; i++) {
                LocalDate day = sprintStart.plusDays(i);
                if (! isExcludedDay(day.getDayOfWeek())) {
                    availableDays.add(DateUtil.localDateToDate(day));
                }
            }
        }
        return availableDays;
    }


    public boolean isExcludedDay(DayOfWeek dayOfWeek){
        return EXCLUDED_DAYS.contains(dayOfWeek);
    }

}
