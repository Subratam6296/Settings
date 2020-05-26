/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.android.settings.display.darkmode;

import android.content.Context;
import android.content.res.Configuration;
import android.provider.Settings;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.SwitchPreference;

import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settings.R;

public class BlackThemePreferenceController extends AbstractPreferenceController implements
        PreferenceControllerMixin, Preference.OnPreferenceChangeListener {

    private static final String KEY_BERRY_BLACK_THEME = "berry_black_theme";

    public BlackThemePreferenceController(Context context) {
        super(context);
    }

    @Override
    public String getPreferenceKey() {
        return KEY_BERRY_BLACK_THEME;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void updateState(Preference preference) {
        int value = Settings.System.getInt(
                mContext.getContentResolver(), Settings.System.BERRY_BLACK_THEME, 0);
        ((SwitchPreference) preference).setChecked(value != 0);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean value = (Boolean) newValue;
        if(value && (mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_YES) == 0) {
            Toast.makeText(mContext, R.string.turn_on_dark_theme_info, Toast.LENGTH_SHORT).show();
            return false;
        }
        Settings.System.putInt(
                mContext.getContentResolver(), Settings.System.BERRY_BLACK_THEME, value ? 1 : 0);
        return true;
    }
}
