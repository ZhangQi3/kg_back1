package anxin.shixi.kg_back.pojo.Node;

/**
 * 自定义节点
 */
public class CustomNode {
    private String nodeType;//节点类型
    private String nodeName;//节点名称
    private Object node;//节点

    public CustomNode(String nodeType, String nodeName, Object node) {
        this.nodeType = nodeType;
        this.nodeName = nodeName;
        this.node = node;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Object getNode() {
        return node;
    }

    public void setNode(Object node) {
        this.node = node;
    }
}
