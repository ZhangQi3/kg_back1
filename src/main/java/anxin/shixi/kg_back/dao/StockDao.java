package anxin.shixi.kg_back.dao;


import anxin.shixi.kg_back.pojo.Node.Concept;
import anxin.shixi.kg_back.pojo.Node.Plate;
import anxin.shixi.kg_back.pojo.Node.Stock;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface StockDao extends Neo4jRepository<Stock,Long> {
    /**
     * 最初版本初始化，根据市值返回前50的股票
     * @return
     */
    @Query("MATCH (n:Stock) RETURN n order by n.regcapital desc LIMIT 50")//neo4j默认返回多个数据用Iterable<>集合
    Iterable<Stock> Init1();

    /**
     * 初始化返回板块节点
     * @return
     */
    @Query("MATCH (n:Plate) RETURN n")
    Iterable<Plate> Init();


    /**
     * 测试用例，这个是默认自带的查询
     */
    //Stock findByShorthand(String shorthand);

    /**
     * 通过名字或代码模糊查询节点
     * @param chinesename
     * @return
     */
    @Query("MATCH (n:Stock) WHERE n.chinesename =~ '.*'+$chinesename+'.*' or n.stockcode =~ '.*'+$chinesename+'.*' or n.shorthand =~ '.*'+$chinesename+'.*' RETURN n")
    Iterable<Stock> queryByName(@Param("chinesename") String chinesename);

    /**
     * 通过shorthand查询股票实体,用这个查询股票的所有关系节点
     * @param shorthand
     * @return
     */
    Stock findByShorthand(String shorthand);

    /**
     * 查询特定节点的所有有向关系节点,这个查不出来，不明白为啥,用其他办法解决了
     * @param shorthand
     * @return
     */
    //@Query("MATCH (:Stock {shorthand:'$shorthand'})-->(n) RETURN n")
    //Iterable<Object> queryRelationNodeByShorthand(@Param("shorthand") String shorthand);

    /**
     * 查询板块的股票关系节点,返回市值最大的前50个节点
     * @param platename
     * @return
     */
    @Query("MATCH (n)-->(:Plate {platename: $platename }) RETURN n order by n.regcapital desc LIMIT 10")
    Set<Stock> queryPRNByPlatename(@Param("platename") String platename);

    /**
     * 查询行业的股票关系节点,返回市值最大的前50个节点
     * @param name
     * @return
     */
    @Query("MATCH (n)-->(:Industry {name: $name }) RETURN n order by n.regcapital desc LIMIT 10")
    Set<Stock> queryIRNByName(@Param("name") String name);

    /**
     * 查询地域的股票关系节点,返回市值最大的前50个节点
     * @param provinces
     * @return
     */
    @Query("MATCH (n)-->(:Location {provinces: $provinces }) RETURN n order by n.regcapital desc LIMIT 10")
    Set<Stock> queryLRNByLocation(@Param("provinces") String provinces);

    /**
     * 查询概念的股票关系节点,返回市值最大的前50个节点
     * @param conceptname
     * @return
     */
    @Query("MATCH (n)-->(:Concept {conceptname: $conceptname }) RETURN n order by n.regcapital desc LIMIT 10")
    Set<Stock> queryCRNByConcept(@Param("conceptname") String conceptname);

    /**
     * 查询特定股票的概念关系节点,返回10个
     * @param shorthand
     * @return
     */
    @Query("MATCH (:Stock {shorthand:$shorthand})-->(n:Concept) RETURN n LIMIT 10")
    Set<Concept> queryCNByShorthand(@Param("shorthand") String shorthand);

    /**
     * 通过股票代码查询股票
     * @param stockcode
     * @return
     */
    @Query("MATCH (n:Stock) WHERE n.stockcode =~ '.*'+$stockcode+'.*' RETURN n")
    Stock queryPriceByCode(@Param("stockcode") String stockcode);

}
