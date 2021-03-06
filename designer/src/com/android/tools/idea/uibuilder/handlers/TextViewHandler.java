/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.tools.idea.uibuilder.handlers;

import com.android.tools.idea.uibuilder.api.ViewHandler;
import com.android.tools.idea.XmlBuilder;
import com.android.tools.idea.uibuilder.api.XmlType;
import com.android.tools.idea.uibuilder.model.NlComponent;
import com.google.common.collect.ImmutableSet;
import com.intellij.openapi.util.text.StringUtil;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static com.android.SdkConstants.*;

/**
 * Handler for several widgets that have a {@code text} attribute.
 */
public class TextViewHandler extends ViewHandler {
  private static final Set<String> HAVE_REDUCED_SCALE_IN_PREVIEW =
    ImmutableSet.of(AUTO_COMPLETE_TEXT_VIEW, EDIT_TEXT, MULTI_AUTO_COMPLETE_TEXT_VIEW);

  @NotNull
  @Override
  public String getTitleAttributes(@NotNull NlComponent component) {
    String text = component.getAttribute(ANDROID_URI, ATTR_TEXT);
    if (!StringUtil.isEmpty(text)) {
      // Display the android:text attribute if this component has such an attribute.
      return String.format("- \"%1$s\"", text);
    }
    return super.getTitleAttributes(component);
  }

  @Override
  @Language("XML")
  @NotNull
  public String getXml(@NotNull String tagName, @NotNull XmlType xmlType) {
    return new XmlBuilder()
      .startTag(tagName)
      .androidAttribute(ATTR_TEXT, tagName)
      .androidAttribute(ATTR_LAYOUT_WIDTH, VALUE_WRAP_CONTENT)
      .androidAttribute(ATTR_LAYOUT_HEIGHT, VALUE_WRAP_CONTENT)
      .endTag(tagName)
      .toString();
  }

  @Override
  public double getPreviewScale(@NotNull String tagName) {
    // EditText components are scaled to avoid a large presentation on the palette
    if (HAVE_REDUCED_SCALE_IN_PREVIEW.contains(tagName)) {
      return 0.8;
    }
    return super.getPreviewScale(tagName);
  }

  @Override
  @NotNull
  public String getPreferredProperty() {
    return ATTR_TEXT;
  }
}
