package com.jdum.booking.checkin.mapper;

import com.jdum.booking.checkin.model.CheckInRecord;
import com.jdum.booking.common.dto.CheckInRecordDTO;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * @author idumchykov
 * @since 2/13/18
 */
@Component
public class CheckInRecordMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {

        factory.classMap(CheckInRecord.class, CheckInRecordDTO.class)
                .byDefault()
                .register();
    }
}
