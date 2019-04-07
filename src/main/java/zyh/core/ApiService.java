package zyh.core;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zyh.bean.Result;
import zyh.bean.SearchGoodsBean;
import zyh.bean.ShopCarBean;

public interface ApiService {

    //搜索
    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<Result<List<SearchGoodsBean>>> getSearch(
            @Query("keyword")String keys,
            @Query("page")int pages,
            @Query("count")int counts);

    //搜索
    @GET("ks/product/getCarts")
    Observable<ShopCarBean> getShopCar(
            @Query("uid")int keys);

}
