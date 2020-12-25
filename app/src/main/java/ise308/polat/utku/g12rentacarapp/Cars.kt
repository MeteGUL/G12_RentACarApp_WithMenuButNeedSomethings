package ise308.polat.utku.g12rentacarapp

import org.json.JSONException
import org.json.JSONObject


private val JSON_CARMODEL = "car model"
private val JSON_CARTYPE = "car type"
private val JSON_PRODUCTIONYEAR = "production year"
private val JSON_RENTPRICE = "rent price"
private val JSON_RENTFLAG = "rent flag"
private val JSON_CARPLATE = "car plate"

class Cars {

    var carModel: String? = null
    var carType: String? = null
    var productionYear: Int = 0
    var rentPrice: Double = 0.0
    var rentFlag: Boolean = true
    var carPlate: String? = null




    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject) {
        carModel = jsonObject.getString(JSON_CARMODEL)
        carType = jsonObject.getString(JSON_CARTYPE)
        productionYear = jsonObject.getInt(JSON_PRODUCTIONYEAR)
        rentPrice = jsonObject.getDouble(JSON_RENTPRICE)
        rentFlag = jsonObject.getBoolean(JSON_RENTFLAG)
        carPlate = jsonObject.getString(JSON_CARPLATE)

    }

    constructor() {

    }

    constructor(                 // Base Class For Renting Cars
            carModel: String,
            carType: String,
            productionYear: Int,
            rentPrice: Double,
            rentFlag: Boolean,     // Is the selected car available for renting or not?
            carPlate: String){
        this.carModel = carModel
        this.carType = carType
        this.productionYear = productionYear
        this.rentPrice = rentPrice
        this.rentFlag = rentFlag
        this.carPlate = carPlate
    }

    constructor(                 // Base Class For Renting Cars
        carModel: String,
        carType: String,
        productionYear: Int,
        rentPrice: Double,
        carPlate: String){
        this.carModel = carModel
        this.carType = carType
        this.productionYear = productionYear
        this.rentPrice = rentPrice
        this.carPlate = carPlate
    }


    @Throws(JSONException::class)
    fun convertTOJSON(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(JSON_CARMODEL, carModel)
        jsonObject.put(JSON_CARTYPE, carType)
        jsonObject.put(JSON_PRODUCTIONYEAR, productionYear)
        jsonObject.put(JSON_RENTPRICE, rentPrice)
        jsonObject.put(JSON_RENTFLAG, rentFlag)
        jsonObject.put(JSON_CARPLATE, carPlate)

        return jsonObject
    }

}