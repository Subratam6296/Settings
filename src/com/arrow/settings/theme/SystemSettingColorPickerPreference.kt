/*
 * Copyright (C) 2022
 * SPDX-License-Identifier: Apache-2.0
*/

package com.arrow.settings.theme

import android.content.Context
import android.util.AttributeSet

public class SystemSettingColorPickerPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
): ColorPickerPreference(context, attrs) {
    init {
        setPreferenceDataStore(SystemSettingsStore(context.contentResolver))
    }
}
