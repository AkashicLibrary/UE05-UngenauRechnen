public class InexactNumber {
    /*==================================================================
            from this point on is stuff added for instruction B2
    ==================================================================*/
    private double x;
    private double dx;

    public InexactNumber(){
        this.x=0;
        this.dx=0;
    }

    public InexactNumber(double x){
        this.x=x;
        this.dx=0;
    }

    public InexactNumber(double x, double dx) {
        if(dx<0){
            //throw new RuntimeException("dx has to be positive!");
            dx=indexOf(dx);
        }
        this.x=x;
        this.dx=dx;
    }

    public double getX(){
        return this.x;
    }

    public double getDx(){
        return this.dx;
    }

    public double getMax(){
        return this.x+this.dx;
    }

    public double getMin(){
        return this.x-this.dx;
    }
    /*==================================================================
            from this point on is stuff added for instruction B3
    ==================================================================*/
    public String toString(){
        return  "("+this.x+"\u00B1"+this.dx+")";
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

    public double indexOf(double in){
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
    /*==================================================================
            from this point on is stuff added for instruction C1
    ==================================================================*/
    public void roundDx(int dezSpaces){
        double pow10=Math.pow(10, dezSpaces);
        double out=Math.round(this.dx*pow10);
        this.dx=out/pow10;
    }
    public static void main(String[] args) {
        InexactNumber whiskyGlass = new InexactNumber(0.02, 0.0014);
        InexactNumber whiskyBottle = new InexactNumber(0.7, 0);
        InexactNumber glassContainment = whiskyBottle.div(whiskyGlass);
        glassContainment.roundDx(2);
        int minGlasses= (int) Math.floor(0.7/ whiskyGlass.getMax());
        double maxGlassContent=whiskyGlass.getMax()*minGlasses;
        double maxGlassRemain= (double) (Math.round((0.7 - maxGlassContent) * 10000)) /10000;
        int maxGlasses= (int) Math.floor(0.7/whiskyGlass.getMin());
        double minGlassContent=whiskyGlass.getMin()*maxGlasses;
        double minGlassRemain= (double) (Math.round((0.7 - minGlassContent) * 10000)) /10000;
        System.out.println(glassContainment.toString());
        System.out.println(whiskyGlass.toString());
        System.out.println("mindestens "+minGlasses+" Gläser");
        System.out.println(maxGlassRemain+" Liter");
        System.out.println("maximal "+maxGlasses+" Gläser");
        System.out.println(minGlassRemain+" Liter");
    }
}