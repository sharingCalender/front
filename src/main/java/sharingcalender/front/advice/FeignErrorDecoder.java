package sharingcalender.front.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import sharingcalender.front.dto.MessageDto;
import sharingcalender.front.exception.AlreadyExistException;
import sharingcalender.front.exception.AuthenticationException;
import sharingcalender.front.exception.BadRequestException;
import sharingcalender.front.exception.ResourceNotFoundException;
import sharingcalender.front.exception.UnAuthorizedException;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    private ObjectMapper objectMapper = new ObjectMapper();
    private ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();
    @Override
    public Exception decode(String s, Response response) {
        int status = response.status();

        MessageDto messageDto = null;
        try (BufferedInputStream bi = new BufferedInputStream(response.body().asInputStream())) {
            messageDto = objectMapper.readValue(bi.readAllBytes(), MessageDto.class);
        } catch (IOException e) {
            log.warn("Parsing Exception in FeignErrorDecoder Class :  ", e);
            throw new RuntimeException(e);
        }

        return switch (status) {
            case 400 -> new BadRequestException(messageDto.message());
            case 401 -> new UnAuthorizedException(messageDto.message());
            case 403 -> new AuthenticationException(messageDto.message());
            case 404 -> new ResourceNotFoundException(messageDto.message());
            case 409 -> new AlreadyExistException(messageDto.message());
            default -> defaultErrorDecoder.decode(s, response);
        };
    }
}
