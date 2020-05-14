package anxin.shixi.kg_back.pojo.Node;

import anxin.shixi.kg_back.pojo.base.AllBase;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * 板块实体
 */
@NodeEntity
public class Plate extends AllBase {
    private String platename;//板块名称


    public Plate(){}

    public Plate(String platename) {
        this.platename = platename;
    }

    public String getPlatename() {
        return platename;
    }

    public void setPlatename(String platename) {
        this.platename = platename;
    }

}
