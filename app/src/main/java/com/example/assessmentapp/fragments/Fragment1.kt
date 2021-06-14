package com.example.assessmentapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.example.assessmentapp.R
import com.example.assessmentapp.model.Barcodes
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class Fragment1 : Fragment() {

    private lateinit var codeScanner : CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        barcodeScanner(view)

    }


    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        super.onPause()
        codeScanner.releaseResources()
    }

    private fun barcodeScanner(view: View) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE

        }
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                val date = Calendar.getInstance().time.toString()
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Barcode Information")
                val code = it.toString()
                val barcodes = Barcodes(code, date)
                myRef.child(code).setValue(barcodes)
                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
            }
        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }
}