package com.bw.presenter;

import com.bw.base.BasePresenter;
import com.bw.core.DataCall;
import com.bw.core.IRequest;
import com.bw.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by 1607c王晴
 * date 2019/3/7
 * Describe:
 */
public class CartPresenter extends BasePresenter {
    public CartPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        IRequest iRequest = RetrofitUtils.getInstance().create(IRequest.class);

        return iRequest.getCart((int)args[0],(String) args[1]);
    }
}
