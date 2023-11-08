public class InexactNumber {
    /*==================================================================
            from this point on is stuff added for instruction B2
    ==================================================================*/
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

    public float getX(){
        return this.x;
    }

    public float getDx(){
        return this.dx;
    }

    public float getMax(){
        return this.x+this.dx;
    }

    public float getMin(){
        return this.x-this.dx;
    }
    /*==================================================================
            from this point on is stuff added for instruction B3
    ==================================================================*/
    public String toString(){
        return  this.x+"\u00B1"+this.dx;
    }
    /*==================================================================
            from this point on is stuff added for instruction B4
    ==================================================================*/
    public InexactNumber add(InexactNumber other){
        return new InexactNumber(this.x+other.x,this.dx+other.dx);
    }

    public InexactNumber sub(InexactNumber other){
        return new InexactNumber(this.x-other.x,this.dx+other.dx);
    }

    public float indexOf(float in){
        if(in<0){
            return in*-1;
        }
        return in;
    }

    public InexactNumber mult(InexactNumber other){
        return new InexactNumber(this.x*other.x,indexOf(this.x*other.dx)+indexOf(other.x*this.dx));
    }

    public InexactNumber div(InexactNumber other){
        return new InexactNumber(this.x/other.x,(indexOf(this.x*other.dx)+indexOf(other.x*this.dx))/(other.x*other.x));
    }
    /*==================================================================
                       end of instructions from set B
    ==================================================================*/
}