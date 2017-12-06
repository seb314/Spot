package nrdzs.cs465.illinois.edu.spot;


import android.content.Context;

/**
 * Created by zuyi chen on 12/5/2017.
 */

public class GlobalApplicationVaribles{
    private int [] imageResources;

    private int [] leftImageResources;
    private int [] middleImageResources;
    private int [] rightImageResources;
    private Context ctx;

    private static GlobalApplicationVaribles instance;

    private GlobalApplicationVaribles(Context ctx){
        this.ctx = ctx;
    };

    public void initVariables(){
        this.imageResources = new int[] {R.drawable.grainger_image_1, R.drawable.grainger_image_2,
                R.drawable.grainger_image_3, R.drawable.grainger_image_4, R.drawable.grainger_image_5};

        this.leftImageResources = new int [] {R.drawable.grainger_image_1};

        this.middleImageResources = new int [] {R.drawable.grainger_image_3, R.drawable.grainger_image_4,
                R.drawable.grainger_image_5};

        this.rightImageResources = new int[]{R.drawable.grainger_image_2};

    }

    public void addMiddleResource(){
        int length = this.middleImageResources.length;
        int [] newArr = new int [length+1];
        for (int i=1; i<length+1; i++){
            newArr[i] = this.middleImageResources[i-1];
        }
        newArr[0] = R.drawable.spot_mark;
        this.middleImageResources = newArr;
    }

    public void addRightResource(){
        int length = this.rightImageResources.length;
        int [] newArr = new int [length+1];
        for (int i=1; i<length+1; i++){
            newArr[i] = this.rightImageResources[i-1];
        }
        newArr[0] = R.drawable.spot_mark;
        this.rightImageResources = newArr;
    }

    public void addLeftResource(){
        int length = this.leftImageResources.length;
        int [] newArr = new int [length+1];
        for (int i=1; i<length+1; i++){
            newArr[i] = this.leftImageResources[i-1];
        }
        newArr[0] = R.drawable.spot_mark;
        this.leftImageResources = newArr;
    }

    public int[] getImageResources(){
        return instance.imageResources;
    }

    public int[] getLeftImageResources(){
        return instance.leftImageResources;
    }

    public int[] getMiddleImageResources(){
        return instance.middleImageResources;
    }

    public int [] getRightImageResources(){
        return instance.rightImageResources;
    }

    public static synchronized GlobalApplicationVaribles getInstance(Context ctx){
        if (instance==null){
            instance = new GlobalApplicationVaribles(ctx);
            instance.initVariables();
        }

        return instance;
    }
}
