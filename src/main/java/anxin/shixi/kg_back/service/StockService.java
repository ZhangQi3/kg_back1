package anxin.shixi.kg_back.service;

import anxin.shixi.kg_back.dao.StockDao;
import anxin.shixi.kg_back.pojo.Node.Concept;
import anxin.shixi.kg_back.pojo.Node.Kline;
import anxin.shixi.kg_back.pojo.Node.Plate;
import anxin.shixi.kg_back.pojo.Node.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StockService {
    @Autowired//自动装配对象
    StockDao stockDao;//引入dao

    /**
     * 最初版本初始化
     * @return
     */
    public Iterable<Stock> Init1(){
        return stockDao.Init1();
    }

    /**
     * 初始化返回板块
     * @return
     */
    public Iterable<Plate> Init(){return stockDao.Init();}

    /**
     * 测试用例
     */
    //public Stock findByShorthand(String shorthand){
    //    return stockDao.findByShorthand(shorthand);
    //}

    /**
     * 通过名字或代码模糊查询节点
     * @param chinesename
     * @return
     */
    public Iterable<Stock> queryByName(String chinesename){
        return stockDao.queryByName(chinesename);
    }

    /**
     * 通过stockcode查询股票实体
     * @param shorthand
     * @return
     */
    public Stock findByshorthand(String shorthand){
        return stockDao.findByShorthand(shorthand);
    }

    /**
     * 查询特定节点的所有有向关系节点，查不出来不明白为啥，用别的办法解决了
     * @param shorthand
     * @return
     */
    //public Iterable<Object> queryRelationNodeBy(String shorthand){
    //   return stockDao.queryRelationNodeByShorthand(shorthand);
    //}

    /**
     * 查询板块的股票关系节点
     * @param platename
     * @return
     */
    public Set<Stock> queryPRNByPlatename(String platename){
        return stockDao.queryPRNByPlatename(platename);
    }

    /**
     * 查询行业的股票关系节点
     * @param name
     * @return
     */
    public Set<Stock> queryIRNByName(String name){
        return stockDao.queryIRNByName(name);
    }

    /**
     * 查询地域的股票关系节点
     * @param provinces
     * @return
     */
    public Set<Stock> queryLRNByLocation(String provinces){
        return stockDao.queryLRNByLocation(provinces);
    }

    /**
     * 查询概念的股票关系节点
     * @param conceptname
     * @return
     */
    public Set<Stock> queryCRNByConcept(String conceptname){
        return stockDao.queryCRNByConcept(conceptname);
    }

    /**
     * 查询特定股票的概念关系节点,返回10个
     * @param shorthand
     * @return
     */
    public Set<Concept> queryCNByShorthand(String shorthand){
        return stockDao.queryCNByShorthand(shorthand);
    }

    /**
     * 通过股票代码查询股票
     * @param stockcode
     * @return
     */
    public Stock queryPriceByCode(String stockcode){
        return stockDao.queryPriceByCode(stockcode);
    }

    /**
     * 通过股票代码查询股票
     * @param stockcode
     * @return
     */
    public Kline queryKlineByStockcode(String stockcode){return stockDao.queryKlineByStockcode(stockcode);}
}
