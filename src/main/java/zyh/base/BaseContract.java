package zyh.base;

import io.reactivex.Observable;

public interface BaseContract {

    //顶层v层
    public interface BaseView<T>{
        void onSuccess(T data);

        void onError(Throwable throwable);
    }

    //顶层P层接口
    public interface BasePresenter {
        //响应数据
        void requestData(Object...args);

        void requestShopCar(Object...args);
    }

    //顶层M层对象
    public interface BaseModel<T> {

        Observable<T> requestData(Object...args);
        Observable<T> requestShopCar(Object...args);


    }

}
