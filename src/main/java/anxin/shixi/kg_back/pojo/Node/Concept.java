package anxin.shixi.kg_back.pojo.Node;

import anxin.shixi.kg_back.pojo.base.AllBase;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 概念实体
 */
@NodeEntity
public class Concept extends AllBase {
    private String conceptname;//概念

    public Concept(){}

    public Concept(String conceptname) {
        this.conceptname = conceptname;
    }

    public String getConceptname() {
        return conceptname;
    }

    public void setConceptname(String conceptname) {
        this.conceptname = conceptname;
    }
}
