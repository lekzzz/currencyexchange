package arudanovsky.com.currencyexchange.view.common;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public abstract class BaseFragment extends Fragment implements IView {

    @Override
    public void showError(String errorMessage) {
        if (getView() != null) {
            Snackbar snackbar = Snackbar.make(getView(), errorMessage, Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showProgressBar(boolean show) {

    }
}
