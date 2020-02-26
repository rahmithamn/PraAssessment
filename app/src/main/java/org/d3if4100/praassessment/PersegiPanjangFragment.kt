package org.d3if4100.praassessment


import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import org.d3if4100.praassessment.databinding.FragmentMainBinding
import org.d3if4100.praassessment.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {
    private var luasPersegiPanjang = 0.0
    private var kelilingPersegiPanjang = 0.0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPersegiPanjangBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_persegi_panjang, container, false)
        binding.btnHitung.setOnClickListener { v: View ->
            luasPersegiPanjang = et_inputpanjang.text.toString().toDouble() * et_inputlebar.text.toString().toDouble()
            kelilingPersegiPanjang = (et_inputlebar.text.toString().toDouble() * 2) + (et_inputpanjang.text.toString().toDouble() * 2)

            tv_luas.text = "Luas = $luasPersegiPanjang"
            tv_keliling.text = "Keliling = $kelilingPersegiPanjang"

        }
        if (savedInstanceState != null) {

            luasPersegiPanjang = savedInstanceState.getDouble("luas", luasPersegiPanjang)
            kelilingPersegiPanjang = savedInstanceState.getDouble("keliling", kelilingPersegiPanjang)



        }

        binding.btnShare.setOnClickListener {
            onShare()
        }
        return binding.root
    }

    @SuppressLint("StringFormatInvalid")
    private fun onShare() {
        val shareIntent = this.activity?.let {
            ShareCompat.IntentBuilder.from(it)
                .setText(getString(R.string.share_text, luasPersegiPanjang, kelilingPersegiPanjang))
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
        outState.putDouble("luas", luasPersegiPanjang)
        outState.putDouble("keliling", kelilingPersegiPanjang)
        super.onSaveInstanceState(outState)
    }
}
