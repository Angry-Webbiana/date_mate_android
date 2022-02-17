package com.angrywebbiana.datemate.presentation.ui

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.angrywebbiana.datemate.databinding.ActivityWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    private lateinit var webSettings: WebSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra("url")

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.webView.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        webSettings = binding.webView.settings //세부 세팅 등록
        webSettings.javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
        webSettings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        webSettings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        webSettings.loadWithOverviewMode = true // 메타태그 허용 여부
        webSettings.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(false) // 화면 줌 허용 여부
        webSettings.builtInZoomControls = false // 화면 확대 축소 허용 여부
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        webSettings.domStorageEnabled = true // 로컬저장소 허용 여부

        if (url != null) binding.webView.loadUrl(url) // 웹뷰에 표시할 웹사이트 주소, 웹뷰 시작
    }
}