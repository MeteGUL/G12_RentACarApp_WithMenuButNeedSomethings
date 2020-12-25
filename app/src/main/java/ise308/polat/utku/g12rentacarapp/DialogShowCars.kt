package ise308.polat.utku.g12rentacarapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class DialogShowCars : DialogFragment() {
    private lateinit var car: Cars

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val dialogLayout = inflater?.inflate(R.layout.dialog_show_cars, null)

        val textViewCarModel = dialogLayout?.findViewById<TextView>(R.id.textView_car_model)
        val textViewCarType = dialogLayout?.findViewById<TextView>(R.id.textView_car_type)
        val textViewProductionYear = dialogLayout?.findViewById<TextView>(R.id.textView_production_year)
        val textViewRentPrice = dialogLayout?.findViewById<TextView>(R.id.textView_rent_price)
        val textViewRentFlag = dialogLayout?.findViewById<TextView>(R.id.textView_rent_flag)
        val textViewCarPlate = dialogLayout?.findViewById<TextView>(R.id.textView_plate)
        val buttonDone = dialogLayout?.findViewById<Button>(R.id.button_done)

        textViewCarModel?.text = car.carModel
        textViewCarType?.text = car.carType
        textViewProductionYear?.text = car.productionYear.toString()
        textViewRentPrice?.text = car.rentPrice.toString()
        textViewRentFlag?.text= car.rentFlag.toString()
        textViewCarPlate?.text = car.carPlate

        buttonDone?.setOnClickListener {
            dismiss()
        }

        builder.setView(dialogLayout)

        return builder.create()
    }

    fun setCars( car : Cars){
        this.car = car
    }
}
