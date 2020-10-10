package javabase.父类;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 11861 on 2018/4/3.
 */
public class OverrideTest {
    public static void main(String[] args){
        IDtoService service = new dtoImpl();
        Dto dto = new Dto();
        dto.setDto1(1);
        dto.setDto2(2);
        dto.setDto3(3);
        dto.setDto4(4);
        service.select(dto);
    }
}

class Dto{
    private int dto1;
    private int dto2;
    private int dto3;
    private int dto4;

    public int getDto1() {
        return dto1;
    }

    public void setDto1(int dto1) {
        this.dto1 = dto1;
    }

    public int getDto2() {
        return dto2;
    }

    public void setDto2(int dto2) {
        this.dto2 = dto2;
    }

    public int getDto3() {
        return dto3;
    }

    public void setDto3(int dto3) {
        this.dto3 = dto3;
    }

    public int getDto4() {
        return dto4;
    }

    public void setDto4(int dto4) {
        this.dto4 = dto4;
    }
}

interface IDtoService{
    Dto select(Dto dto);
}

class dtoImpl implements IDtoService{
    DtoMapper mapper = new DtoMapper();
    @Override
    public Dto select(Dto dto) {
        return mapper.select(dto);
    }
}

class DtoMapper{

    public Dto select(Dto dto){

        Map<String, String> map = new HashMap<String, String>();
        if(dto==null){
            return null;
        }
        Field[] fields = dto.getClass().getDeclaredFields();//获取类的各个属性值
        for(Field field:fields){
            String fieldName =  field.getName();//获取类的属性名称
            if(getValueByFieldName(fieldName,dto)!=null)//获取类的属性名称对应的值
                map.put(fieldName,  getValueByFieldName(fieldName,dto));
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        return dto;
    }
    /**
     * 根据属性名获取该类此属性的值
     * @param fieldName
     * @param object
     * @return
     */
    public static String getValueByFieldName(String fieldName,Object object){
        String firstLetter=fieldName.substring(0,1).toUpperCase();
        String getter = "get"+firstLetter+fieldName.substring(1);
        try {
            Method method = object.getClass().getMethod(getter, new Class[]{});
            String value = method.invoke(object, new Object[] {}).toString();
            return value;
        } catch (Exception e) {
            return null;
        }
    }

}

interface IBaseService<T>{
    T select(T t);
}

class BaseImpl<T> implements IBaseService<T>{
    protected Mapper<T> mapper;
    public T select(T t) {
        return mapper.select(t);
    }
}

class Mapper<T>{
    public T select(T t){

        return t;
    }
}

class A{
    String i = "A";
    String f(){
        return "A";
    };
    static String g(){
        return "A";
    }
}

class B extends A{
    String i = "B";

    @Override
    public String f(){
        return "B_";
    }
    static String g(){
        return "B";
    }
}