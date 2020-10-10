package javabase.类型.treeList;

import java.util.List;

/**
 * @author 11861
 * @version 1.0
 * @date 2018/7/18
 */
public class Demo {
    private long id;
    private long parentId;
    private String name;
    private List<Demo> demoList;

    public Demo(){}

    public Demo(long id,long parentId,String name){
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Demo> getDemoList() {
        return demoList;
    }

    public void setDemoList(List<Demo> demoList) {
        this.demoList = demoList;
    }
}
