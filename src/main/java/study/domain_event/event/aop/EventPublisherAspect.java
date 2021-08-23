package study.domain_event.event.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EventPublisherAspect implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    private ThreadLocal<Boolean> appliedLocal = new ThreadLocal<>();

    /* 메소드에 트랜젝션 어노테이션 붙은 경우에만 동작하도록 되어 있다. */
    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object handleEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean appliedValue = appliedLocal.get();
        boolean nested = false;

        if (appliedValue != null && appliedValue) {
            nested = true;
        } else {
            nested = false;
            appliedLocal.set(Boolean.TRUE);
        }

        if (!nested) Events.setPublisher(publisher);

        try {
            return joinPoint.proceed();
        } finally {
            if (!nested) {
                Events.reset();
                appliedLocal.remove();
            }
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.publisher = eventPublisher;
    }
}
