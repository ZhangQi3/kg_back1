package anxin.shixi.kg_back.controller;

import anxin.shixi.kg_back.pojo.Node.*;
import anxin.shixi.kg_back.service.StockService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class StockController {
    @Autowired//自动装配对象
            StockService stockService;//引入service

    /**
     * 最初版本的初始化，返回市值前50的股票
     * @return
     */
    @PostMapping("/init1")
    public Iterable<Stock> Init1(){
        return stockService.Init1();
    }

    /**
     * 初始化返回板块节点
     * @return
     */
    @PostMapping("/init")
    public Iterable<Plate> Init(){return stockService.Init();}

    /**
     * 测试用例
     */
    //@GetMapping("/test/{shorthand}")
    //public Stock findByShorthand(@PathVariable("shorthand") String shorthand){
    //    return stockService.findByShorthand(shorthand);
    //}

    /**
     * 通过名字或代码模糊查询节点
     * @param chinesename
     * @return
     */
    @PostMapping("/queryByName")
    public Iterable<Stock> queryByName(@RequestParam("chinesename") String chinesename){//使用RequestParam获取key-value格式的参数
        return stockService.queryByName(chinesename);
    }

    /**
     * 通过stockcode查询股票实体
     * @param shorthand
     * @return
     */
    @PostMapping("/stock")
    public Stock findByStockcode(@RequestParam("shorthand") String shorthand){//前端写的nodeMsg就是股票代码
        return stockService.findByshorthand(shorthand);
    }

    /**
     * 返回所有股票的关系节点，课题中没有用到
     * @param stockcode
     * @return
     */
    @PostMapping("/querySRNByshorthand")
    public Set<CustomNode> querySRNByshorthand(@RequestParam("stockcode") String stockcode){
        Stock stock = findByStockcode(stockcode);//所点击的股票节点，即stockcode的股票实体
        Set<CustomNode> relationNodes = new HashSet<>();//初始化自定义关系节点集合
        Set<Location> locations = stock.getLocations();//地域关系节点
        for(Location location:locations){//将每一个地域节点都装进自定义关系节点里
            relationNodes.add(new CustomNode("Location",location.getProvinces(),location));
        }
        Set<Plate> plates = stock.getPlates();//板块关系节点
        for(Plate plate:plates){//将每一个板块节点都装进自定义关系节点里
            relationNodes.add(new CustomNode("Plate",plate.getPlatename(),plate));
        }
        Set<Industry> industries = stock.getIndustries();//行业关系节点
        for(Industry industry:industries){//将每一个行业节点都装进自定义关系节点里
            relationNodes.add(new CustomNode("Industry",industry.getName(),industry));
        }
        Set<Concept> concepts = stock.getConcepts();//概念关系节点
        for(Concept concept:concepts){//将每一个概念节点都装进自定义关系节点里
            relationNodes.add(new CustomNode("Concept",concept.getConceptname(),concept));
        }
        return relationNodes;//返回所有自定义格式的关系节点
    }

    /**
     * 查询某板块的股票关系节点
     * @param platename
     * @return
     */
    @PostMapping("/plate")
    public Set<Stock> queryPRNByplatename (@RequestParam("nodeMsg") String platename){
        Set<Stock>  stocks = stockService.queryPRNByPlatename(platename);//股票关系节点
        return stocks;
    }

    /**
     * 查询某行业的股票关系节点
     * @param name
     * @return
     */
    @PostMapping("/industry")
    public Set<Stock> queryIRNByName (@RequestParam("nodeMsg") String name){
        Set<Stock>  stocks = stockService.queryIRNByName(name);//股票关系节点
        return stocks;//返回所有关系节点
    }

    /**
     * 查询某地域的股票关系节点
     * @param provinces
     * @return
     */
    @PostMapping("/location")
    public Set<Stock> queryLRNByLocation (@RequestParam("nodeMsg") String provinces){
        Set<Stock>  stocks = stockService.queryLRNByLocation(provinces);//股票关系节点
        return stocks;//返回所有关系节点
    }

    /**
     * 查询某概念的股票关系节点
     * @param conceptname
     * @return
     */
    @PostMapping("/conception")
    public Set<Stock> queryCRNByConcept (@RequestParam("nodeMsg") String conceptname){
        Set<Stock>  stocks = stockService.queryCRNByConcept(conceptname);//股票关系节点
        return stocks;//返回所有的关系节点
    }

    /**
     * 查询特定股票的概念关系节点,返回10个
     * @param shorthand
     * @return
     */
    @PostMapping("/conceptionTag")
    public Set<CustomNode> queryCNByShorthand(@RequestParam("nodeMsg") String shorthand){
        Set<Concept>  concepts = stockService.queryCNByShorthand(shorthand);//股票关系节点
        Set<CustomNode> relationNodes = new HashSet<>();//初始化自定义关系节点集合
        for(Concept concept:concepts){//将每一个概念关系的股票节点都装进自定义关系节点里
            relationNodes.add(new CustomNode("Concept",concept.getConceptname(),concept));
        }
        return relationNodes;//返回所有自定义格式的关系节点
    }

    /**
     * 连接新浪的接口，用于实时获取股票的相关数据信息
     * @param stockcode
     * @return
     */
    @PostMapping("/price")
    public String[] queryPriceByCode(@RequestParam("stockcode") String stockcode){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";//用于将获取的数据转化为字符串
        String openprice = "";//今日开盘价
        String closeprice = "";//今日收盘价
        String price = "";//当前股票价
        String topprice = "";//今日最高价
        String bottomprice = "";//今日最低价

        DecimalFormat format = new DecimalFormat("0.00");//用于始终保留string的两位小数

        float a,b,c;//用于计算涨跌幅的一些变量
        String pricelimit = "";//当前涨跌幅
        String[] result1 = null;//用于将result分隔后存放的数组，这个数组可以初始化时不定长
        String[] endresult = new String[6];//初始化返回的最终结果数组，这个数组必须定长，不然返回报错
        Stock stock = stockService.queryPriceByCode(stockcode);
        String stockcode1 = stock.getStockcode();
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet("http://hq.sinajs.cn/list=" + stockcode1);
            // 设置请求头信息，鉴权
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            //System.out.print(response.toString());
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
            //result = result.substring(38,42);
            //System.out.print(result);
            result1 = result.split(",");//数据按逗号分隔成数组
            openprice = result1[1];
            closeprice = result1[2];
            price = result1[3];
            topprice = result1[4];
            bottomprice = result1[5];
             a = Float.parseFloat(price);
             b = Float.parseFloat(closeprice);
             c = ((a-b)/b)*100;
             if(c<0)//做一个判断，涨幅加上“+”号
             {
                 pricelimit = "" + c;
             }else{
                 pricelimit = "+" + c;
             }
             if(pricelimit.length()<5){//判断一下涨跌幅的字符长度
                 pricelimit = pricelimit.substring(0,4);//字符长度小于5的涨跌幅截取前4位
                 pricelimit = pricelimit+"0";//长度小于4位的最后一位补零，统一格式
             }else{
                 pricelimit = pricelimit.substring(0,5);//字符长度不小于5的涨跌幅截取前5位
             }
            openprice = format.format(new BigDecimal(openprice));//相关价格都保留2位
            //System.out.println(openprice);用于测试是否保留了两位小数
            closeprice = format.format(new BigDecimal(closeprice));
            price = format.format(new BigDecimal(price));
            topprice = format.format(new BigDecimal(topprice));
            bottomprice = format.format(new BigDecimal(bottomprice));
            //pricelimit = format.format(new BigDecimal(pricelimit));

            endresult[0] = openprice;//今日开盘价
            endresult[1] = closeprice;//今日收盘价
            endresult[2] = price;//当前价格
            endresult[3] = topprice;//今日最高价
            endresult[4] = bottomprice;//今日最低价
            endresult[5] = pricelimit;//涨跌幅
            //System.out.print(endresult[0]+endresult[1]+endresult[2]+endresult[3]+endresult[4]+endresult[5]);//这个就是数组里的内容，用于测试
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return endresult;
    }

    /**
     * 查询行业的股票关系节点,这里这个是备份
     * @param name
     * @return
     */
    //@PostMapping("/queryIRNByName")
    //public Set<CustomNode> queryIRNByName (@RequestParam("name") String name){
    //    Set<Stock>  stocks = stockService.queryIRNByName(name);//股票关系节点
    //    Set<CustomNode> relationNodes = new HashSet<>();//初始化自定义关系节点集合
    //    for(Stock stock:stocks){//将每一个板块关系的股票节点都装进自定义关系节点里
    //       relationNodes.add(new CustomNode("Stock",stock.getShorthand(),stock));
    //    }
    //    return relationNodes;//返回所有自定义格式的关系节点
    //}

}