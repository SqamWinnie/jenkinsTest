package dev.com;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    /**
     * 分页测试.
     * @param dtoList  列表
     * @param page 页码
     * @param pageSize  每页数据量
     * @return ResponseData
     */
    public Result pageList(List<T> dtoList, int page, int pageSize) {
        Result rs = new Result(dtoList);
        if (pageSize == 0) {
            return rs;
        }
        List<T> returnList = new ArrayList<>();
        int count = 1;
        for (T info : dtoList) {
            if (count > (page - 1) * pageSize && count <= page * pageSize) {
                returnList.add(info);
            }
            count++;
        }
        rs.setDatas(returnList);
        rs.setTotal((long) dtoList.size());
        return rs;
    }
}
