package cmpt213.assignment1.packagedeliveriestracker;

import java.time.LocalDateTime;

public class Package {
    private final String name;
    private final String notes;
    private final double price;
    private final double weight;
    private boolean isDelivered;
    private final LocalDateTime expectedDeliveryDate;

    public Package (String name, String notes, double price,
                    double weight, LocalDateTime date){
        this.isDelivered = false;

        this.name = name;
        this.notes = notes;
        this.price = price;
        this.weight = weight;
        this.expectedDeliveryDate = date;
    }

    //GETTERS, SETTER
    public void setDeliveryStatus(boolean newStatus){
        this.isDelivered = newStatus;
    }

    public boolean getDeliveryStatus() {
        return isDelivered;
    }

    public LocalDateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    //PRINT
    public void printPackageInfo(){
        String deliveryStatus = "Not delivered.";
        if(this.isDelivered){
            deliveryStatus = "Delivered.";
        }

        System.out.println("Package Name: "+this.name+"\n" +
                "Notes: "+this.notes+"\n" +
                "Price (CAD): $"+ this.price +"\n" +
                "Weight (kg): "+ this.weight +"kg\n" +
                "Expected Delivery Date: "+this.expectedDeliveryDate+"\n" +
                "Delivery Status: "+deliveryStatus);
    }

}
