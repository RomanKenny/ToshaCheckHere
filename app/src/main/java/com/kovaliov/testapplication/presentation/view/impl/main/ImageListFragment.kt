package com.kovaliov.testapplication.presentation.view.impl.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kovaliov.testapplication.App
import com.kovaliov.testapplication.R
import com.kovaliov.testapplication.domain.entities.InputImage
import com.kovaliov.testapplication.presentation.isNetworkAvailable
import com.kovaliov.testapplication.presentation.presenter.main.ImageListPresenter
import com.kovaliov.testapplication.presentation.showToast
import com.kovaliov.testapplication.presentation.view.interfaces.ImageListScreen
import com.kovaliov.testapplication.presentation.view.impl.details.INPUT_IMAGE_KEY
import com.kovaliov.testapplication.presentation.view.impl.details.ImageDetailsActivity
import kotlinx.android.synthetic.main.fragment_image_list.*
import javax.inject.Inject

class ImageListFragment : Fragment(), ImageListScreen, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var imageListPresenter: ImageListPresenter

    private val adapter by lazy { ImageListAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }

    private fun inject() {
        App.getAppComponent().injectImageListFragment(this)
    }

    override fun onRefresh() {
        imageListPresenter.loadImageList(true)
    }

    override fun isNetworkAvailable() = requireContext().isNetworkAvailable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeLayout.setOnRefreshListener(this)
        rvGridImages.adapter = adapter
        imageListPresenter.onViewCreated()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
        imageListPresenter.onAttach(this)
    }

    override fun onDetach() {
        super.onDetach()
        imageListPresenter.onDetach()
    }

    override fun showLoading(state: Boolean) {
        swipeLayout.isRefreshing = state
    }

    override fun showLoadingError(error: String) {
        rvGridImages.visibility = View.GONE
        tvInfo.visibility = View.VISIBLE
        tvInfo.text = resources.getString(R.string.slide_down_for_update)
        requireActivity().showToast(error)
    }

    override fun showEmptyData() {
        tvInfo.visibility = View.VISIBLE
        tvInfo.text = resources.getString(R.string.not_found_correct_image_try_again)
    }

    override fun showImages(inputImageList: List<InputImage>) {
        tvInfo.visibility = View.GONE
        rvGridImages.visibility = View.VISIBLE
        adapter.updateData(inputImageList)
    }

    override fun onChoice(inputImage: InputImage) {
        val intent = Intent(requireContext(), ImageDetailsActivity::class.java)
        intent.putExtra(INPUT_IMAGE_KEY, inputImage)
        requireContext().startActivity(intent)
    }

    companion object{
        fun newInstance() = ImageListFragment()
    }

}