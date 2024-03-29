package com.example.loftcoin.rates;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loftcoin.data.Coin;
import com.example.loftcoin.data.CoinsRepository;
import com.example.loftcoin.data.Quote;
import com.example.loftcoin.util.ChangeFormat;
import com.example.loftcoin.util.ChangeFormatImpl;
import com.example.loftcoin.util.ImgUrlFormat;
import com.example.loftcoin.util.ImgUrlFormatImpl;
import com.example.loftcoin.util.PriceFormat;
import com.example.loftcoin.util.PriceFormatImpl;

import java.util.ArrayList;
import java.util.List;

class RatesViewModel extends ViewModel {

    private final CoinsRepository mRepository;
    private final PriceFormat mPriceFormat;
    private final ChangeFormat mChangeFormat;
    private final ImgUrlFormat mImgUrlFormat;

    private final MutableLiveData<List<CoinRate>> mDataSet = new MutableLiveData<>();

    private final MutableLiveData<Boolean> mOnTheFly = new MutableLiveData<>();

    private final MutableLiveData<Throwable> mError = new MutableLiveData<>();

    private String currency;

    private RatesViewModel(@NonNull CoinsRepository repository,
                           @NonNull PriceFormat priceFormat,
                           @NonNull ChangeFormat changeFormat,
                           @NonNull ImgUrlFormat imgUrlFormat) {
        mRepository = repository;
        mPriceFormat = priceFormat;
        mChangeFormat = changeFormat;
        mImgUrlFormat = imgUrlFormat;
        setCurrency("RUB");
    }

    public void setCurrency(String currency){
        this.currency = currency;
        refresh();
    }

    void refresh() {
        mOnTheFly.postValue(true);
        mRepository.listings(currency, coins -> {
            final List<CoinRate> exchangeRates = new ArrayList<>(coins.size());
            for (Coin coin : coins){
                final CoinRate.Builder builder = CoinRate.builder()
                        .id(coin.getId())
                        .imageUrl(mImgUrlFormat.format(coin.getId()))
                        .symbol(coin.getSymbol());
                Quote quote = coin.getQuotes().get(currency);
                if (quote != null) {
                    builder.price(mPriceFormat.format(quote.getPrice()))
                            .change24(mChangeFormat.format(quote.getChange24h()))
                            .isChange24Negative(quote.getChange24h() > 0d);
                }
                exchangeRates.add(builder.build());
            }
            mDataSet.postValue(exchangeRates);
            mOnTheFly.postValue(false);
        }, value -> {
            mError.postValue(value);
            mOnTheFly.postValue(false);
        });
    }

    @NonNull
    LiveData<Boolean> onTheFly() {
        return mOnTheFly;
    }

    @NonNull
    LiveData<List<CoinRate>> dataSet() {
        return mDataSet;
    }

    @NonNull
    LiveData<Throwable> error() {
        return mError;
    }

    static class Factory implements ViewModelProvider.Factory {

        private Context mContext;

        Factory(@NonNull Context context) {
            mContext = context;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new RatesViewModel(
                    CoinsRepository.get(),
                    new PriceFormatImpl(mContext),
                    new ChangeFormatImpl(mContext),
                    new ImgUrlFormatImpl()
            );
        }

    }

}
