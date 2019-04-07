package zyh.model;

import io.reactivex.Observable;
import zyh.base.BaseContract;
import zyh.core.ApiService;
import zyh.util.RetrofitUtil;

public class SearchQueryModel implements BaseContract.BaseModel{

    private int page;

    @Override
    public Observable requestData(Object... args) {

        boolean isflog = (boolean) args[1];
        if (isflog) {
            page = 1;
        } else {
            page++;
        }
        ApiService apiService = RetrofitUtil.instance().create(ApiService.class);
        return apiService.getSearch((String) args[0],page,10);
    }

    @Override
    public Observable requestShopCar(Object... args) {

        ApiService apiService = RetrofitUtil.instance().create(ApiService.class);
        return apiService.getShopCar(51);
    }


}
