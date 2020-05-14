package anxin.shixi.kg_back.pojo.base;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

/**
 * 所有节点和关系的基类
 */
public abstract class AllBase {
    @Id//用于声明一个实体类的属性映射为数据库的主键列。这里就是说下面这个id就是Person的主键
    @GeneratedValue//@GeneratedValue用于标注主键的生成策略,通过strategy属性指定，注解存在的意义主要就是为一个实体生成一个唯一标识的主键
    private Long id;//neo4j自带的，给数据排序的id

    public Long getId() {
        return id;
    }
}
