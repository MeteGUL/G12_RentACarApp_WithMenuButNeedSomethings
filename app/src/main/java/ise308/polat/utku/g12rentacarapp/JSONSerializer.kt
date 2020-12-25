package ise308.polat.utku.g12rentacarapp

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.lang.StringBuilder
import java.io.Writer

class JSONSerializer(private val filename: String, private val context: Context) {
    @Throws(IOException::class, JSONException::class)
    fun save(carList: List<Cars>) {
        val jsonArray = JSONArray()
        for (cars in carList) {
            jsonArray.put(cars.convertTOJSON())
        }
        var writer: Writer? = null
        try {
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())
        } finally {
            if (writer != null) {
                writer.close()
            }
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Cars> {
        val carList = ArrayList<Cars>()
        var reader: BufferedReader? = null
        try {
            val inFile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(inFile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }

            val jsonArray = JSONTokener(jsonString.toString())
                    .nextValue() as JSONArray

            for (i in 0 until jsonArray.length()) {
                carList.add(Cars(jsonArray.getJSONObject(i)))
            }

        } catch (e: FileNotFoundException) {

        } finally {
            reader!!.close()
        }
        return carList
    }

}