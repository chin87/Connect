package com.chinmay.globantconnect;

import com.chinmay.globantconnect.POJO.Datacatalog;
import com.chinmay.globantconnect.POJO.GlobantConnectData;

import java.util.ArrayList;

/**
 * Created by chinmay.deshpande on 10/11/17.
 */

public class WorldBankDataContract {

        public interface View {

            void showMessage(String message);

            void showProgressBar();

            void removeProgressBar();

            void showDbCatalog(ArrayList<GlobantConnectData> datacatalogArrayList);
        }

        public interface Actions {
            void loadDataCatlog();

            void viewDestroy();

            void viewPause();

            void viewResume(View view);
        }

    }
