package com.bw.core;

import com.bw.bean.CartBean;
import com.bw.bean.Result;
import com.bw.config.HttpConfig;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by 1607c王晴
 * date 2019/3/7
 * Describe:
 */
public interface IRequest {
    /**
     * 查询购物车
     * @param userId
     * @param sessionId
     * @return
     */
    @GET(HttpConfig.cart_url)
    Observable<Result<List<CartBean>>> getCart(@Header("userId") int userId,
                                               @Header("sessionId") String sessionId);


}
