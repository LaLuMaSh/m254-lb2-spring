package ch.lush.m254lb2spring.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckAhvDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String ahv = (String) delegateExecution.getVariable("ahv");
        if (ahv.replace(".", "").length() != 13) {
            delegateExecution.setVariable("errorMsg", delegateExecution.getVariable("errorMsg") + "; Invalide AHV Nummer " + ahv);
        }
    }
}
