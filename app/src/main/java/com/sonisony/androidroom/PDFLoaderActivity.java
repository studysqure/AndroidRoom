package com.sonisony.androidroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

public class PDFLoaderActivity extends AppCompatActivity implements DownloadFile.Listener  {
    RemotePDFViewPager read_pdf_view;
    private PDFPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfloader);
        statView();
        processView();
    }

    private void statView() {
        read_pdf_view = findViewById(R.id.read_pdf_view);

    }

    private void processView() {
        String url = "http://africau.edu/images/default/sample.pdf";
        read_pdf_view  = new RemotePDFViewPager(getApplicationContext(), url, this);

    }


    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, destinationPath);
        read_pdf_view.setAdapter(adapter);
        setContentView(read_pdf_view);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.close();
    }
}

