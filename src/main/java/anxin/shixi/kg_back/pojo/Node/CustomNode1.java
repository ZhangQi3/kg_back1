package anxin.shixi.kg_back.pojo.Node;

    /**
     * 自定义节点
     */
    public class CustomNode1 {
        private String shorthand;//股票简称
        private String stockcode;//股票代码

        public CustomNode1(String shorthand, String stockcode) {
            this.shorthand = shorthand;
            this.stockcode = stockcode;
        }

        public String getShorthand() {
            return shorthand;
        }

        public void setShorthand(String shorthand) {
            this.shorthand = shorthand;
        }

        public String getStockcode() {
            return stockcode;
        }

        public void setStockcode(String stockcode) {
            this.stockcode = stockcode;
        }
    }

