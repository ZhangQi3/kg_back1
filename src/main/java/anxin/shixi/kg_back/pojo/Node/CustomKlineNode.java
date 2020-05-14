package anxin.shixi.kg_back.pojo.Node;

/**
 * 自定义k线节点，为了返回数组格式
 */
public class CustomKlineNode {
    String date;//日期
    String open;//开盘价
    String close;//收盘价
    String high;//最高价
    String low;//最低价
    String volume;//成交量
    String p_change;//涨跌幅

    public CustomKlineNode(String date, String open, String close, String high, String low, String volume, String p_change) {
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.p_change = p_change;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getP_change() {
        return p_change;
    }

    public void setP_change(String p_change) {
        this.p_change = p_change;
    }
}
