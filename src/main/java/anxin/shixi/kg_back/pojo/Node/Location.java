package anxin.shixi.kg_back.pojo.Node;

import anxin.shixi.kg_back.pojo.base.AllBase;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 地域实体
 */
@NodeEntity
public class Location extends AllBase {
    private String provinces;//省份

    public Location(){}

    public Location(String provinces) {
        this.provinces = provinces;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }
}

