package core.steps.global;

import core.hooks.CucumberHooks;

import java.util.ArrayDeque;
import java.util.Queue;

public class AbstractStepExecutor {
    protected CucumberHooks hooks;
    protected Queue<AbstractGlobalStep> steps = new ArrayDeque<>();

    public AbstractStepExecutor addStep(AbstractGlobalStep step){
        steps.add(step);
        return this;
    }

    public void setHooks(CucumberHooks hooks) {
        this.hooks = hooks;
    }

    public void execute(){
        while (!steps.isEmpty()) {
            AbstractGlobalStep step = steps.poll();
            step.initHook(hooks);
            step.execute();
        }
    }


}
