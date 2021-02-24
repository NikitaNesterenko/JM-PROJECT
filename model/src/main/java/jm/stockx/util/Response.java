package jm.stockx.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * Дополнительная кастомная обертка над HTTP response объектом,
 * которая реализует интерфейс Builder и позволяет более гибко настроить ответ.
 *
 * @param <T> тип тела
 */
public class Response<T> extends HttpEntity<T> {

    private final Object status;

    /**
     * Создает Response с заданным телом, заголовками и статусом.
     *
     * @param body    Тело ответа
     * @param headers Заголовки ответа
     * @param status  Статус код ответа
     */
    private Response(@Nullable T body,
                     @Nullable MultiValueMap<String, String> headers,
                     Object status) {
        super(body, headers);
        this.status = status;
    }

    /**
     * Возвращает HTTP статус код ответа.
     *
     * @return HttpStatus со статусом ответа
     */
    public HttpStatus getStatusCode() {
        if (this.status instanceof HttpStatus) {
            return (HttpStatus) this.status;
        } else {
            return HttpStatus.valueOf((Integer) this.status);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("<");
        builder.append(this.status.toString());
        if (this.status instanceof HttpStatus) {
            builder.append(' ');
            builder.append(((HttpStatus) this.status).getReasonPhrase());
        }
        builder.append(',');

        T body = getBody();
        HttpHeaders headers = getHeaders();
        if (body != null) {
            builder.append(body);
            builder.append(',');
        }
        builder.append(headers);
        builder.append('>');
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Response<?> response = (Response<?>) o;
        return Objects.equals(status, response.status);
    }

    @Override
    public int hashCode() {
        return (29 * super.hashCode() + ObjectUtils.nullSafeHashCode(this.status));
    }

    // Статические Builder методы

    /**
     * Создает Builder cо статусом с поданным в качестве параметра статусом.
     *
     * @param status Подаваемый статус
     * @return Созданный Builder
     */
    private static BodyBuilder httpStatus(HttpStatus status) {
        return new DefaultBuilder(status);
    }

    /**
     * Создает Builder cо статусом 200 (HttpStatus.OK).
     *
     * @return Созданный Builder
     */
    public static BodyBuilder ok() {
        return httpStatus(HttpStatus.OK);
    }

    /**
     * Создает Builder cо статусом 200 (HttpStatus.OK) и переданным телом.
     *
     * @return Созданный Builder с переданным телом
     */
    public static <T> Response<T> ok(T body) {
        return ok().body(body);
    }

    /**
     * Builder для возврата статуса ACCEPTED.
     *
     * @return Созданный Builder статус 202 (ACCEPTED)
     */
    public static BodyBuilder accepted() {
        return httpStatus(HttpStatus.ACCEPTED);
    }

    /**
     * Builder для возврата статуса NO_CONTENT.
     *
     * @return Созданный Builder статус 204 (NO_CONTENT)
     */
    public static BodyBuilder noContent() {
        return httpStatus(HttpStatus.NO_CONTENT);
    }

    /**
     * Builder для возврата статуса BAD_REQUEST
     *
     * @return Созданный Builder статус 400 (BAD_REQUEST)
     */
    public static BodyBuilder badRequest() {
        return httpStatus(HttpStatus.BAD_REQUEST);
    }

    /**
     * Builder работы с ошибками.
     *
     * @return Созданный Builder с заданным статусом
     */
    public static BodyBuilder error(HttpStatus status) {
        return httpStatus(status);
    }

    /**
     * Builder работы с ошибками с возможность задания сообщения.
     *
     * @return Созданный Builder с заданным статусом и сообщением
     */
    public static <T> Response<T> error(HttpStatus status, String text) {
        BodyBuilder builder = httpStatus(status);
        return (Response<T>) builder.message(text);
    }

    /**
     * Интерфейс Builder для задания залоговков.
     *
     * @param <B> Возвращаемое значение заголовка(-ов)
     */
    public interface HeadersBuilder<B> {
        /**
         * Задание одного заголовка в рамках Builder.
         *
         * @param headerName   Название заголовка заголовка. Может использоваться HttpHeaders или кастомное название
         * @param headerValues Значения заголовка. Используется при работе с кастомным названием заголовка
         * @return Заголовок
         */
        B header(String headerName, String... headerValues);

        /**
         * Задание нескольких заголовков в рамках Builder.
         *
         * @param headers Используемые HttpHeaders
         * @return Заголовки
         */
        B headers(@Nullable HttpHeaders headers);

        /**
         * Построение объекта в рамках Builder без тела.
         *
         * @return Созданный ответ без тела
         */
        <T> Response<T> build();
    }

    /**
     * Интерфейс Builder для задания тела.
     */
    public interface BodyBuilder extends HeadersBuilder<BodyBuilder> {

        BodyBuilder status(HttpStatus httpStatus);

        BodyBuilder contentType(MediaType var1);

        Response<String> message(String message);

        /**
         * Построение объекта в рамках Builder с телом.
         *
         * @return Созданный ответ c телом
         */
        <T> Response<T> body(@Nullable T body);
    }

    /**
     * Реализация Builder для задания всего ответа.
     */
    private static class DefaultBuilder implements BodyBuilder {
        private final Object statusCode;
        private final HttpHeaders headers = new HttpHeaders();

        private DefaultBuilder(Object statusCode) {
            this.statusCode = statusCode;
        }

        @Override
        public BodyBuilder header(String headerName, String... headerValues) {
            for (String headerValue : headerValues) {
                this.headers.add(headerName, headerValue);
            }
            return this;
        }

        @Override
        public BodyBuilder headers(@Nullable HttpHeaders headers) {
            if (headers != null) {
                this.headers.putAll(headers);
            }
            return this;
        }

        @Override
        public BodyBuilder contentType(MediaType contentType) {
            this.headers.setContentType(contentType);
            return this;
        }

        @Override
        public BodyBuilder status(HttpStatus httpStatus) {
            return error(httpStatus);
        }

        @Override
        public Response<String> message(String message) {
            return new Response<>(message, this.headers, this.statusCode);
        }

        @Override
        public <T> Response<T> build() {
            return new Response<>(null, this.headers, this.statusCode);
        }

        @Override
        public <T> Response<T> body(@Nullable T body) {
            return new Response<>(body, this.headers, this.statusCode);
        }
    }
}