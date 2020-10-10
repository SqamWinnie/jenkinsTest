package dev.com;


import java.io.Serializable;
import java.util.List;

/**
 * 数据返回对象.
 *
 * @author njq.niu@hand-china.com
 */
public class Result implements Serializable {

    // 返回状态编码
    private String code;

    // 返回信息
    private String message;

    //数据
    private List<?> datas;

    // 成功标识
    private boolean success = true;

    //总数
    private Long total;

    public Result() {
    }

    public Result(boolean success) {
        setSuccess(success);
    }

    public Result(List<?> list) {
        this(true);
        setDatas(list);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<?> getDatas() {
        return datas;
    }

    public Long getTotal() {
        return total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
        if (null != datas) {
            setTotal((long) datas.size());
        }
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

