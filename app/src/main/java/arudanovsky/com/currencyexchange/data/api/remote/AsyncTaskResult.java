package arudanovsky.com.currencyexchange.data.api.remote;

/**
 * Created by arudanovskiy on 12/12/17.
 */

public class AsyncTaskResult<T> {
    private T result;
    private Throwable throwable;

    public AsyncTaskResult(T result) {
        this.result = result;
    }

    public AsyncTaskResult(Throwable throwable) {
        this.throwable = throwable;
    }

    public T getResult() {
        return result;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
