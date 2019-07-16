package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.*;
import com.myicellar.digitalmenu.service.*;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.utils.file.ExcelUtils;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/manage/test")
@Api(tags = "测试数据导入", description = "/manage/test")
public class ImportTestController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private WineryService wineryService;
    @Autowired
    private WineService wineService;
    @Autowired
    private OriginService originService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    public static Map<String,Long> typeMap = new HashMap<String,Long>();
    static {
        typeMap.put("STARTERS",1L );
        typeMap.put("SOUP",2L );
        typeMap.put("DRIED SEAFOOD",3L );
        typeMap.put("SEAFOOD",4L );
        typeMap.put("MEAT",5L );
        typeMap.put("BARBECUE MEAT",6L );
        typeMap.put("VEGETABLES",7L );
        typeMap.put("NOODLES / RICE",8L );
        typeMap.put("DESSERT",9L );
    }

    @ApiOperation("Excel导入Wine")
    @PostMapping("excelImportWine")
    @AuthIgnore
    public ResultVO<Integer> excelImportWine(@RequestParam("filePath")  String filePath) throws IOException {
        List<List<String>> lists = getExecelContent(filePath);
        Integer n = wineService.importWineData(lists);


        return ResultVO.success(n);
    }

    @ApiOperation("Excel导入Winery")
    @PostMapping("excelImportWinery")
    @AuthIgnore
    public ResultVO<Integer> excelImportWinery(@RequestParam("filePath")  String filePath) throws IOException {
        List<List<String>> lists = getExecelContent(filePath);
        Integer n = 0;
        for(List<String> list : lists){
            String wineryName = list.get(0);
            if(wineryName!=null && wineryName.trim().length()!=0) {

                Winery winery = new Winery();
                winery.setWineryId(snowflakeIdWorker.nextId());
                winery.setWinerySeoName(wineryName.toLowerCase());
                winery.setWineryNameEng(wineryName);
                winery.setNotePlainEng(list.get(1));
                winery.setAboutUrl(list.get(2));

                Date now = new Date();
                Long currentUserId = 1L;
                winery.setCreatedAt(now);
                winery.setUpdatedAt(now);
                winery.setCreatedBy(currentUserId);
                winery.setUpdatedBy(currentUserId);
                wineryService.insertSelective(winery);
                n++;
            }
        }


        return ResultVO.success(n);
    }

    @ApiOperation("Excel导入Food")
    @PostMapping("excelImportFood")
    @AuthIgnore
    public ResultVO<Integer> excelImportFood(@RequestParam("filePath")  String filePath) throws IOException {
        List<List<String>> lists = getExecelContent(filePath);
        Integer n = 0;
        for(List<String> list : lists){
            String foodName = list.get(1);
            if(foodName!=null && foodName.trim().length()!=0) {
                String[] foodArr = foodName.split("\n");
                List<String> foodNames = new ArrayList<String>();
                for(int j=0;j<foodArr.length;j++){
                    if(foodArr[j]!=null && foodArr[j].trim().length()!=0) {
                        foodNames.add(foodArr[j]);
                    }
                }
                Food food = new Food();
                food.setFoodId(snowflakeIdWorker.nextId());
                food.setFoodTypeId(typeMap.get(list.get(0)));
                food.setFoodNameCht(foodNames.get(0));
                if(foodNames.size()>1) {
                    food.setFoodNameEng(foodNames.get(1));
                }
                food.setIsEnabled((byte)1);
                Byte isRecommend = (byte)0;
                food.setIsRecommend(isRecommend);
                food.setPrice(new BigDecimal(98.00));
                Date now = new Date();
                Long currentUserId = 1L;
                food.setCreatedAt(now);
                food.setUpdatedAt(now);
                food.setCreatedBy(currentUserId);
                food.setUpdatedBy(currentUserId);
                foodService.insertSelective(food);
                n++;
            }
        }


        return ResultVO.success(n);
    }

    public List<List<String>> getExecelContent(String filePath){
        List<List<String>> lists = null;
        InputStream fis = null;
        try{
            fis = new FileInputStream(new File(filePath));
            lists = ExcelUtils.readRows(fis);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        return lists;
    }

}
