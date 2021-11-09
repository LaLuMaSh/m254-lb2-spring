package ch.lush.m254lb2spring.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LocalDate baseDate = LocalDate.now().plusDays(1);
        LocalDateTime baseDateTime = LocalDateTime.of(baseDate.getYear(), baseDate.getMonth(), baseDate.getDayOfMonth(), 8, 0);

        List<LocalDateTime> result = new ArrayList<>();
        while (result.size() < 101) {
            if (baseDateTime.getDayOfWeek() == DayOfWeek.SATURDAY) {
                baseDateTime = baseDateTime.plusDays(2);
            }else if (baseDateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
                baseDateTime = baseDateTime.plusDays(1);
            }

            if (baseDateTime.getHour() >= 17) {
                baseDateTime = LocalDateTime.of(baseDate.getYear(), baseDate.getMonth(), baseDate.getDayOfMonth(), 8, 0).plusDays(1);
            }

            result.add(baseDateTime);
            baseDateTime = baseDateTime.plusHours(1);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        delegateExecution.setVariable("dates", result.stream().map(localDateTime -> localDateTime.format(dateTimeFormatter)).collect(Collectors.joining(";")));
    }
}
