package com.sonisony.androidroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.sonisony.androidroom.Modelclass.PdfFileData;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;

public class PDFLoaderActivity extends AppCompatActivity implements DownloadFile.Listener  {
    RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter adapter;
    String url;
    PdfFileData pdfdata;
    RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfloader);
        statView();
        processView();
    }

    private void statView() {
//        read_pdf_view = findViewById(R.id.read_pdf_view);

    }

    private void processView() {
        database = RoomDB.getInstance(this);

        pdfdata = database.pdfSaveDao().getAll();
        Log.d("LLLLLLLLLLLLLLLLLLLLL", "onCreate: "+pdfdata);
//        String url = "http://africau.edu/images/default/sample.pdf";
      url = pdfdata.getPdf_file();
//        url = "http://demo.trackoplus.com/administrator_f2/Andro_App_APIs/pdf_file/dummy_pdf.pdf";

       /*
        read_pdf_view  = new RemotePDFViewPager(getApplicationContext(), url, this);*/
//        String url = "http://www.cals.uidaho.edu/edComm/curricula/CustRel_curriculum/content/sample.pdf";

         remotePDFViewPager =
                new RemotePDFViewPager(getApplicationContext(), url, this);

    }


    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, destinationPath);
        remotePDFViewPager.setAdapter(adapter);
        setContentView(remotePDFViewPager);


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

