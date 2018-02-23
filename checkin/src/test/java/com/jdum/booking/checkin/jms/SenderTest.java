package com.jdum.booking.checkin.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jdum.booking.checkin.util.TestDataCreator.CHECK_IN_ID;
import static com.jdum.booking.checkin.constants.Constants.CHECK_IN_QUEUE;
import static org.mockito.Mockito.verify;

/**
 * @author idumchykov
 * @since 2/22/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SenderTest {

    @InjectMocks
    private Sender sender;

    @Mock
    private RabbitMessagingTemplate template;

    @Test
    public void send() throws Exception {

        sender.send(CHECK_IN_ID);

        verify(template).convertAndSend(CHECK_IN_QUEUE, CHECK_IN_ID);
    }

}