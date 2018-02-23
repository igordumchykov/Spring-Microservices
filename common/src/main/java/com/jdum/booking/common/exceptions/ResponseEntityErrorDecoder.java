package com.jdum.booking.common.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author idumchykov
 * @since 2/21/18
 */
@Component
public class ResponseEntityErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            ErrorDetails errorDetails = decodeError(response);
            return new NotFoundException(errorDetails.getMessage());
        }

        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            ErrorDetails errorDetails = decodeError(response);
            return new BusinessServiceException(errorDetails.getMessage());
        }

        return defaultErrorDecoder.decode(methodKey, response);
    }

    private ErrorDetails decodeError(Response response) {
        try {
            final byte[] body = body(response);
            return mapper.readerFor(ErrorDetails.class).readValue(body);
        } catch (IOException ignore) {

        }
        return new ErrorDetails();//if something will go wrong
    }

    private static byte[] body(Response response) throws IOException {
        try (Response.Body body = response.body()) {
            return ByteStreams.toByteArray(body.asInputStream());
        }
    }
}
