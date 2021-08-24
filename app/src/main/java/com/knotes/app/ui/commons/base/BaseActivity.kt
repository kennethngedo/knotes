package com.knotes.app.ui.commons.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.internal.Primitives


abstract class BaseActivity<B: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: B
    protected lateinit var viewModel: VM

    /**
     * @return Triple
     *      <ul>
     *          <li>First: Layout ID</li>
     *          <li>Second: View Model Binding Variable ID</li>
     *          <li>Third: View Model Object Clazz</li>
     *      </ul>
     */
    abstract fun setViewConfiguration(): Triple<Int, Int, VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureDataBinding()
    }

    private fun configureDataBinding() {
        val (layoutID, viewModelVariableID, viewModel) = setViewConfiguration()
        binding = DataBindingUtil.setContentView(this, layoutID)
        binding.apply {
            this@BaseActivity.viewModel = viewModel //ViewModelProvider(this@BaseActivity).get(viewModelClass)
            setVariable(viewModelVariableID, this@BaseActivity.viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseActivity // Set the binding's lifecycle (otherwise Live Data won't work properly)
        }
    }

}