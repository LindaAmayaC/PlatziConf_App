package com.platzi.conf.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.platzi.conf.R
import com.platzi.conf.model.Ubication

import kotlinx.android.synthetic.main.fragment_ubication_detail_dialog.*

class UbicationDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ubication_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBarUbication.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_close)
        toolBarUbication.setTitleTextColor(Color.WHITE)
        toolBarUbication.setNavigationOnClickListener{
            dismiss()
        }
        val ubication = Ubication()
        toolBarUbication.title = ubication.name
        tvNombreLugar.text = ubication.name
        tvAdressUbication.text = ubication.address
        tvPhoneUbication.text = ubication.phone
        tvLinkUbication.text = ubication.website

        llTelefonoLugar.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${ubication.phone}")
            }
            startActivity(intent)
        }
        llSitioWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data= Uri.parse(ubication.website)
            startActivity(intent)
        }
    }
    override fun onStart(){
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
    }
}