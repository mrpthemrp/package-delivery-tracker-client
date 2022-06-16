package cmpt213.assignment2.packagedeliveriestracker;

import java.time.LocalDateTime;

public class PackageFactory {
    private String extraField;

    public Package getInstance(String packageType){

        if(packageType == null){
            return null;
        }

//        if(packageType == "BOOK"){
//            return
//        }
//
//        if(packageType == "PERISHABLE"){
//            return new Perishable();
//        }
//
//        if(packageType == "ELECTRONIC"){
//            return new Electronic();
//        }
        return null;
    }
}
