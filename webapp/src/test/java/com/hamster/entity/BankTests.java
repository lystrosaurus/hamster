package com.hamster.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hamster.service.BankCardRepository;
import com.hamster.service.BankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by opabinia on 2017/5/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankTests {
    private static final Logger log = LoggerFactory.getLogger(BankTests.class);
    private static final String VALIDATE_AND_CACHE_CARD_INFO_URL
            = "https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=%s&cardBinCheck=true";
    private static final String LOGO_URL = "https://apimg.alipay.com/combo.png?d=cashier&t=%s";
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankCardRepository bankCardRepository;
    private Gson gson = new Gson();
    private final JsonObject bank = initBank();
    private final JsonObject bankCard = initBankCard();

    private JsonObject initBank() {
        try {
            return gson.fromJson(Files.newBufferedReader(
                    Paths.get(this.getClass().getClassLoader().getResource("resources/bank/bank.json").toURI())
            ), JsonObject.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    private JsonObject initBankCard() {
        try {
            return gson.fromJson(Files.newBufferedReader(
                    Paths.get(this.getClass().getClassLoader().getResource("resources/bank/bankBin.json").toURI())
            ), JsonObject.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (URISyntaxException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Test
    public void parse() throws IOException, URISyntaxException {
//        URL bankBin = this.getClass().getClassLoader().getResource("resources/bank/bankBin.json");
//        BufferedReader reader = Files.newBufferedReader(Paths.get(bankBin.toURI()));

//        reader.lines().forEach(line -> {
//            String[] bankProp = line.split("\\s");
//            for (int i = 0; i < bankProp.length; ++i) {
//                if (i == 0) {
//                    System.out.print(bankProp[i]);
//                } else {
//                    System.out.print(" " + bankProp[i]);
//                }
//            }
//            System.out.println();
//        });

//        JsonObject json = new JsonObject();
//        reader.lines().parallel().forEach(line -> {
//            String[] bankProp = line.split(" ");
//            JsonObject v = new JsonObject();
//            if (bankProp.length == 16) {
//                v.addProperty("bankName", bankProp[0].substring(1, bankProp[0].indexOf('(')));
//                v.addProperty("cardCode", bankProp[0].substring(bankProp[0].indexOf('(') + 1, bankProp[0].indexOf(')')));
//                v.addProperty("cardName", bankProp[1]);
//                v.addProperty("cardType", bankProp[15]);
//                v.addProperty("len", bankProp[8]);
//                v.addProperty("bin", bankProp[13]);
//            } else {
//
//                v.addProperty("bankName", line.substring(1, line.indexOf('(')));
//                v.addProperty("cardCode", line.substring(line.indexOf('(') + 1, line.indexOf(')')));
//                v.addProperty("cardName", line.substring(line.indexOf(' ', line.indexOf(')') + 2) + 1, line.indexOf('√') - 1));
//                v.addProperty("cardType", bankProp[bankProp.length - 1]);
//                v.addProperty("len", bankProp[bankProp.length - 9]);
//                v.addProperty("bin", bankProp[bankProp.length - 3]);
//            }
//
//            json.add(bankProp[bankProp.length - 3], v);
//        });
//        System.out.println(json.toString());
    }

    @Test
    public void testInitBankCard() {
        bankCardRepository.deleteAll();
        final AtomicLong id = new AtomicLong(1L);
        bankCard.entrySet().parallelStream().forEach(elementEntry -> {
            BankCard bankCard = new BankCard();
            JsonObject json = elementEntry.getValue().getAsJsonObject();
            bankCard.setBankName(json.get("bankName").getAsString());
            bankCard.setBin(json.get("bin").getAsString());
            bankCard.setCardCode(json.get("cardCode").getAsString());
            bankCard.setCardName(json.get("cardName").getAsString());
            bankCard.setCardType(json.get("cardType").getAsString());
            bankCard.setLength(json.get("len").getAsLong());
            bankCard.setId(id.getAndIncrement());

            bankCardRepository.save(bankCard);
        });
    }

    @Test
    public void testInitBank() {
        bankRepository.deleteAll();

        final AtomicLong id = new AtomicLong(1L);
        bank.entrySet().parallelStream().forEach(entry -> {
            Bank bank = new Bank();
            bank.setName(entry.getValue().getAsString());
            bank.setBank(entry.getKey());
            bank.setId(id.getAndIncrement());
            try {
                URL url = new URL(String.format(LOGO_URL, bank.getBank()));
                // 打开连接
                URLConnection con = url.openConnection();
                // 输入流
                InputStream is = con.getInputStream();
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流
                OutputStream os = new FileOutputStream(new File("F:/img/" + bank.getBank() + ".png"));
                // 开始读取
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                // 完毕，关闭所有链接
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bank.setLogo("http://10.1.14.163:8080/bank/logo/pc/" + bank.getBank() + ".png");
            bankRepository.save(bank);
        });
    }
}
