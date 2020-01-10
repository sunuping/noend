package com.sunup.noend;

import com.alibaba.fastjson.JSON;
import com.sunup.noend.entity.Commodity;
import com.sunup.noend.entity.Company;
import com.sunup.noend.pojo.Page;
import com.sunup.noend.service.i.CommodityService;
import com.sunup.noend.service.i.CompanyService;
import com.sunup.noend.util.ExcelUtil;
import com.sunup.noend.util.PingyinConver;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyAndCommodityTests {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CommodityService commodityService;
    private ExecutorService executorService = new ThreadPoolExecutor(100, 100, 0L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());


    @Test
//    @Transactional(rollbackFor = RuntimeException.class)
    public void testCommodityDataPoiInsert() throws IOException, BadHanyuPinyinOutputFormatCombination {
        File file = new File("C:\\Users\\lime\\Desktop\\新建文件夹 (2)\\");
        File[] files = file.listFiles();
        for (int o = 0, olen = files.length; o < olen; o++) {
//            log.info(files[o].getName());
            if (files[o].getName().length() < 5) {
                continue;
            }
            String[][] data = ExcelUtil.read(new MockMultipartFile(files[o].getName(), new FileInputStream(files[o])));
            //检查企业名称是否重复
            String name = null;
            int index = -1;
            String search = "收货单位：";
            boolean isBreak = false;
            for (int i = 0, len = data.length; i < len; i++) {
                for (int j = 0, jlen = data[i].length; j < jlen; j++) {
                    name = data[i][j];
                    if (name != null && !name.isEmpty() && name.trim().length() > 5) {
                        name = name.trim().replaceAll(" ", "");
                        index = name.indexOf(search);
                        if (index > -1) {
                            name = name.substring(index + search.length());
                            if (companyService.checkCompanyNameRepeat(name) <= 0) {
                                companyService.add(Company.builder().name(name).address("").build());
                                log.info(name);
                            }
                            isBreak = true;
                            break;
                        }
                    }
                }
                if (isBreak) {
                    break;
                }
            }
        }


    }

    /**
     * 测试商品的拼音修改
     *
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    public void testCommodityUpdate() throws BadHanyuPinyinOutputFormatCombination {
        List<Commodity> commodities = commodityService.all();
        for (int i = 0, len = commodities.size(); i < len; i++) {
            if (commodityService.update(commodities.get(i)) == 0) {
                log.error("id为{}的修改失败", commodities.get(i).getId());
            }
        }
    }

    /**
     * 测试企业名称和地址的拼音修改
     *
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    @Test
    @Transactional(rollbackFor = RuntimeException.class)
    public void testCompanyUpdate() throws BadHanyuPinyinOutputFormatCombination {
        List<Company> companies = companyService.all();
        for (int i = 0, len = companies.size(); i < len; i++) {
            if (companyService.update(companies.get(i)) == 0) {
                log.error("id为{}的修改失败", companies.get(i).getId());
            }
        }
    }

    @Test
    public void testCommodityAdd() throws UnsupportedEncodingException, InterruptedException {
        final List<String> temps1 = PingyinConver.randomChar(11, 11, 100);
        for (int i = 0, len = 100; i < len; i++) {
            int temp = i;
            executorService.submit(() -> {
                try {
                    commodityService.add(Commodity.builder().name(temps1.get(temp))
                            .namePinyin(PingyinConver.conver(temps1.get(temp), false))
                            .namePinyinFirst(PingyinConver.conver(temps1.get(temp), true))
                            .build());
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            });

            if ((i + 1) == len) {
                i = 0;
                Thread.sleep(3);
            }
        }
        Thread.sleep(60 * 100);
    }

    @Test
    public void testCompanySingleAdd() throws BadHanyuPinyinOutputFormatCombination {
        String name = "金牛区铭艳药房";
        String address = "未知";
        companyService.add(Company.builder().name(name)
                .namePinyin(PingyinConver.conver(name, false))
                .namePinyinFirst(PingyinConver.conver(name, true))
                .address(address)
                .addressPinyin(PingyinConver.conver(address, false))
                .addressPinyinFirst(PingyinConver.conver(address, true))
                .build());
    }

    @Test
    public void testCompanyAdd() throws UnsupportedEncodingException, InterruptedException {

        final List<String> temps1 = PingyinConver.randomChar(11, 11, 100);
        final List<String> temps2 = PingyinConver.randomChar(11, 31, 100);

        for (int i = 0, len = 100; i < len; i++) {
            int temp = i;
            executorService.submit(() -> {
                try {
                    companyService.add(Company.builder().name(temps1.get(temp))
                            .namePinyin(PingyinConver.conver(temps1.get(temp), false))
                            .namePinyinFirst(PingyinConver.conver(temps1.get(temp), true))
                            .address(temps2.get(temp))
                            .addressPinyin(PingyinConver.conver(temps2.get(temp), false))
                            .addressPinyinFirst(PingyinConver.conver(temps2.get(temp), true))
                            .build());
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();

                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            });

            if ((i + 1) == len) {
                i = 0;
                Thread.sleep(3);
            }
        }
        Thread.sleep(60 * 100);
    }

    /**
     * 测试全文检索公司名称或者地址
     */
    @Test
    public void testCompanyNameOrAddressFullTextSearch() {
        List<Company> companies = companyService.search("cxe");
        log.info(JSON.toJSONString(companies));
    }

    /**
     * 测试全文检索商品名称拼音
     */
    @Test
    public void testCommodityNameFullTextSearch() {

        Page page = commodityService.page(Page.builder().start(0).length(10).searchValue("我我我").build());
        Page page2 = commodityService.page(Page.builder().start(0).length(10).searchValue("www").build());
        Page page3 = commodityService.page(Page.builder().start(0).length(10).searchValue("我").build());
        log.info(JSON.toJSONString(page.getData()));
    }
}
