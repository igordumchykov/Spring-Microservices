package com.jdum.booking.prices.mapper;

import com.jdum.booking.common.dto.PriceDTO;
import com.jdum.booking.prices.model.Price;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@Component
public class PriceMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(Price.class, PriceDTO.class)
                .byDefault()
                .register();
    }
}