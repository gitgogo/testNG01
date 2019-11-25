package CommonTest;

/**
 * @Auther: liudongjie
 * @Date: 2019-11-25 15:34
 * @Description:
 */
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
@Test(groups = {"常用接口测试"})
public class commonTest01 {
    private String host = "http://www.mxnzp.com/api";
    private String history_day = "/history/today";
    private String SINGER = "/music/singer/search";

    // 历史上的今天
    @Test
    public void historyToday(){
        Response res = given().param("type","0").
                expect().statusCode(200).body("code",equalTo(1)).
                when().get(host + history_day);
        log.info("接口返回结果：" + res.asString());
    }
    // 搜索歌手
    @Test
    public void singerSearch(){
        Response res = given().param("keyWord","coldplay").
                expect().statusCode(200).body("code",equalTo(1)).
                when().get(host + SINGER);
        log.info("接口返回结果：" + res.asString());
    }

    @Test(skipFailedInvocations = true, enabled = false)
    public void queryBankList(){
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.put("brandId",0);
        data.put("userId",100000);
        Response res = given().contentType(ContentType.JSON).
                body(data).
                expect().statusCode(200).body("code",equalTo(0)).when().
                post("http://www.baidu.com/xxx");
        log.info("接口返回结果："+ res.asString());
    }

}
