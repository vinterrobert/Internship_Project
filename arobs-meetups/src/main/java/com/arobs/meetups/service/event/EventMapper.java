package com.arobs.meetups.service.event;

import com.arobs.meetups.entities.Event;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class EventMapper extends ConfigurableMapper implements ApplicationContextAware {

    protected void configure(MapperFactory factory) {
        factory.classMap(Event.class, EventDto.class)
                .byDefault()
                .register();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        init();
    }

}
