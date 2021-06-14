package com.example.assessmentapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.assessmentapp.adapters.Pager
import com.example.assessmentapp.fragments.Fragment1
import com.example.assessmentapp.fragments.Fragment2
import com.example.assessmentapp.fragments.Fragment3
import com.google.android.material.tabs.TabLayout

private val CAMERA_CODE = 101
class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionChecker()
        setContentView(R.layout.activity_main)
        settabs()
    }

    private fun permissionChecker(){
        val permission : Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED)
            makerequest()
    }

    private fun makerequest() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),CAMERA_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Again", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun settabs() {
        val adapter = Pager(supportFragmentManager)
        adapter.addfragment(Fragment1(), "First")
        adapter.addfragment(Fragment2(), "Second")
        adapter.addfragment(Fragment3(), "Third")
        val viewPager : ViewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter
        val tabLayout : TabLayout = findViewById(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)

    }
}