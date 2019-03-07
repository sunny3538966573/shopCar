package com.bw.core;

/**
 * Created by 1607c王晴
 * date 2019/3/7
 * Describe:
 */
public interface DataCall<T> {
    void onSuccess(T data);
    void onFail(Throwable throwable);
}
