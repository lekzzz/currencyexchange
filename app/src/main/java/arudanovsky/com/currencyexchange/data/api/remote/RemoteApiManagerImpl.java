package arudanovsky.com.currencyexchange.data.api.remote;

import android.os.AsyncTask;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import arudanovsky.com.currencyexchange.data.dto.CurrenciesListTO;
import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class RemoteApiManagerImpl implements RemoteApiManager {
    private static RemoteCallbackListener mListener;

    @Override
    public RemoteApiManager init(RemoteCallbackListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public void loadCurrencies() {
        new Task().execute();
    }

    private static class Task extends AsyncTask<Void, Void, AsyncTaskResult<CurrenciesListTO>> {
        @Override
        protected AsyncTaskResult<CurrenciesListTO> doInBackground(Void... voids) {
            BufferedReader br = null;
            AsyncTaskResult<CurrenciesListTO> result = null;
            try {
                HttpURLConnection conn = (HttpURLConnection)(new URL("http://www.cbr.ru/scripts/XML_daily.asp")).openConnection();
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "windows-1251"));

                String line;
                final StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                Serializer serializer = new Persister();
                byte[] bytes = sb.toString().getBytes();
                result = new AsyncTaskResult<>(serializer.read(CurrenciesListTO.class, new String(bytes, "UTF-8")));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                result = new AsyncTaskResult<>(throwable);
            } finally {
                try {
                    if (br != null) br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(AsyncTaskResult<CurrenciesListTO> result) {
            super.onPostExecute(result);
            if (mListener != null) {
                if (result.getResult() != null)
                    mListener.onDataLoaded(result.getResult());
                else
                    mListener.onErrorHappened(result.getThrowable());
            }
        }
    }
}
