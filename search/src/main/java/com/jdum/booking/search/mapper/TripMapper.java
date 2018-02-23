package com.jdum.booking.search.mapper;

import com.jdum.booking.common.dto.TripDTO;
import com.jdum.booking.search.model.Trip;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * @author idumchykov
 * @since 2/13/18
 */
@Component
public class TripMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(Trip.class, TripDTO.class)
                .byDefault()
                .register();
    }
}
