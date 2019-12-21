package com.arobs.meetups.service.proposal;

import com.arobs.meetups.entities.Proposal;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ProposalMapper extends ConfigurableMapper implements ApplicationContextAware {

    protected void configure(MapperFactory factory) {
        factory.classMap(Proposal.class, ProposalDto.class)
                .byDefault()
                .register();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        init();
    }

}
