package ua.kotsko.lab.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HttpException extends RuntimeException {
    private final int httpCode;
}
