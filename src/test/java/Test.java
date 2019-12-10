import com.shoppingmall.util.MD5Util;

public class Test {

    @org.junit.Test
    public void test(){
        String s = "123456";
        System.out.println(MD5Util.encode(s));
    }
}
