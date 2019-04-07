package zyh.base;

public abstract class BasePresenter<V>{

    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();

    public void onBind(){
        view=null;
    }
}
