package anxin.shixi.kg_back.pojo.Node;

import anxin.shixi.kg_back.pojo.base.AllBase;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 行业实体
 */
@NodeEntity
public class Industry extends AllBase {
    private String name;//行业名称
    private String level;//行业等级
    private String industrycode;//行业代码

    public Industry(){}

    public Industry(String name, String level, String industrycode) {
        this.name = name;
        this.level = level;
        this.industrycode = industrycode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIndustrycode() {
        return industrycode;
    }

    public void setIndustrycode(String industrycode) {
        this.industrycode = industrycode;
    }
}
