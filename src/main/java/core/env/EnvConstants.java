package core.env;

public class EnvConstants {


    public static final int PAGE_LOAD_TIMEOUT =             Integer.valueOf(Environment.INSTANCE.getProperty("global.page_load_timeout"));
    public static final int ELEMENT_LOAD_TIMEOUT =          Integer.valueOf(Environment.INSTANCE.getProperty("global.element_load_timeout"));



}
