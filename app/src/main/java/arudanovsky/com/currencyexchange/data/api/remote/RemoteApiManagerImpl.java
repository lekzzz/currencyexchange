package arudanovsky.com.currencyexchange.data.api.remote;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import arudanovsky.com.currencyexchange.data.dto.CurrenciesListTO;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class RemoteApiManagerImpl implements RemoteApiManager {
    private RemoteCallbackListener mListener;

    @Override
    public RemoteApiManager init(RemoteCallbackListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public void loadCurrencies() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = null;
                try {
                    HttpURLConnection conn = (HttpURLConnection)(new URL("http://www.cbr.ru/scripts/XML_daily.asp")).openConnection();
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    final StringBuilder sb = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    Serializer serializer = new Persister();
                    if (mListener != null)
                        mListener.onDataLoaded(serializer.read(CurrenciesListTO.class, sb.toString()));
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    if (mListener != null)
                        mListener.onErrorHappened(throwable);
                } finally {
                    try {
                        if (br != null) br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
