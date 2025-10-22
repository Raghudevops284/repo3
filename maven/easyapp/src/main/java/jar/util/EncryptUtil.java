package jar.util;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

/**
 * 加密工具类
 *
 * @author zhaozhiguo
 * @version 1.0
 * @date 2020/9/23 15:46
 */
public class EncryptUtil {
    private static final String KEY = "09a981cecf854bfe";
    private static final String IV_KEY = "CJObC1rGBlp65YnA";

    /**
     * 加解密
     *
     * @param mode 模式
     * @param src  源数据
     * @return 加密或解密后的数据
     * @throws Exception
     */
    private static byte[] doFinal(int mode, byte[] src) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(IV_KEY.getBytes());
        Key keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(mode, keySpec, ivSpec);
        return cipher.doFinal(src);

    }

    /**
     * AES加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String encrypt(String text) {
        try {
            return Base64.getEncoder().encodeToString(doFinal(Cipher.ENCRYPT_MODE, text.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * AES解密
     *
     * @param text 密文
     * @return 明文
     */
    public static String decrypt(String text) {
        try {
            return new String(doFinal(Cipher.DECRYPT_MODE, Base64.getDecoder().decode(text)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String password = EncryptUtil.encrypt("db2inst1");
        String text = EncryptUtil.decrypt(password);
        System.out.println(text + ":\t" + password);
    }
}
