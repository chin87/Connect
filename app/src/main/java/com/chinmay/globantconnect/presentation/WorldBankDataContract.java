package com.chinmay.globantconnect.presentation;

import com.chinmay.globantconnect.presentation.model.GlobantConnectData;

import java.util.List;

/**
 * Created by chinmay.deshpande on 10/11/17.
 */

public class WorldBankDataContract {

        public interface View {

            void showMessage(String message);

            void showProgressBar();

            void removeProgressBar();

            void showDbCatalog(List<GlobantConnectData> datacatalogArrayList);
        }

        public interface Presenter {
            void loadDataCatlog();

            void viewDestroy();

            void viewPause();

            void viewResume(View view);
        }

    }
