public class InexactNumber {
    private float x;
    private float dx;
    public InexactNumber(){
        this.x=0;
        this.dx=0;
    }
    public InexactNumber(float x){
        this.x=x;
        this.dx=0;
    }

    public InexactNumber(float x, float dx) {
        if(dx<0){
            throw new RuntimeException("dx has to be positive!");
        }
        this.x=x;
        this.dx=dx;
    }
    float getX(){
        return this.x;
    }
    float getDx(){
        return this.dx;
    }
    float getMax(){
        return this.x+this.dx;
    }
    float getMin(){
        return this.x-this.dx;
    }
}