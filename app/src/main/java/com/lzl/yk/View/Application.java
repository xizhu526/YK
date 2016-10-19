package com.lzl.yk.View;

public class Application extends android.app.Application {
    private static Application instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public synchronized  static Application getInstance(){
        if(instance==null){
            instance=new Application();
        }
        return  instance;
    }
}
