package anxin.shixi.kg_back.pojo.Node;

import anxin.shixi.kg_back.pojo.base.AllBase;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * 股票实体
 */
@NodeEntity
public class Stock extends AllBase {
    /*
       股票的属性
     */
    private String stockcode;//股票代码
    private String chinesename;//公司中文全称
    private String englishname;//公司英文全称
    private String shorthand;//股票简称
    private String market;//交易所
    private Integer employee;//公司员工数
    private Integer regcapital;//公司市值
    private String profit;//总股本
    private String website;//公司官网
    private String business;//主营业务
    private String introduction;//公司简介
    private String office;//公司办公地址
    private String chairman;//公司法人


    /*
     * 股票的各种关系
     */
    @Relationship(type = "位于",direction = Relationship.OUTGOING)
    Set<Location> locations = new HashSet<>();

    @Relationship(type = "所在板块",direction = Relationship.OUTGOING)
    Set<Plate> plates = new HashSet<>();

    @Relationship(type = "所在行业",direction = Relationship.OUTGOING)
    Set<Industry> industries = new HashSet<>();

    @Relationship(type = "行业概念",direction = Relationship.OUTGOING)
    Set<Concept> concepts = new HashSet<>();


   public Stock(){
   }



    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand;
    }


    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getRegcapital() {
        return regcapital;
    }

    public void setRegcapital(Integer regcapital) {
        this.regcapital = regcapital;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getChairman() {
        return chairman;
    }

    public void setChairman(String chairman) {
        this.chairman = chairman;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Plate> getPlates() {
        return plates;
    }

    public void setPlates(Set<Plate> plates) {
        this.plates = plates;
    }

    public Set<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(Set<Industry> industries) {
        this.industries = industries;
    }

    public Set<Concept> getConcepts() {
        return concepts;
    }

    public void setConcepts(Set<Concept> concepts) {
        this.concepts = concepts;
    }
}
