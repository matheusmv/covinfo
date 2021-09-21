package br.edu.ifce.usecase.utils;

import br.edu.ifce.domain.Message;

import java.util.Optional;
import java.util.function.Function;

import static br.edu.ifce.usecase.utils.MessageValidationResult.CONTENT_NOT_VALID;
import static br.edu.ifce.usecase.utils.MessageValidationResult.SUCCESS;
import static br.edu.ifce.usecase.utils.MessageValidationResult.TITLE_NOT_VALID;

public interface MessageValidation extends Function<Message, MessageValidationResult> {
    static MessageValidation titleIsValid() {
        return message -> {
            boolean titleNotNullAndNotEmpty = Optional.ofNullable(message)
                    .map(Message::getTitle)
                    .filter(title -> !title.isBlank())
                    .isPresent();

            return titleNotNullAndNotEmpty ? SUCCESS : TITLE_NOT_VALID;
        };
    }

    static MessageValidation contentIsValid() {
        return message -> {
            boolean titleNotNullAndNotEmpty = Optional.ofNullable(message)
                    .map(Message::getContent)
                    .filter(content -> !content.isBlank())
                    .isPresent();

            return titleNotNullAndNotEmpty ? SUCCESS : CONTENT_NOT_VALID;
        };
    }

    default MessageValidation and(MessageValidation validation) {
        return address -> {
            MessageValidationResult result = this.apply(address);
            return result.equals(SUCCESS) ? validation.apply(address) : result;
        };
    }
}