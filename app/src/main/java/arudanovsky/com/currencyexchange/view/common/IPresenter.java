package arudanovsky.com.currencyexchange.view.common;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public interface IPresenter {
    void onCreate(IView view);
    void subscribe();
    void unsubscribe();
}
