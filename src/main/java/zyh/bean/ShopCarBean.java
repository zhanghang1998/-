package zyh.bean;

import java.util.List;

public class ShopCarBean {


    /**
     * msg : 请求成功
     * code : 0
     * */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"bargainPrice":99,"createtime":"2017-10-14T21:38:26","detailUrl":"http://172.17.8.100/images/small/commodity/xbsd/sjb/5/1.jpg","images":"http://172.17.8.100/images/small/commodity/xbsd/sjb/5/1.jpg","num":2,"pid":45,"price":2999,"pscid":39,"selected":0,"sellerid":1,"subhead":"高清双摄，就是清晰！2000+1600万高清摄像头，6GB大内存+高通骁龙835处理器，性能怪兽！","title":"一加手机5 (A5000) 6GB+64GB 月岩灰 全网通 双卡双待 移动联通电信4G手机"},{"bargainPrice":22.9,"createtime":"2017-10-14T21:48:08","detailUrl":"http://172.17.8.100/images/small/commodity/xbsd/sjb/5/1.jpg","images":"http://172.17.8.100/images/small/commodity/xbsd/sjb/5/1.jpg","num":2,"pid":24,"price":288,"pscid":2,"selected":0,"sellerid":1,"subhead":"三只松鼠零食特惠，专区满99减50，满199减100，火速抢购》","title":"三只松鼠 坚果炒货 零食奶油味 碧根果225g/袋"}]
         * sellerName : 商家1
         * sellerid : 1
         */

        private String sellerName;
        private String sellerid;
        private boolean flags;
        private List<ListBean> list;

        public boolean isFlags() {
            return flags;
        }

        public void setFlags(boolean flags) {
            this.flags = flags;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * bargainPrice : 99
             * createtime : 2017-10-14T21:38:26
             * detailUrl : http://172.17.8.100/images/small/commodity/xbsd/sjb/5/1.jpg
             * images : http://172.17.8.100/images/small/commodity/xbsd/sjb/5/1.jpg
             * num : 2
             * pid : 45
             * price : 2999
             * pscid : 39
             * selected : 0
             * sellerid : 1
             * subhead : 高清双摄，就是清晰！2000+1600万高清摄像头，6GB大内存+高通骁龙835处理器，性能怪兽！
             * title : 一加手机5 (A5000) 6GB+64GB 月岩灰 全网通 双卡双待 移动联通电信4G手机
             */

            private String createtime;
            private String detailUrl;
            private String images;
            private int num;
            private double price;
            private String subhead;
            private String title;
            private boolean flagItem;

            public boolean isFlagItem() {
                return flagItem;
            }

            public void setFlagItem(boolean flagItem) {
                this.flagItem = flagItem;
            }


            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getSubhead() {
                return subhead;
            }

            public void setSubhead(String subhead) {
                this.subhead = subhead;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
