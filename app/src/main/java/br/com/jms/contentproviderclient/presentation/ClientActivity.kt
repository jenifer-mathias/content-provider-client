package br.com.jms.contentproviderclient.presentation

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jms.contentproviderclient.R
import kotlinx.android.synthetic.main.activity_client.*

class ClientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)

        getContentProvider()

        bt_refresh_client.setOnClickListener { getContentProvider() }
    }

    private fun getContentProvider(){
        try {
            val url = "content://br.com.jms.contentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor: Cursor? =
                contentResolver.query(data, null, null, null, "title")
            client_list.apply {
                layoutManager = LinearLayoutManager(this@ClientActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}