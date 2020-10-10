package javabase.类型.decimal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

/**
 * Copyright @2000 Shanghai Hand Co. Ltd.
 * All right reserved.
 *
 * @author yachen.li@hand-china.com
 * @date 2019/11/26
 */
public class DecimalFormatTest {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.############");
        System.out.println(df.format(12345));
        System.out.println("".length());
//        BigDecimal a = new BigDecimal(10);
        BigDecimal a = null;
        BigDecimal b = null;
        System.out.println(addDec(a, b, a, b, b, b, b, a, a, a, b, b, a, a));
    }

    private static BigDecimal addDec(BigDecimal... decimals) {
        BigDecimal[] result = {null, new BigDecimal(0)};
        Arrays.stream(decimals).filter(Objects::nonNull).forEach(de -> {
            result[0] = de.add(result[0] == null ? result[1] : result[0]);
        });
        return result[0];
    }

}
