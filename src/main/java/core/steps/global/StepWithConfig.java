package core.steps.global;

import core.steps.config.AbstractStepConfig;

public interface StepWithConfig {

    void initConfig(AbstractStepConfig config);
    AbstractStepConfig getConfig();
}
