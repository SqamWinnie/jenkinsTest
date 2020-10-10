package javadev.克隆;

import java.util.Calendar;
import java.util.Date;

/**
 * 商品价目.
 * @author ...
 */
public class Product implements Cloneable{
    private Integer productId;

    private String productName;

    private Date workDate;

    private Info info;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Info getInfo() {
        return this.info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    @Override
    public String toString() {
        String str =  "productId: " + this.productId
                + ", productName: " + this.productName
                + ", workDate: " + this.workDate;
        if (this.info == null) {
            str +=  ", info: null";
        }else{
            str += ",\n ==================================================================   info 的值   ：" + this.info.toString();
        }
        return str;
    }

    /**
     * 深克隆(属性为对象时，该属性是浅克隆).
     * @return Product
     */
    public Product clone1() throws CloneNotSupportedException{
        return (Product) super.clone();
    }

     /**
     * 深克隆(属性为对象时，将该对象深克隆).
     * @return Product
     */
    public Product clone2() throws CloneNotSupportedException{
        Product cloned = (Product) super.clone();
        cloned.info = info.clone();
        return cloned;
    }

    public void product() {
        this.productId = 0;
        this.productName = "产品0";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        this.workDate = calendar.getTime();
    }

}