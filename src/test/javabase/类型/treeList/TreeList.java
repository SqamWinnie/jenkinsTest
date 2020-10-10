package javabase.类型.treeList;

import java.util.*;

/**
 * @author 11861
 * @version 1.0
 * @date 2018/7/18
 */
public class TreeList {
    public static void main(String[] args) {
        List<Demo> demos = addDemo();
        Demo demo  = convert(demos);
        demo.getParentId();
    }
    /*将 demos 转化为 demoTree*/
    public static Demo convert(List<Demo> demos){
         /*1. 使用 Map 接收 demos */
        Map<Long,Demo> map = new HashMap<Long,Demo>();
        for(Demo demo:demos){
            map.put(demo.getId(),demo);
        }
        /*2. 找到根目录的id */
        for(Demo demo:demos){
            if(demo.getParentId()==0L){
                /*调用 3*/
                build(demo.getId(),map);
                Demo demo1 = map.get(demo.getId());
                return demo1;
            }
        }
        return new Demo();
    }
    /*3. 递归*/
    public static void build(Long id,Map<Long,Demo> map){
        Set<Long> sets = map.keySet();
        Iterator<Long> i = sets.iterator();
        List<Demo> list = new ArrayList<>();
        while (i.hasNext()) {
            long eid = i.next();
            if (map.get(eid).getParentId() == id) {
                build(eid, map);
                list.add(map.get(eid));
            }
        }
        map.get(id).setDemoList(list);
    }

    public static List<Demo> addDemo(){
        Demo demo1 = new Demo(1L,0L,"1");
        Demo demo2 = new Demo(2L,1L,"2");
        Demo demo3 = new Demo(3L,2L,"3");
        Demo demo4 = new Demo(4L,1L,"4");
        List<Demo> demos = new ArrayList<Demo>();
        demos.add(demo1);
        demos.add(demo2);
        demos.add(demo3);
        demos.add(demo4);
        return demos;
    }

}
