package com.dorianlamande.cleanarchisample.facts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.dorianlamande.cleanarchisample.R
import com.nicolasmouchel.executordecorator.MutableDecorator
import kotlinx.android.synthetic.main.activity_facts.*
import javax.inject.Inject

class FactsActivity : FactsView, AppCompatActivity() {
    @Inject lateinit var controller: FactsController
    @Inject lateinit var view: MutableDecorator<FactsView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        CleanArchiApplication.getComponent(this)
                .plus(FactsModule())
                .inject(this)

        view.mutate(this)

        controller.loadFacts()

    }

    override fun displayError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun displayFacts(facts: List<FactViewModel>) {
        factsRecyclerView.layoutManager = LinearLayoutManager(this)
        factsRecyclerView.adapter = FactsAdapter(facts)
    }

    override fun onDestroy() {
        view.mutate(null)
        super.onDestroy()
    }
}
