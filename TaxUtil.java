public class TaxUtil {
    public double calculateTax(double amount, double rate) {
        return amount * rate;
    }

    public static void main(String[] args){
        TaxUtil taxUtil = new TaxUtil();
        double num = taxUtil.calculateTax(4.3,2.4);
        System.out.println(num);
    }
 }
 