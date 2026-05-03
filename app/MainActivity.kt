package com.multiemu.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    data class Emulator(
        val nome: String,
        val cor: Int,
        val pacote: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lista = listOf(
            Emulator("NES", 0xFF777777.toInt(), "com.retroarch"),
            Emulator("SNES", 0xFFAA00FF.toInt(), "com.explusalpha.Snes9xPlus"),
            Emulator("PS1", 0xFF00AAFF.toInt(), "com.github.stenzek.duckstation"),
            Emulator("3DS", 0xFFFF8800.toInt(), "org.citra.emu"),
            Emulator("Wii / GameCube", 0xFF0044FF.toInt(), "org.dolphinemu.dolphinemu"),
            Emulator("PS2", 0xFF222222.toInt(), "xyz.aethersx2.android"),
            Emulator("DS", 0xFF00CC66.toInt(), "com.dsemu.drastic"),
            Emulator("Genesis", 0xFFFF3333.toInt(), "com.retroarch"),
            Emulator("Dreamcast", 0xFFFF6600.toInt(), "io.recompiled.redream")
        )

        val scroll = ScrollView(this)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        lista.forEach { emu ->
            val botao = Button(this)
            botao.text = emu.nome
            botao.textSize = 20f
            botao.setBackgroundColor(emu.cor)

            botao.setOnClickListener {
                val abrir = packageManager.getLaunchIntentForPackage(emu.pacote)

                if (abrir != null) {
                    startActivity(abrir)
                } else {
                    val loja = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/search?q=${emu.nome}&c=apps")
                    )
                    startActivity(loja)
                }
            }

            layout.addView(botao)
        }

        scroll.addView(layout)
        setContentView(scroll)
    }
}
