package study.domain_event.event.aop;

import org.springframework.context.ApplicationEventPublisher;
import study.domain_event.event.event.DomainEvent;

public class Events {
    private static ThreadLocal<ApplicationEventPublisher> publisherLocal = new ThreadLocal<>();

    public static void raise(DomainEvent event) {
        if (event == null) return;

        if (publisherLocal.get() != null) {
            publisherLocal.get().publishEvent(event);
        }
    }

    static void setPublisher(ApplicationEventPublisher publisher) {
        publisherLocal.set(publisher);
    }

    static void reset() {
        publisherLocal.remove();
    }
}
