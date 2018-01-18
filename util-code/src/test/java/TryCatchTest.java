import com.google.common.base.Throwables;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

import java.util.Arrays;

public class TryCatchTest {


    @Test
    public void testCatch() {
        while (true) {
            try {
                int a = 1 / 0;
            } catch (Exception e) {
                System.out.println("error" + e.getMessage() + "..");
                e.printStackTrace();
                System.out.printf(Arrays.toString(e.getStackTrace()));
                break;
            } finally {
                System.out.println("finally");
            }
        }
    }

    @Test
    public void testMod() {
        System.out.println((int) 2157766004L % 3);
        System.out.println((int) (2157766004L % 3));
    }

}
