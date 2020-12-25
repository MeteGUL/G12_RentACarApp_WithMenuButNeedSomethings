package ise308.polat.utku.g12rentacarapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class NewCarDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity?.layoutInflater
        val newCarDialog = inflater?.inflate(R.layout.dialog_new_car, null)

        val editTextCarModel = newCarDialog?.findViewById<EditText>(R.id.editText_car_model)
        val editTextCarType = newCarDialog?.findViewById<EditText>(R.id.editText_car_type)
        val editTextProductionYear =
            newCarDialog?.findViewById<EditText>(R.id.editText_production_year)
        val editTextRentPrice = newCarDialog?.findViewById<EditText>(R.id.editText_rent_price)
        val editTextCarPlate = newCarDialog?.findViewById<EditText>(R.id.editText_car_plate)

        val buttonAddCar = newCarDialog?.findViewById<Button>(R.id.button_add_car)
        val buttonCancel = newCarDialog?.findViewById<Button>(R.id.button_cancel)

        builder.setView(newCarDialog)

        buttonCancel?.setOnClickListener {
            dismiss()
        }
        buttonAddCar?.setOnClickListener {
            val car = Cars(
                editTextCarModel?.text.toString(),
                editTextCarType?.text.toString(),
                editTextProductionYear?.text.toString().toInt(),
                editTextRentPrice?.text.toString().toDouble(),
                editTextCarPlate?.text.toString()
            )
            val callingActivity = activity as MainActivity
            callingActivity.createNewCar(car)
            dismiss()
        }
        return builder.create()
    }
}
