package com.mobile.ekstrakurikulerapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var etNama: EditText
    lateinit var etNIS: EditText
    lateinit var spinnerKelas: Spinner
    lateinit var btnTanggal: Button
    lateinit var tvTanggal: TextView
    lateinit var rgGender: RadioGroup
    lateinit var cbBasket: CheckBox
    lateinit var cbFutsal: CheckBox
    lateinit var cbPaduanSuara: CheckBox
    lateinit var etHariJam: EditText
    lateinit var btnSimpan: Button

    var tanggalLahir = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        etNama = findViewById(R.id.etNama)
        etNIS = findViewById(R.id.etNIS)
        spinnerKelas = findViewById(R.id.spinnerKelas)
        btnTanggal = findViewById(R.id.btnTanggal)
        tvTanggal = findViewById(R.id.tvTanggal)
        rgGender = findViewById(R.id.rgGender)
        cbBasket = findViewById(R.id.cbBasket)
        cbFutsal = findViewById(R.id.cbFutsal)
        cbPaduanSuara = findViewById(R.id.cbPaduanSuara)
        etHariJam = findViewById(R.id.etHariJam)
        btnSimpan = findViewById(R.id.btnSimpan)

        // Setup spinner
        val kelas = arrayOf("X IPA", "X IPS", "XI IPA", "XI IPS")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kelas)
        spinnerKelas.adapter = adapter

        // Date Picker
        btnTanggal.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, y, m, d ->
                tanggalLahir = "$d/${m+1}/$y"
                tvTanggal.text = tanggalLahir
            }, year, month, day)
            dpd.show()
        }

        // Button Simpan
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val nis = etNIS.text.toString()
            val kelasDipilih = spinnerKelas.selectedItem.toString()

            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "Tidak dipilih"
            }

            val ekstra = mutableListOf<String>()
            if (cbBasket.isChecked) ekstra.add("Basket")
            if (cbFutsal.isChecked) ekstra.add("Futsal")
            if (cbPaduanSuara.isChecked) ekstra.add("Paduan Suara")

            val hariJam = etHariJam.text.toString()

            val data = """
                Nama: $nama
                NIS: $nis
                Kelas: $kelasDipilih
                Tanggal Lahir: $tanggalLahir
                Gender: $gender
                Ekstrakurikuler: ${ekstra.joinToString(", ")}
                Hari & Jam: $hariJam
            """.trimIndent()

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("formData", data)
            startActivity(intent)
        }
    }
}