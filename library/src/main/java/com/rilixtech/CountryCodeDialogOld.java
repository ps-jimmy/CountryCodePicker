package com.rilixtech;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by hbb20 on 11/1/16.
 */
class CountryCodeDialogOld {
  public static void openCountryCodeDialog(CountryCodePicker codePicker) {
    Context context = codePicker.getContext();
    final Dialog dialog = new Dialog(context);
    codePicker.refreshCustomMasterList();
    codePicker.refreshPreferredCountries();
    List<Country> masterCountries = Country.getCustomMasterCountryList(codePicker);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setContentView(R.layout.layout_picker_dialog);
    RecyclerView recyclerView_countryDialog =
        (RecyclerView) dialog.findViewById(R.id.country_dialog_rv);
    final TextView textViewTitle = (TextView) dialog.findViewById(R.id.title_tv);
    textViewTitle.setText(R.string.select_country);
    final AppCompatEditText editText_search = (AppCompatEditText) dialog.findViewById(R.id.search_edt);
    editText_search.setHint(R.string.search_hint);
    AppCompatTextView textView_noResult = (AppCompatTextView) dialog.findViewById(R.id.no_result_tv);
    textView_noResult.setText(R.string.no_result_found);
    final CountryCodeAdapter cca =
        new CountryCodeAdapter(masterCountries, codePicker, editText_search,
            textView_noResult, dialog);
    if (!codePicker.isSelectionDialogShowSearch()) {
      Toast.makeText(context, "Found not to show search", Toast.LENGTH_SHORT).show();
      RelativeLayout.LayoutParams params =
          (RelativeLayout.LayoutParams) recyclerView_countryDialog.getLayoutParams();
      params.height = RecyclerView.LayoutParams.WRAP_CONTENT;
      recyclerView_countryDialog.setLayoutParams(params);
    }
    recyclerView_countryDialog.setLayoutManager(new LinearLayoutManager(context));
    recyclerView_countryDialog.setAdapter(cca);
    dialog.show();
  }
}
