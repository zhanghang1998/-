package zyh.presenter;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zyh.base.BaseContract;
import zyh.base.BasePresenter;
import zyh.bean.Result;
import zyh.bean.ShopCarBean;
import zyh.model.SearchQueryModel;

public class SearchQueryPresenter extends BasePresenter<BaseContract.BaseView> implements BaseContract.BasePresenter {

    private boolean running;
    private SearchQueryModel searchQueryModel;

    public SearchQueryPresenter(BaseContract.BaseView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        //搜索model层
        searchQueryModel = new SearchQueryModel();
    }

    @Override
    public void requestData(Object... args) {

        if (running) {
            return;
        }
        running=true;
        searchQueryModel.requestData(args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        running=false;
                        view.onSuccess(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        running=false;
                        view.onSuccess(throwable);
                    }
                });
    }

    @Override
    public void requestShopCar(Object... args) {
        searchQueryModel.requestShopCar(args)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShopCarBean>() {
                    @Override
                    public void accept(ShopCarBean carBean) throws Exception {
                        view.onSuccess(carBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.onSuccess(throwable);
                    }
                });
    }

    public boolean isRunning() {
        return running;
    }
}
