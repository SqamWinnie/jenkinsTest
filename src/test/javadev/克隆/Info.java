package javadev.克隆;

/**
 * 商品价目.
 * @author ...
 */
public class Info implements Cloneable{
    private Integer productId;

    private String productName;

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


    @Override
    public String toString() {
        String str =  "productId: " + this.productId
                + ", productName: " + this.productName;
        return str;
    }

    @Override
    public Info clone() throws CloneNotSupportedException{
        return (Info) super.clone();
    }

    public void info(){
        this.productId = 0;
        this.productName = "产品0";
    }
}