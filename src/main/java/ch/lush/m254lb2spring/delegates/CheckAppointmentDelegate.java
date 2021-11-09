package ch.lush.m254lb2spring.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckAppointmentDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String selectedDate = (String) delegateExecution.getVariable("selectedDate");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(selectedDate, dateTimeFormatter);

        if (LocalDateTime.now().isAfter(localDateTime)) {
            delegateExecution.setVariable("errorMsg", delegateExecution.getVariable("errorMsg").toString() + "; Datum ist in der Vergangenheit");
        }
    }
}
