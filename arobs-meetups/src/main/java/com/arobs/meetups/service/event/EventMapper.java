package com.arobs.meetups.service.event;

import com.arobs.meetups.entities.Event;
import com.arobs.meetups.repositories.UserRepository;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class EventMapper extends ConfigurableMapper implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    protected void configure(MapperFactory factory) {
        factory.classMap(Event.class, EventDto.class)
                .byDefault()
                .field("date", "date")
                .customize(new CustomMapper<Event, EventDto>(){
                    @Override
                    public void mapBtoA(EventDto eventDto, Event event, MappingContext context){
                        event.setUser(eventDto.getUser());
                        event.setMaximumPeople(eventDto.getMaximumPeople());
                        event.setTitle(eventDto.getTitle());
                        event.setLanguage(eventDto.getLanguage());
                        event.setDurationInMinutes(eventDto.getDurationInMinutes());
                        event.setDifficulty(eventDto.getDifficulty());
                        event.setDescription(eventDto.getDescription());
                        String date = eventDto.getDate();
                        Timestamp dateTimestamp = Timestamp.valueOf(date);
                        event.setDate(dateTimestamp);
                        event.setRoom(eventDto.getRoom());
                        event.setClosed(eventDto.isClosed());
                    }
                })
                .register();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        init();
    }

}
