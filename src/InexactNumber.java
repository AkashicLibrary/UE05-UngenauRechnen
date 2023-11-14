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
    /*==================================================================
            from this point on is stuff added for instruction C2
    ==================================================================*/
        double errorRoom=0.002;

        //INFO: Yes I used trial and error until I got a number that ended up matching the minimum square meters with .getMax()
        double neededErrorRoom=0.2803578011725224;

        InexactNumber entrywayWidth=new InexactNumber(1.349,errorRoom);
        InexactNumber entrywayLength=new InexactNumber(1.808,errorRoom);
        InexactNumber entrywaySquareM=entrywayWidth.mult(entrywayLength);

        InexactNumber livingRoomWidth=new InexactNumber(4.892,errorRoom);
        InexactNumber livingRoomLength=new InexactNumber(3.24,errorRoom);
        InexactNumber livingRoomSquareM=livingRoomWidth.mult(livingRoomLength);

        InexactNumber bedRoomWidth=new InexactNumber(4.185,errorRoom);
        InexactNumber bedRoomLength=new InexactNumber(3.168,errorRoom);
        InexactNumber bedRoomSquareM=bedRoomWidth.mult(bedRoomLength);

        InexactNumber kitchenWidth=new InexactNumber(4.395,errorRoom);
        InexactNumber kitchenLength=new InexactNumber(2.678,errorRoom);
        InexactNumber kitchenSquareM=kitchenWidth.mult(kitchenLength);

        InexactNumber bathWidth=new InexactNumber(2.124,errorRoom);
        InexactNumber bathLength=new InexactNumber(2.673,errorRoom);
        InexactNumber bathSquareM=bathWidth.mult(bathLength);

        InexactNumber houseSquareM=entrywaySquareM.add(livingRoomSquareM.add(bedRoomSquareM.add(kitchenSquareM.add(bathSquareM))));
        double houseListingPrice=463.9;
        double houseSquareMPrice=8.061;

        System.out.println(houseSquareM.toString());
        System.out.println(houseSquareM.getMin());
        System.out.println(houseSquareM.getMax());
        System.out.println(houseListingPrice/houseSquareMPrice);
        System.out.println(houseListingPrice/houseSquareM.getMax());
        System.out.println(houseSquareM.getMax()*houseSquareMPrice);
        System.out.println(neededErrorRoom*1000);
    }
    /*==================================================================
                       end of instructions from set C
    ==================================================================*/
}