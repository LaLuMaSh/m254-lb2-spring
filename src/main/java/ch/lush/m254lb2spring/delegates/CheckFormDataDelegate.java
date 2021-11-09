package ch.lush.m254lb2spring.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckFormDataDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        isValid(delegateExecution, "name");
        isValid(delegateExecution, "firstname");
        isValid(delegateExecution, "ahv");
        isValid(delegateExecution, "email");
    }

    private static void isValid(DelegateExecution delegateExecution, String property) {
        String error = delegateExecution.hasVariable("errorMsg") ? (String) delegateExecution.getVariable("errorMsg") : "";
        if (!delegateExecution.hasVariable(property)) {
            error += "; " + property + " existiert nicht.";
            delegateExecution.setVariable("errorMsg", error);
            return;
        }
        String variable = delegateExecution.getVariable(property).toString();
        if (variable.isEmpty()) {
            error += "; " + property + " beinhaltet nichts.";
        }
        delegateExecution.setVariable("errorMsg", error);
    }
}
