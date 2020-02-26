package org.d3if4100.praassessment


import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.KeyCharacterMap
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import kotlinx.android.synthetic.main.fragment_segitiga.*
import org.d3if4100.praassessment.databinding.FragmentPersegiPanjangBinding
import org.d3if4100.praassessment.databinding.FragmentSegitigaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaFragment : Fragment() {
    private var luasSegitiga = 0.0
    private var kelilingSegitiga = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSegitigaBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_segitiga, container, false)
        binding.btnHitungsegitiga.setOnClickListener { v: View ->
            var alas = et_inputalas.text.toString().toDouble()
            var alassquare = alas * alas
            var tinggi = et_inputtinggi.text.toString().toDouble()
            var tinggisquare = tinggi * tinggi
            var carisisimiring = alassquare + tinggisquare

            luasSegitiga = (alas * tinggi) / 2
            kelilingSegitiga = Math.sqrt(carisisimiring) + alas + tinggi

            tv_luassegitiga.text = "Luas = $luasSegitiga"
            tv_kelilingsegitiga.text = "Keliling = $kelilingSegitiga"


        }
        binding.btnSharesegitiga.setOnClickListener {
            onShare()
        }
        if (savedInstanceState != null) {

            luasSegitiga = savedInstanceState.getDouble(luasSegitiga.toString(), luasSegitiga)
            kelilingSegitiga = savedInstanceState.getDouble(kelilingSegitiga.toString(), kelilingSegitiga)



        }
        binding.luasSegitiga = luasSegitiga
        binding.kelilingSegitiga = kelilingSegitiga
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    private fun onShare() {
        val shareIntent = this.activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setText(getString(R.string.share_text, luasSegitiga, kelilingSegitiga))
                .setType("text/plain")
                .intent
        }
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this.activity, getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG).show()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble(luasSegitiga.toString(), luasSegitiga)
        outState.putDouble(kelilingSegitiga.toString(), kelilingSegitiga)
        super.onSaveInstanceState(outState)
    }

}
