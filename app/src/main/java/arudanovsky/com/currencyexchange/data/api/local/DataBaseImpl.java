package arudanovsky.com.currencyexchange.data.api.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import arudanovsky.com.currencyexchange.common.EmptyResultException;
import arudanovsky.com.currencyexchange.domain.model.Currency;

/**
 * Created by arudanovskiy on 12/11/17.
 */

public class DataBaseImpl extends SQLiteOpenHelper implements Database {
    private DatabaseDataListener mListener;
    private SQLiteDatabase db;

    public DataBaseImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseImpl withListener(DatabaseDataListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public void init() {
        db = this.getWritableDatabase();
    }

    @Override
    public void saveList(List<Currency> currenciesListTO) {
        try {
            db.execSQL("drop table currency");
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Currency rub = new Currency();
        rub.setRateToRub(BigDecimal.ONE);
        rub.setCode("RUB");
        rub.setName("Российский рубль");
        currenciesListTO.add(rub);
        ContentValues cv = new ContentValues();
        for (Currency currency : currenciesListTO) {
            cv.put("id", currency.getId());
            cv.put("name", currency.getName());
            cv.put("code", currency.getCode());
            cv.put("rate", currency.getRateToRub().toString());
            db.insert("currency", null, cv);
            cv.clear();
        }
    }

    @Override
    public void provideCurrencies() {
        try {
            Cursor cursor = db.rawQuery("select distinct * from currency", null);
            if (cursor.getCount() > 0) {
                List<Currency> currencies = new ArrayList<>();
                cursor.moveToFirst();
                do {
                    Currency currency = new Currency();
                    currency.setId(cursor.getString(cursor.getColumnIndex("id")));
                    currency.setCode(cursor.getString(cursor.getColumnIndex("code")));
                    currency.setName(cursor.getString(cursor.getColumnIndex("name")));
                    currency.setRateToRub(new BigDecimal(cursor.getString(cursor.getColumnIndex("rate"))));
                    currencies.add(currency);
                    cursor.moveToNext();
                } while (!cursor.isLast());
                cursor.close();
                if (mListener != null)
                    mListener.onDataLoaded(currencies);
            } else {
                if (mListener != null)
                    mListener.onError(new EmptyResultException());
            }
        } catch (Throwable throwable) {
            if (mListener != null)
                mListener.onError(throwable);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table currency ("
                + "id text primary key,"
                + "historyId int,"
                + "name text,"
                + "code text,"
                + "rate text"+ ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
