package id.my.lowscarlet.uts_native.response;

public class GetResponse<T> {
    private String message;
    private T data;

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}