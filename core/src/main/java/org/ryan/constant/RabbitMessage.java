package org.ryan.constant;

public final class RabbitMessage {
    public static final String DIRECT_EXCHANGE = "rpc.exchange";

    public static final String AUTH_ROUTING_KEY = "auth.routing.key";
    public static final String AUTH_REQUEST = "auth.request";

    public static final String REGISTER_ROUTING_KEY = "register.routing.key";
    public static final String REGISTER_REQUEST = "register.request";

    public static final String USER_ROUTING_KEY = "user.routing.key";
    public static final String USER_REQUEST = "user.request";

    /* Dead Letter */
    public static final String DEAD_LETTER_EXCHANGE = "dead.exchange";
    public static final String DEAD_LETTER_QUEUE = "dead.queue";
    public static final String DEAD_LETTER_ROUTING = "dead.routing";
}
