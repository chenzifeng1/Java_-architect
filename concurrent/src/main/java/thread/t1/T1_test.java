package thread.t1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @ProjectName: concurrent
 * @Package: thread.t1
 * @ClassName: T1_test
 * @Author: czf
 * @Description: ${description}
 * @Date: 2020/9/14 16:56
 * @Version: 1.0
 */
public class T1_test {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            while (true) {
                String str = bufferedReader.readLine();
                if (!str.equals("#")) {
                    hashMap.put(str,str);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
