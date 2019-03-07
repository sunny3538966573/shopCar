package com.bw.bean;

/**
 * Created by 1607c王晴
 * date 2019/3/7
 * Describe:
 */
public class Result<T> {
    /**
     * result : [{"commodityId":5,"commodityName":"双头两用修容笔","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39},{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","count":4,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39},{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","count":1,"pic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78},{"commodityId":11,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵","count":1,"pic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9},{"commodityId":17,"commodityName":"化妆镜","count":1,"pic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/7/1.jpg","price":31}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private T result;

    public Result(String message, String status, T result) {
        this.message = message;
        this.status = status;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
