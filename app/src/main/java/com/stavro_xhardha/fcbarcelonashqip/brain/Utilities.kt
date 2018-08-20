package com.stavro_xhardha.fcbarcelonashqip.brain

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog

object Utilities {

    public fun showLoadingMaterialDialog(conttext: Context): MaterialDialog =
            MaterialDialog.Builder(conttext)
                    .progress(false, 100, false)
                    .show()
}